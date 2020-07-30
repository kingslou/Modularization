package com.geen.module_net.api;

import android.app.Activity;
import android.text.TextUtils;

import com.blankj.utilcode.util.ApiUtils;
import com.geen.commonlibary.config.UrlManger;
import com.geen.commonlibary.eventbus.Event;
import com.geen.commonlibary.eventbus.EventBusUtil;
import com.geen.commonlibary.eventbus.EventCode;
import com.geen.commonlibary.utils.HandleUtils;
import com.geen.module_net.api.interceptor.LogInterceptor;
import com.geen.module_net.bean.response.BaseResponse;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import cn.nekocode.rxlifecycle.LifecycleEvent;
import cn.nekocode.rxlifecycle.RxLifecycle;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author youtui
 */
public class ApiClient {
    private Retrofit mRetrofit;

    private String baseUrl;

    private static final int TIME_OUT = 20 * 1000;

    private ApiClient() {

    }

    private static class Holder {

        private static final ApiClient INSTANCE = new ApiClient();
    }

    public static ApiClient getInstance() {
        return Holder.INSTANCE;
    }

    public void initApiClient(String baseUrl) {
        if(mRetrofit!=null){
            return;
        }
        if (TextUtils.isEmpty(baseUrl)) {
            baseUrl = UrlManger.getApiUrl();
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new LogInterceptor())
                .addInterceptor(new ParameterInterceptor()).build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        mRetrofit = builder.build();
    }

    public <T> T create(Class<T> service) {
        if (mRetrofit == null) {
            initApiClient("");
        }
        return mRetrofit.create(service);
    }


    /***
     * 插入观察者
     * @param observable
     * @param respListener
     * @param <T>
     */
    public <T> void setSubscribe(Activity activity, Observable<T> observable, OnResponseListener<T> respListener) {
        observable.subscribeOn(Schedulers.io())
                //子线程访问网络
                .subscribeOn(Schedulers.newThread())
                //回调到主线程
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycle.bind(activity).disposeObservableWhen(LifecycleEvent.DESTROY))
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(T t) {
                        BaseResponse baseResponse = (BaseResponse) t;
                        if (baseResponse.isSuccess()) {
                            respListener.onSuccess(t);
                        } else {
                            String msg = baseResponse.getMsg();
                            if (TextUtils.isEmpty(msg)) {
                                msg = "请求失败，请重试";
                            }
                            respListener.onFailed(msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //todo 也可以在这里统一处理异常
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            if (exception.code() == 403) {
                                respListener.onFailed("");
                                HandleUtils.sUiHandler.post(() -> EventBusUtil.sendEvent(new Event(EventCode.EVENT_TOKEN_ERROR)));
                            } else {
                                try {
                                    ResponseBody body = exception.response().errorBody();
                                    String json = body.string();
                                    BaseResponse result = new Gson().fromJson(json, BaseResponse.class);
                                    respListener.onFailed(result.getMsg());
                                } catch (Exception exception1) {
                                    respListener.onFailed(exception1.getMessage());
                                }
                            }

                        } else {
                            respListener.onFailed(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
