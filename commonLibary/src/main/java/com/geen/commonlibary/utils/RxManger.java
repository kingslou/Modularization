package com.geen.commonlibary.utils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/***
 * @author 86153
 * Rx 请求管理器
 */
public class RxManger {
    private CompositeDisposable compositeDisposable;

    private RxManger(){

    }

    private static class SingletonHolder {
        private static final RxManger intstance = new RxManger();
    }

    public static RxManger getInstance(){
        return SingletonHolder.intstance;

    }

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void clearDisposable() {
        compositeDisposable.clear();
    }
}
