package com.zjm;

import java.util.HashMap;
import java.util.Map;
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

    public static void �������ñ���(){
        Binding binding = new Binding();
        Map<String,Object> context = new HashMap<String,Object>();
        context.put("systemName" , "linux");
        binding.setVariable("$context" , context);
        GroovyShell groovyShell = new GroovyShell(binding);

        String script = "println($context.get('systemName'));";
        groovyShell.evaluate(script);

        String script2 = "println($context.systemName);";
        groovyShell.evaluate(script2);
    }

    public static void main(String[] args) {
        Main.ֱ������һ��С�ű�();
        Main.�ű�������ͷ���();
        Main.�������();
        Main.�������ñ���();
    }
}
