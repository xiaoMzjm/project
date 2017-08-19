package com.zjm;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * @author:�ھ�
 * @date:2017/8/19 16:05
 */
public class Main {

    private static GroovyShell groovyShell = new GroovyShell(new Binding());

    public static void ֱ������һ��С�ű�(){
        String script = "System.out.println(\"success\");";
        Object value = groovyShell.evaluate(script);
    }


    public static void �ű�������ͷ���(){
        String script = "class Example {\n"
                    +   "   static void main(String[] args) {\n"
                    +   "      println('Hello World');\n"
                    +   "   }\n"
                    +   "}";
        Object value = groovyShell.evaluate(script);
    }

    public static void �������(){
        String script = "";
        script += "def a = 1;";
        script += "println(a);";
        Object value = groovyShell.evaluate(script);
    }

    public static void main(String[] args) {
        Main.ֱ������һ��С�ű�();
        Main.�ű�������ͷ���();
        Main.�������();
    }
}
