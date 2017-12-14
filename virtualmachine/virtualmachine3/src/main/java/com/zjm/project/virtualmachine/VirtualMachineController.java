package com.zjm.project.virtualmachine;

import java.lang.reflect.Field;

import com.sun.tools.attach.VirtualMachine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:黑绝
 * @date:2017/12/12 10:19
 */
@Controller
public class VirtualMachineController {

    // http://localhost/attach/11720
    @RequestMapping("/attach/{pid}")
    @ResponseBody
    public String attach(@PathVariable String pid) throws  Exception{
        try {
            //注意，是jre的bin目录，不是jdk的bin目录
            //VirtualMachine need the attach.dll in the jre of the JDK.
            System.setProperty("java.library.path", "D:/Program Files/Java/jdk1.8.0_121/jre/bin");
            Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);

            //目标进程的进程id -- 记得改成正确的数字
            VirtualMachine vm = VirtualMachine.attach(pid);

            //参数1：代理jar的位置
            //参数2：代理类的类名
            vm.loadAgent("C:\\Users\\jiaming.zjm\\Desktop\\agent.jar", "D:\\User.class");
            return "success";
        }catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    @RequestMapping("/")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
