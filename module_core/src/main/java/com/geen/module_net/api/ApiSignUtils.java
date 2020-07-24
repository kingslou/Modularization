package com.geen.module_net.api;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;

/***
 * @author youtui
 * 验签工具类
 */
public class ApiSignUtils {

    public static String getSign(Request request, String timeStamp) {
        String method = request.method();
        //用于存储原请求的参数
        HashMap<String, String> params = new HashMap<>(16);
        //params.put("token", ConfigUtil.getString(AppConstans.TOKEN));


        //get方法和post方法处理参数情况不一样，需要区分开来
        if ("GET".equals(method)) {
            HttpUrl url = request.url();
            for (int i = 0; i < url.querySize(); i++) {
                //取出url中？后的参数
                String key = url.queryParameterName(i);
                String value = url.queryParameterValue(i);
                params.put(key, value);
            }
        } else if ("POST".equals(method)) {
            if (request.body() instanceof FormBody) {
                FormBody.Builder builder = new FormBody.Builder();
                FormBody oldFormBody = (FormBody) request.body();
                for (int i = 0; i < oldFormBody.size(); i++) {
                    //取出并保存原请求参数
                    params.put(oldFormBody.encodedName(i), oldFormBody.value(i));
                }
            }
        }
        String result = AuthUtil.getMCSign(params,timeStamp);
        return result;
    }

    public static class MapKeyComparator implements Comparator<String> {

        @Override
        public int compare(String str1, String str2) {

            return str1.compareTo(str2);
        }
    }


    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, Object> sortMap = new TreeMap<>(new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }

}
