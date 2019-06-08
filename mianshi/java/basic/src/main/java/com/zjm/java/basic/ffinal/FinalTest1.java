package com.zjm.java.basic.ffinal;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

/**
 * @author:小M
 * @date:2019/5/14 11:49 PM
 */
public class FinalTest1 {

    int a;
    final int b;
    static FinalTest1 finalTest1;

    public FinalTest1() {
        a = 1;  // 对普通域写入
        b = 2;  // 对final域的写入
    }

    public static void main(String[] args) throws Exception {

        finalTest1 = new FinalTest1();

        // 以下两行代码可能被重排序
        new Thread(new Runnable() {
            public void run() {
                FinalTest1 o = finalTest1;
                // a可能是0，原因可能不是因为a被放到了构造器外被执行了，
                // 而是因为FinalTest1 o = finalTest1;被放到了int a = 0.a下面执行了
                int a = o.a;

                // b一定被初始化成2了，因为o.b是final的，
                // 所以FinalTest1 o = finalTest1;一定比int b = o.b; 先执行
                int b = o.b;
            }
        }).start();
    }
}
