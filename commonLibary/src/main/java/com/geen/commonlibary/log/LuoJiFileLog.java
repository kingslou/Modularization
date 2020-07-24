package com.geen.commonlibary.log;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/***
 * @author 86153
 * 写Log到文件
 * 写入到app data 下 兼容Android10文件存储
 */
public class LuoJiFileLog {
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    private static String filePath;
    private static String ZIP_LOG_PATH;
    private static String LOG_DIR ;
    private static File file;
    private static volatile BufferedWriter writer;
    private static volatile boolean canWrite = false;
    private static volatile LinkedBlockingQueue msgQueue;
    private static volatile boolean isRunning = false;
    private static volatile ConsumerThread consumerThread;
    private static long maxLogFileLength = 52428800L;
    private static volatile int currentPosition;
    private static int MAX_LOG_COUNT = 7;
    private static LuoJiFileLog sInstance;
    private static final String ZIP_LOG_FILE_NAME = "app_log.zip";

    public static LuoJiFileLog getInstance() {
        return sInstance == null ? new LuoJiFileLog() : sInstance;
    }

    private LuoJiFileLog() {
    }

    public static void setLogDirPath(String path) {
        if ((new File(path)).isDirectory()) {
            LOG_DIR = path;
        }
    }

    public static String getLogDirPath() {
        return LOG_DIR;
    }

    public static void setMaxRecordDay(int recordDay) {
        MAX_LOG_COUNT = recordDay;
    }

    public static int getMaxRecordDay() {
        return MAX_LOG_COUNT;
    }

    private static void setMaxLogFileLength(long bytes) {
        if (bytes > 0L) {
            maxLogFileLength = bytes;
        } else {
            Log.w("LuoJiFileLog", "set maxLogFileLength failed, it must bigger than 0");
        }

    }

    private static long getMaxLogFileLength() {
        return maxLogFileLength;
    }

    public static synchronized void start(Context context) {
        if (!isRunning) {
            isRunning = true;
            try {
                if (TextUtils.isEmpty(LOG_DIR)) {
                    File logDir = new File(Objects.requireNonNull(context.getExternalFilesDir((String) null)).getAbsolutePath() + File.separator + "appLog");
                    LOG_DIR = logDir.getAbsolutePath();
                    if (!logDir.exists()) {
                        logDir.mkdirs();
                    }
                }
                String[] fileArray = (new File(LOG_DIR)).list();
                Arrays.sort(fileArray);
                if (fileArray.length > MAX_LOG_COUNT) {
                    boolean isDeleted = (new File(LOG_DIR + File.separator + fileArray[0])).delete();
                }
                ZIP_LOG_PATH = context.getExternalFilesDir((String)null).getAbsolutePath() + File.separator + ZIP_LOG_FILE_NAME;
                filePath = LOG_DIR + File.separator + (new SimpleDateFormat("yyyyMMdd")).format(new Date())+"_appLog" + ".txt";
                file = new File(filePath);
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8"));
                msgQueue = new LinkedBlockingQueue();
                consumerThread = new ConsumerThread();
                consumerThread.start();
                canWrite = true;
            } catch (Exception var3) {
                Log.e("LuoJiFileLog", "init error - " + var3.toString());
            }

        }
    }

    public static synchronized void stop() {
        if (isRunning) {
            Log.i("LuoJiFileLog", "stop");
            isRunning = false;
            canWrite = false;
            file = null;

            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException var1) {
            }

            writer = null;
            if (msgQueue != null) {
                msgQueue.clear();
                msgQueue = null;
            }

            if (consumerThread != null) {
                consumerThread.sendExitSignal();
                consumerThread = null;
            }

        }
    }

    public static boolean isRunning() {
        return isRunning;
    }

    private static boolean resetWriter() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException var4) {
        }

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8"));
            return true;
        } catch (Exception var3) {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException var2) {
            }

            return false;
        }
    }

    private static void produce(String level, Class clazz, String tag, String msg) {
        if (canWrite && msgQueue != null) {
            try {
                String str = String.format(Locale.CHINA, "%s %s %s/%s: %s\n", df.format(new Date()), clazz == null ? "" : (clazz.getName() == null ? "" : clazz.getName()), level, tag, msg);
                msgQueue.offer(str);
            } catch (Exception var5) {
                var5.printStackTrace();
            }

        }
    }

    public static void i(Class clazz, String tag, String content) {
        produce("I", clazz, tag, content);
    }

    public static void i(Class clazz, String content) {
        produce("I", clazz, "", content);
    }

    public static void d(Class clazz, String tag, String content) {
        produce("D", clazz, tag, content);
    }

    public static void d(Class clazz, String content) {
        produce("D", clazz, "", content);
    }

    public static void w(Class clazz, String tag, String content) {
        produce("W", clazz, tag, content);
    }

    public static void w(Class clazz, String content) {
        produce("W", clazz, "", content);
    }

    public static void e(Class clazz, String tag, String content) {
        produce("E", clazz, tag, content);
    }

    public static void e(Class clazz, String content) {
        produce("E", clazz, "", content);
    }

    public static void logWithVideoPosition(Class clazz, String tag, String content) {
        produce("D", clazz, tag, "currentPos=" + currentPosition + ", " + content);
    }

    public static void updatePosition(int position) {
        currentPosition = position;
    }

    public static void zipLogFiles(final IZipCallback zipCallback) {
        (new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
//                try {
//                    //copy 百家的日志
////                    String srcPath = Objects.requireNonNull(BaseApplication.getInstance().getExternalFilesDir(null)).getAbsolutePath() + File.separator + "bj_live_log";
////                    FileHelper.copyFolder(srcPath,getLogDirPath());
//                   // ZipUtils.compress(getLogDirPath(), ZIP_LOG_PATH);
//                } catch (IOException var3) {
//                    var3.printStackTrace();
//                    return "error:" + var3.getMessage();
//                }

                return ZIP_LOG_PATH;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.startsWith("error:")) {
                    zipCallback.zipFailed(s.substring("error:".length()));
                } else {
                    zipCallback.zipSuccess(s);
                }

            }
        }).execute(new Void[0]);
    }

    public static boolean deleteZipLogFiles() {
        File zipLogFile = new File(ZIP_LOG_PATH);
        return zipLogFile.exists() && zipLogFile.delete();
    }

    public interface IZipCallback {
        void zipSuccess(String var1);

        void zipFailed(String var1);
    }

    private static class ConsumerThread extends Thread {
        private boolean exitSignal;

        private ConsumerThread() {
            this.exitSignal = false;
        }

        public void sendExitSignal() {
            this.exitSignal = true;
        }

        @Override
        public void run() {
            long lastCheckTs = System.currentTimeMillis();

            while(!this.exitSignal && this == consumerThread) {
                String s = null;

                try {
                    long nowTs = System.currentTimeMillis();
                    if (nowTs - lastCheckTs > 10000L) {
                        lastCheckTs = nowTs;
                        if (file != null && !file.exists()) {
                            Log.i("LuoJiFileLog", "found log file is not exists, will create it");
                            resetWriter();
                        } else if (file != null && file.exists() && file.length() > maxLogFileLength) {
                            Log.i("LuoJiFileLog", "log file is too big, will recreate it");
                            file.delete();
                            resetWriter();
                        }
                    }

                    try {
                        if (msgQueue != null) {
                            s = (String)msgQueue.poll(5L, TimeUnit.SECONDS);
                        }
                    } catch (InterruptedException var8) {
                    }

                    if (s != null && canWrite) {
                        if (writer == null) {
                            resetWriter();
                        }

                        try {
                            writer.write(s);
                            writer.flush();
                        } catch (Exception var7) {
                            resetWriter();
                        }
                    }
                } catch (Exception var9) {
                }
            }

            Log.i("LuoJiFileLog", "ConsumerThread exit, exitSignal=" + this.exitSignal + ", thread is valid=" + (this == consumerThread));
        }
    }
}
