package com.zjm;

import java.util.Objects;

/**
 * @author:ºÚ¾ø
 * @date:2017/8/15 0:20
 */
public enum Encode {
    UTF8 (new Integer(1).byteValue(),"UTF-8"),
    GBK (new Integer(2).byteValue(),"GBK"),
    ;

    private byte encode;
    private String name;

    Encode(byte encode, String name) {
        this.encode = encode;
        this.name = name;
    }

    public static Encode getEncodeByByte(byte encode){
        if(UTF8.getEncode() == encode) {
            return UTF8;
        }
        else {
            return GBK;
        }
    }

    public byte getEncode() {
        return encode;
    }

    public void setEncode(byte encode) {
        this.encode = encode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
