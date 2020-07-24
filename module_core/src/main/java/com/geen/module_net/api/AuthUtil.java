package com.geen.module_net.api;

import com.blankj.utilcode.util.LogUtils;
import com.socks.library.KLog;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * auth code 工具类
 *
 * @author stone
 */
public class AuthUtil {

    /**
     * 合作伙伴key.以","分隔
     */
    public static final String APP_KEY = "089c4bcc432c5d701bdb11d735a09e31";
    /**
     * 加密的SECRET
     */
    public static final String APP_SECRET = "b69d6a4e4a75e2374fa7a8583006c466";


    /**
     * 把Map所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String preStr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                preStr = preStr + key + "=" + value;
            } else {
                preStr = preStr + key + "=" + value + "&";
            }
        }
        KLog.d("签名"+preStr);
        return preStr;
    }

    /**
     * 获取MC
     *
     * @param params
     * @return
     */
    public static String getMCSign(Map<String, String> params, String authTime) {
        //todo 后端暂时没有算其他参数，所以，参与验签的只有 这两个固定参数
        HashMap<String,String> newParams = new HashMap<>(2);
        newParams.put("authTime", authTime);
        //平台标识
        newParams.put("plateKey", APP_KEY);
        //加密sign
        String authSign = MD5(createLinkString(newParams) + MD5(APP_SECRET));
        params.put("authSign", authSign);
        KLog.d("签名"+authSign);
        return authSign;
    }

    /**
     * MD5加密
     *
     * @param sStr
     * @return String
     */
    private final static String MD5(String sStr) {
        String sReturnCode = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sStr.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer sb = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }

            sReturnCode = sb.toString();
        } catch (Exception ex) {

        }
        return sReturnCode;
    }

}
