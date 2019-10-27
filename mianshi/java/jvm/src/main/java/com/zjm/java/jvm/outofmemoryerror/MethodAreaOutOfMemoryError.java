package com.zjm.java.jvm.outofmemoryerror;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodAreaOutOfMemoryError {

    public static class TestClass {

        private void hi(){

        }

    }

    /**
     * 当前文件目录执行：
     * javac MethodAreaOutOfMemoryError.java
     *
     * 根目录执行：
     * java -XX:PermSize=10M -XX:MaxPermSize=10M com.zjm.java.jvm.outofmemoryerror.MethodAreaOutOfMemoryError
     *
     * @param args
     */
    public static void main(String[] args) {

        for(int i = 0 ; i < 1000 ; i ++) {

            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(TestClass.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {

                @Override
                public Object intercept(Object obj, Method arg1, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });

            TestClass oom = (TestClass) enhancer.create();
        }
    }
}
