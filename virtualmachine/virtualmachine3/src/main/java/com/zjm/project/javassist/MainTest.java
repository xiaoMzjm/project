package com.zjm.project.javassist;

import java.io.IOException;

import com.zjm.project.User;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * @author:黑绝
 * @date:2017/12/12 11:56
 */
public class MainTest {

    public static void main(String[] args)
        throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException, IOException {
        //User user = new User();
        //System.out.println(user.getName());

        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.zjm.project.User");
        CtMethod m = cc.getDeclaredMethod("getName");
        m.setBody("{return \"hello\";}");
        Class c = cc.toClass();
        //cc.writeFile("D:\\redefclass");
        User newUser = (User) c.newInstance();
        System.out.println(newUser.getName());
    }
}
