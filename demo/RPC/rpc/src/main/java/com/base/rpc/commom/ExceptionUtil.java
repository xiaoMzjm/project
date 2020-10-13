package com.base.rpc.commom;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

/**
 * @author:小M
 * @date:2020/10/13 8:31 PM
 */
public class ExceptionUtil {

    public static String getErrorMsg(Exception e) {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        e.printStackTrace(new PrintWriter(buf, true));
        return buf.toString();
    }
}
