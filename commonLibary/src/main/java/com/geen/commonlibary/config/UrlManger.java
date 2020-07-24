package com.geen.commonlibary.config;

import com.geen.commonlibary.BuildConfig;

/***
 * @author genn
 *
 */
public class UrlManger {

    public static String getApiUrl() {
        int buildType = BuildConfig.ENV_TYPE;
        switch (buildType) {
            case EnvType.DEVELOP:
                return getTestUrl();
            case EnvType.UAT:
                return getUatUrl();
            case EnvType.PRODUCT:
                return getReleaseUrl();
            default:
                break;
        }
        return getReleaseUrl();
    }

    private static String getUatUrl() {

        return "http://192.168.5.60:8723/";
    }

    private static String getTestUrl() {

        return "http://172.16.10.234:8723/";
    }

    private static String getReleaseUrl() {
        return "http://seewayuat.creaway.cn:8723";
    }

    public static String getPlateKey() {
        return "089c4bcc432c5d701bdb11d735a09e31";
    }
}
