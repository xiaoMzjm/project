package com.base.netty._4_1_1;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author:小M
 * @date:2020/10/12 5:40 PM
 */
public class ChannelUtil {

    public static String readChannelStr(SocketChannel socketChannel) throws Exception{
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        StringBuilder sb = new StringBuilder();

        int readLength = 0;
        byte[] bytes;

        while((readLength = socketChannel.read(buffer)) > 0 ){
            // 锁定Buffer空白区域（把limit设置为position）
            buffer.flip();

            bytes = new byte[readLength];
            buffer.get(bytes);

            // 转成字符串
            sb.append(new String(bytes));

            // (把limit设置为capacity)
            buffer.clear();

        }

        return sb.toString();
    }
}
