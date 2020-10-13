package com.base.rpc.serialize;

import java.util.List;

import com.base.rpc.protocol.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.ReplayingDecoder;

/**
 * @author:小M
 * @date:2020/10/13 12:10 AM
 */
public class MessageDecoder extends ByteToMessageDecoder{

    private Class<?> clazz;
    private Serializer serializer;

    public MessageDecoder(Class<?> clazz, Serializer serializer) {
        this.clazz = clazz;
        this.serializer = serializer;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list)
        throws Exception {

        // 因为之前编码的时候写入一个Int型，4个字节来表示长度
        if (byteBuf.readableBytes() < 4) {
            return;
        }

        // 标记当前读的位置，如果下一个可读字节非dataLength长度，则不解析，重置可读点，返回
        byteBuf.markReaderIndex();
        int dataLength = byteBuf.readInt();
        if (byteBuf.readableBytes() < dataLength) {
            byteBuf.resetReaderIndex();
            return;
        }

        byte[] data = new byte[dataLength];
        // 将byteBuf中的数据读入data字节数组
        byteBuf.readBytes(data);
        Object obj = serializer.deserialize(clazz, data);
        list.add(obj);
    }

}
