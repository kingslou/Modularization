package com.geen.commonlibary.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * @author youtui-geen
 * @date 2018/8/4
 * @desc 封装eventBus
 */
public class EventBusUtil {
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }
}
