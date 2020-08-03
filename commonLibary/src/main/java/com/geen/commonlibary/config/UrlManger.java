package com.geen.commonlibary.config;

/***
 * @author genn
 *
 */
public class UrlManger {

    public final static int API_TYPE_DEV = 0;
    public final static int API_TYPE_UAT = 1;
    public final static int API_TYPE_RELEASE = 2;

    public static String getApiUrl(int netEnv) {

        switch (netEnv) {
            case API_TYPE_DEV:
                return getDevelopUrl();
            case API_TYPE_UAT:
                return getUatUrl();
            case API_TYPE_RELEASE:
                return getReleaseUrl();
            default:
                return getDevelopUrl();
        }
    }

    private static String getUatUrl() {

        return "http://192.168.5.60:8723/";
    }

    private static String getDevelopUrl() {

        return "http://172.16.10.234:8723/";
    }

    private static String getReleaseUrl() {

        return "http://seewayuat.creaway.cn:8723";
    }

    public static String getPlateKey() {
        return "089c4bcc432c5d701bdb11d735a09e31";
    }
}
