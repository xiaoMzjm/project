package com.zjm;

/**
 * @author:ºÚ¾ø
 * @date:2017/8/15 0:47
 */
public class ByteUtil {

    public static int bytes2Int(byte[] bytes) {
        int num = bytes[3] & 0xFF;
        num |= ((bytes[2] << 8) & 0xFF00);
        num |= ((bytes[1] << 16) & 0xFF0000);
        num |= ((bytes[0] << 24) & 0xFF000000);
        return num;
    }

    public static byte[] int2Bytes(int num) {
        byte[] result = new byte[4];
        result[0] = (byte)((num >> 24 & 0XFF));
        result[1] = (byte)((num >> 16 & 0XFF));
        result[2] = (byte)((num >> 8 & 0XFF));
        result[3] = (byte)((num  & 0XFF));
        return result;
    }
}
