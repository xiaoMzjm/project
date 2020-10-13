package com.base.netty._4_1_2;

import org.junit.Test;

/**
 * @author:小M
 * @date:2020/10/12 9:29 PM
 */
public class NettyNioServerTest {

    //@Test
    public void test() throws Exception{
        NettyNioServer nettyNioServer = new NettyNioServer();
        nettyNioServer.server();
    }
}
