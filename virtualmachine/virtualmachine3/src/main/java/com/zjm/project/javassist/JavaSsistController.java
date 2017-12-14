package com.zjm.project.javassist;

import java.io.IOException;

import com.zjm.project.User;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:黑绝
 * @date:2017/12/12 11:53
 */
@Controller
public class JavaSsistController {

    /**
     * localhost:7001/sist
     * @return
     * @throws NotFoundException
     * @throws CannotCompileException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @RequestMapping("/sist")
    @ResponseBody
    public String change()
        throws NotFoundException, CannotCompileException, IOException, IllegalAccessException, InstantiationException,
        ClassNotFoundException {

        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.zjm.project.User");
        CtMethod m = cc.getDeclaredMethod("getName");
        m.setBody("{return \"hello\";}");
        Class c = cc.toClass();
        cp.getClassLoader().loadClass("com.zjm.project.User");
        Class.forName("com.zjm.project.User");
        return "success";
    }
}
