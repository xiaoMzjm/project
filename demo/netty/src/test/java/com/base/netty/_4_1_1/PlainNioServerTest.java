package com.base.netty._4_1_1;

import org.junit.Test;

/**
 *
 * @author:小M
 * @date:2020/10/12 4:55 PM
 */
public class PlainNioServerTest {

    @Test
    public void init() throws Exception{

        PlainNioServer plainNioServer = new PlainNioServer();
        plainNioServer.create(8888);

        Thread.sleep(1000);

        new PlainNioClient().create("1");

        new PlainNioClient().create("2");

        Thread.sleep(100000);

    }



}
