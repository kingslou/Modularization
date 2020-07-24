package com.geen.module_net.api;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.geen.commonlibary.eventbus.Event;
import com.geen.commonlibary.eventbus.EventBusUtil;
import com.geen.commonlibary.eventbus.EventCode;
import com.geen.commonlibary.log.LuoJiFileLog;
import com.geen.commonlibary.utils.HandleUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class TokenInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response originalResponse = chain.proceed(request);
        ResponseBody responseBody = originalResponse.body();
        if(responseBody==null){
            return originalResponse;
        }
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.getBuffer();
        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }
        String bodyString = null;
        if (charset != null) {
            bodyString = buffer.clone().readString(charset);
        }

        LogUtils.d("TokenInterceptor", "bodyString = " + bodyString);
        LuoJiFileLog.d(TokenInterceptor.class,request.url() + bodyString);
        if(originalResponse.code()==403){
            //todo 提示Token过期
            HandleUtils.sUiHandler.post(() -> EventBusUtil.sendEvent(new Event(EventCode.EVENT_TOKEN_ERROR)));
        }
        return originalResponse;
    }
}
