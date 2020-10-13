package com.base.rpc.serialize;

import com.base.rpc.protocol.Message;
import com.base.rpc.protocol.RpcRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器
 *
 * 对于服务来说，编码的是 Response
 * 对于客户端来说 ，编码的是 Request
 *
 * 所以不具体写死泛型
 *
 * @author:小M
 * @date:2020/10/13 12:22 AM
 */
public class MessageEncoder extends MessageToByteEncoder {

    private Class<?> clazz;
    private Serializer serializer;

    public MessageEncoder(Class<?> clazz, Serializer serializer) {
        this.clazz = clazz;
        this.serializer = serializer;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf byteBuf)
        throws Exception {
        if (clazz != null && clazz.isInstance(msg)) {
            byte[] bytes = serializer.serialize(msg);
            byteBuf.writeInt(bytes.length);
            byteBuf.writeBytes(bytes);
        }
    }
}
