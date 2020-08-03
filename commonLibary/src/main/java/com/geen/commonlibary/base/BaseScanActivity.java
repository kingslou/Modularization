package com.geen.commonlibary.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.device.ScanManager;
import android.device.scanner.configuration.PropertyID;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import androidx.annotation.Nullable;

import com.geen.commonlibary.eventbus.Event;
import com.geen.commonlibary.mvp.BasePresenter;
import com.geen.commonlibary.utils.ToastUtil;
import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.BarcodeDeviceConnectionEvent;
import com.honeywell.aidc.BarcodeFailureEvent;
import com.honeywell.aidc.BarcodeReadEvent;
import com.honeywell.aidc.BarcodeReader;
import com.honeywell.aidc.BarcodeReaderInfo;
import com.honeywell.aidc.ScannerNotClaimedException;
import com.honeywell.aidc.ScannerUnavailableException;
import com.honeywell.aidc.TriggerStateChangeEvent;
import com.honeywell.aidc.UnsupportedPropertyException;

import java.util.List;

public abstract class BaseScanActivity<T extends BasePresenter> extends BaseActivity<T>  implements BarcodeReader.BarcodeListener, BarcodeReader.TriggerListener, AidcManager.BarcodeDeviceListener {


    private AidcManager mAidcManager;
    private BarcodeReader mBarcodeReader;
    private BarcodeReader mInternalScannerReader;
    private boolean mKeyPressed = false;

    /***
     * ####### DT40 ############
     */
    private Vibrator mVibrator;
    private ScanManager mScanManager;
    private SoundPool soundpool = null;
    private int soundid;
    private String barcodeStr;
    private boolean isScaning = false;
    private final static String SCAN_ACTION = ScanManager.ACTION_DECODE;//default action
    private BroadcastReceiver mScanReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            isScaning = false;
            soundpool.play(soundid, 1, 1, 0, 0, 1);
            mVibrator.vibrate(100);
            byte[] barcode = intent.getByteArrayExtra(ScanManager.DECODE_DATA_TAG);
            int barcodelen = intent.getIntExtra(ScanManager.BARCODE_LENGTH_TAG, 0);
            byte temp = intent.getByteExtra(ScanManager.BARCODE_TYPE_TAG, (byte) 0);
            android.util.Log.i("debug", "----codetype--" + temp);
            barcodeStr = new String(barcode, 0, barcodelen);
            scanResult(barcodeStr);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        AidcManager.create(this, new MyCreatedCallback());
    }

    public abstract void scanResult(String result);

    /***
     * ########################################霍尼韦尔扫码 ################################
     */
    class MyCreatedCallback implements AidcManager.CreatedCallback {
        MyCreatedCallback() {
        }

        @Override
        public void onCreated(AidcManager aidcManager) {
            mAidcManager = aidcManager;
            mAidcManager.addBarcodeDeviceListener(BaseScanActivity.this);
            initAllBarcodeReaderAndSetDefault();
        }
    }

    void initAllBarcodeReaderAndSetDefault() {
        List<BarcodeReaderInfo> readerList = mAidcManager.listBarcodeDevices();
        mInternalScannerReader = null;

        for (BarcodeReaderInfo reader : readerList) {
            if ("dcs.scanner.imager".equals(reader.getName())) {
                mInternalScannerReader = initBarcodeReader(mInternalScannerReader, reader.getName());
            }
        }

        if (mInternalScannerReader != null) {
            mBarcodeReader = mInternalScannerReader;
        } else {
        }
        if (mBarcodeReader != null) {
            try {
                mBarcodeReader.addBarcodeListener(this);
                mBarcodeReader.addTriggerListener(this);
            } catch (Throwable e2) {
                e2.printStackTrace();
            }
            try {
                mBarcodeReader.setProperty(BarcodeReader.PROPERTY_NOTIFICATION_GOOD_READ_ENABLED, true);
                mBarcodeReader.setProperty(BarcodeReader.PROPERTY_NOTIFICATION_BAD_READ_ENABLED, true);

                mBarcodeReader.setProperty(BarcodeReader.PROPERTY_CODE_39_ENABLED, true);
                mBarcodeReader.setProperty(BarcodeReader.PROPERTY_EAN_13_ENABLED, true);
                mBarcodeReader.setProperty(BarcodeReader.PROPERTY_EAN_8_ENABLED, true);
                mBarcodeReader.setProperty(BarcodeReader.PROPERTY_CODE_39_FULL_ASCII_ENABLED, true);
                mBarcodeReader.setProperty(BarcodeReader.PROPERTY_CODE_93_ENABLED, true);
            } catch (UnsupportedPropertyException e) {
                e.printStackTrace();
            }

        }
    }

    BarcodeReader initBarcodeReader(BarcodeReader mReader, String mReaderName) {
        if (mReader == null) {
            if (mReaderName == null) {
                mReader = mAidcManager.createBarcodeReader();
            } else {
                mReader = mAidcManager.createBarcodeReader(mReaderName);
            }
            try {
                mReader.claim();
            } catch (ScannerUnavailableException e) {
                e.printStackTrace();
            }
            try {
                mReader.setProperty(BarcodeReader.PROPERTY_TRIGGER_CONTROL_MODE, BarcodeReader.TRIGGER_CONTROL_MODE_CLIENT_CONTROL);

            } catch (UnsupportedPropertyException e2) {
                e2.printStackTrace();
            }
        }
        return mReader;
    }

    @Override
    public void onBarcodeDeviceConnectionEvent(BarcodeDeviceConnectionEvent barcodeDeviceConnectionEvent) {

    }

    @Override
    public void onBarcodeEvent(BarcodeReadEvent barcodeReadEvent) {
        runOnUiThread(() -> {
            String barcodeDate = new String(barcodeReadEvent.getBarcodeData().getBytes(barcodeReadEvent.getCharset()));
            scanResult(barcodeDate);
        });
    }

    @Override
    public void onFailureEvent(BarcodeFailureEvent barcodeFailureEvent) {
        runOnUiThread(() -> {
            ToastUtil.showTips("没有扫描到信息，请重试");
        });
    }

    @Override
    public void onTriggerEvent(TriggerStateChangeEvent triggerStateChangeEvent) {
        if (triggerStateChangeEvent.getState()) {
            if (!mKeyPressed) {
                mKeyPressed = true;
                doScan(true);
            }
        } else {
            mKeyPressed = false;
            doScan(false);
        }
    }

    private void doScan(boolean do_scan) {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                ToastUtil.showTips("正在操作，请稍后在进行扫码");
                return;
            }
            mBarcodeReader.decode(do_scan);
        } catch (ScannerNotClaimedException e) {
            e.printStackTrace();
        } catch (ScannerUnavailableException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private void pauseHoney(){
        if (this.mInternalScannerReader != null) {
            this.mInternalScannerReader.release();
        }
    }

    private void resumeHoney(){
        if (this.mInternalScannerReader != null) {
            try {
                this.mInternalScannerReader.claim();
            } catch (ScannerUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    private void destroyHoney(){
        if (this.mInternalScannerReader != null) {
            this.mInternalScannerReader.removeBarcodeListener(this);
            this.mInternalScannerReader.removeTriggerListener(this);
            this.mInternalScannerReader.close();
            this.mInternalScannerReader = null;
        }
        if (this.mAidcManager != null) {
            this.mAidcManager.removeBarcodeDeviceListener(this);
            this.mAidcManager.close();
        }
    }


    /***
     * ####################################### 霍尼韦尔扫码结束 ###########################
     */




    /***
     * ##########################################  DT40扫码 ################################
     */
    private void initDt40Scan() {
        // TODO Auto-generated method stub
        try{
            mScanManager = new ScanManager();
            mScanManager.openScanner();
            mScanManager.switchOutputMode(0);
            soundpool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100); // MODE_RINGTONE
            soundid = soundpool.load("/etc/Scan_new.ogg", 1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void pauseDt40(){
        try{
            if (mScanManager != null) {
                mScanManager.stopDecode();
                isScaning = false;
                unregisterReceiver(mScanReceiver);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void resumeDt40(){
        try{
            initDt40Scan();
            IntentFilter filter = new IntentFilter();
            int[] idbuf = new int[]{PropertyID.WEDGE_INTENT_ACTION_NAME, PropertyID.WEDGE_INTENT_DATA_STRING_TAG};
            String[] value_buf = mScanManager.getParameterString(idbuf);
            if (value_buf != null && value_buf[0] != null && !value_buf[0].equals("")) {
                filter.addAction(value_buf[0]);
            } else {
                filter.addAction(SCAN_ACTION);
            }
            registerReceiver(mScanReceiver, filter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /***
     * ###################################### DT40 扫码结束################################
     *
     */


    @Override
    protected void onPause() {
        super.onPause();
        pauseDt40();
        pauseHoney();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeDt40();
        resumeHoney();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyHoney();
    }
}
