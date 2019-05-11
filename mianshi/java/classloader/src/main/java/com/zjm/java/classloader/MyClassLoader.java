package com.zjm.java.classloader;

import java.io.InputStream;

import sun.applet.AppletClassLoader;

/**
 * @author:小M
 * @date:2019/5/11 1:03 PM
 */
public class MyClassLoader {

    //public static void main(String[] args) throws Exception{
    //
    //    ClassLoader classLoader = new ClassLoader() {
    //        @Override
    //        public Class<?> loadClass(String name) throws ClassNotFoundException {
    //            try {
    //                System.out.println("name="+name);
    //                String fileName = name.substring(name.lastIndexOf("."),name.length())+".class";
    //                InputStream is = getClass().getResourceAsStream(fileName);
    //                if(is == null) {
    //                    return super.loadClass(name);
    //                }
    //                byte[] bytes = new byte[is.available()];
    //                is.read(bytes);
    //                return defineClass(name , bytes , 0 , bytes.length);
    //            }catch (Exception e) {
    //                return super.loadClass(name);
    //            }
    //        }
    //    };
    //
    //    Object o = classLoader.loadClass("com.zjm.java.classloader.MyClassLoader").newInstance();
    //}

    public static void main(String[] args) throws Exception{

        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> findClass(String name) throws ClassNotFoundException {
                try {
                    System.out.println("name="+name);
                    String fileName = name.substring(name.lastIndexOf("."),name.length())+".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(name , bytes , 0 , bytes.length);
                }catch (Exception e) {
                    return super.loadClass(name);
                }
            }
        };

        Object o = classLoader.loadClass("com.zjm.java.classloader.MyClassLoader").newInstance();
    }
}
