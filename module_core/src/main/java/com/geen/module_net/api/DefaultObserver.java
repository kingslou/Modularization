/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.geen.module_net.api;

import com.geen.commonlibary.utils.ToastUtil;
import com.google.gson.JsonSyntaxException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;


/**
 * Default {@link DisposableObserver} base class to be used whenever you want default error handling.
 */
public abstract class DefaultObserver<T> extends DisposableObserver<T> {

  @Override
  protected void onStart() {
    super.onStart();
  }

  @Override public void onNext(T t) {
    // no-op by default.
  }

  @Override public void onComplete() {
    // no-op by default.
  }

  @Override public void onError(Throwable exception) {
    // no-op by default.
    //todo 可以统一处理异常
    ToastUtil.showTips(unifiedError(exception));
  }

  public static String unifiedError(Throwable e){
    Throwable throwable;
    if(e instanceof UnknownHostException) {
      //无网络的情况，或者主机挂掉了。返回，对应消息 Unable to resolve host "m.app.haosou.com": No address associated with hostname
        //主机挂了，也就是你服务器关了
        throwable = new Throwable("NetWork Timeout", e.getCause());
    } else if(e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof SocketException){
      //连接超时等
      throwable = new Throwable("NetWork Timeout", e.getCause());
    } else if(e instanceof NumberFormatException || e instanceof IllegalArgumentException || e instanceof JsonSyntaxException){
      //也就是后台返回的数据，与你本地定义的Gson类，不一致，导致解析异常
      throwable = new Throwable("No Data", e.getCause());
    }else{
      //其他 未知
      throwable = e;
    }
    return throwable.getMessage();
  }
}
