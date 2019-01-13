package com.zjm.wxlogin.constant;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author:小M
 * @date:2019/1/13 10:54 PM
 */
public class WxLoginVerifyUtil {

    public static void isTrue(Boolean b , WxLoginExceptionEnums e , Object... args) throws Exception {
        if(b == null || b == false) {
            StringBuilder errorMsg = new StringBuilder();
            errorMsg.append(e.getMsg());
            if(args != null && args.length > 0) {
                for(Object arg : args) {
                    if(arg != null) {
                        errorMsg.append(",").append(String.valueOf(arg));
                    }
                }
            }
            throw new WxLoginExcpetion(errorMsg.toString());
        }
    }

    /**
     * 非空
     * @param o
     * @param e
     * @param args
     * @throws Exception
     */
    public static void isNotNull(Object o , WxLoginExceptionEnums e , Object... args) throws Exception{
        if(o == null) {
            isTrue(Boolean.FALSE , e , args);
        }
    }

    /**
     * 非空 & 长度非0
     * @param o
     * @param e
     * @param args
     * @throws Exception
     */
    public static void isNotEmpty(Object o , WxLoginExceptionEnums e, Object... args) throws Exception{
        if(o == null) {
            isTrue(Boolean.FALSE , e , args);
        }
        if(o instanceof String) {
            String s = (String)o;
            if(StringUtils.isEmpty(s)) {
                isTrue(Boolean.FALSE , e , args);
            }
        }
        if(o instanceof Collection) {
            Collection c = (Collection)o;
            if(CollectionUtils.isEmpty(c)) {
                isTrue(Boolean.FALSE , e , args);
            }
        }
        if(o.getClass().isArray()) {
            Object[] os = (Object[])o;
            if(ArrayUtils.isEmpty(os)){
                isTrue(Boolean.FALSE , e , args);
            }
        }
    }
}
