package com.geen.commonlibary.config;
import android.text.TextUtils;
import com.geen.commonlibary.BuildConfig;

/***
 * @author genn
 *
 */
public class UrlManger {

    private static String uatUrl;
    private static String developUrl;
    private static String productUrl;

    public static String getApiUrl() {
        int buildType = BuildConfig.ENV_TYPE;
        switch (buildType) {
            case EnvType.DEVELOP:
                return getDevelopUrl();
            case EnvType.UAT:
                return getUatUrl();
            case EnvType.PRODUCT:
                return getProductUrl();
            default:
                break;
        }
        return getProductUrl();
    }

    public static void setUatUrl(String url) {
        uatUrl = url;
    }

    public static void setDevelopUrl(String url) {
        developUrl = url;
    }

    public static void setProductUrl(String url) {
        productUrl = url;
    }

    private static String getUatUrl() {
        if (!TextUtils.isEmpty(uatUrl)) {
            return uatUrl;
        }
        return "http://192.168.5.60:8723/";
    }

    private static String getDevelopUrl() {
        if (!TextUtils.isEmpty(developUrl)) {
            return developUrl;
        }
        return "http://172.16.10.234:8723/";
    }

    private static String getProductUrl() {
        if (!TextUtils.isEmpty(productUrl)) {
            return productUrl;
        }
        return "http://seewayuat.creaway.cn:8723";
    }

    public static String getPlateKey() {
        return "089c4bcc432c5d701bdb11d735a09e31";
    }
}
