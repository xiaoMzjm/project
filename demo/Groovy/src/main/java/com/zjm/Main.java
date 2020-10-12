package com.zjm;

import java.util.HashMap;
import java.util.Map;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * @author:黑绝
 * @date:2017/8/19 16:05
 */
public class Main {

    private static GroovyShell groovyShell = new GroovyShell(new Binding());

    public static void 直接运行一段小脚本(){
        String script = "System.out.println(\"success\");";
        Object value = groovyShell.evaluate(script);
    }


    public static void 脚本中有类和方法(){
        String script = "class Example {\n"
                    +   "   static void main(String[] args) {\n"
                    +   "      println('Hello World');\n"
                    +   "   }\n"
                    +   "}";
        Object value = groovyShell.evaluate(script);
    }

    public static void 定义变量(){
        String script = "";
        script += "def a = 1;";
        script += "println(a);";
        Object value = groovyShell.evaluate(script);
    }

    public static void 定义内置变量(){
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
        Main.直接运行一段小脚本();
        Main.脚本中有类和方法();
        Main.定义变量();
        Main.定义内置变量();
    }
}
