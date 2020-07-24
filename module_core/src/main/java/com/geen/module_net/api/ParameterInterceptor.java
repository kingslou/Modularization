package com.geen.module_net.api;

import androidx.annotation.NonNull;
import com.geen.commonlibary.config.AppConstans;
import com.geen.commonlibary.config.UrlManger;
import com.geen.commonlibary.utils.ConfigUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author youtui
 */
public class ParameterInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        String authTime = String.valueOf(System.currentTimeMillis());
        String sign = ApiSignUtils.getSign(original, authTime);
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("token", ConfigUtil.getString(AppConstans.TOKEN))
                .addQueryParameter("plateKey", UrlManger.getPlateKey())
                .addQueryParameter("authTime", authTime)
                .addQueryParameter("authSign", sign)
                .build();
        Request.Builder requestBuilder = original.newBuilder()
                .url(url)
                .method(original.method(), original.body());
        Request request = requestBuilder.build();

        return chain.proceed(request);
    }


}
