package agent;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.net.URL;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;


/**
 * @author:黑绝
 * @date:2017/12/11 23:15
 */
public class MyAgent {

	/**
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中
     * 并被同一个System ClassLoader装载
     * 被统一的安全策略(security policy)和上下文(context)管理
     *
     * @param agentOps
     * @param inst
     * @author SHANHY
     * @create  2016年3月30日
     */
    public static void premain(String agentOps, Instrumentation inst) {
        System.out.println("=========premain方法执行开始========");
        System.out.println("agentOps="+agentOps);
        
        try {
        	ClassPool classPool = ClassPool.getDefault();
        	String[] strArray = agentOps.split("|");
        	String cn = strArray[0];
        	String mn = strArray[1];
        	CtClass ctClass = classPool.get(cn);
        	CtMethod ctMethod = ctClass.getDeclaredMethod(mn);
        	String[] paramsName = getMethodParamNames(ctMethod);
        	if(paramsName != null) {
        		System.out.println(paramsName);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        System.out.println("--------------------");
        
        try {
        	URL url = Thread.currentThread().getContextClassLoader().getResource("newClass/");
        	String[] filesPath = new File(url.getFile()).list();
        	for(String filePath : filesPath) {
                File file = new File(url.getFile() + "\\" + filePath);
                String className = filePath.substring(0 , filePath.lastIndexOf("."));
                byte[] reporterClassFile = new byte[(int) file.length()];
                DataInputStream in = new DataInputStream(new FileInputStream(file));
                in.readFully(reporterClassFile);
                in.close();
                ClassDefinition reporterDef = new ClassDefinition(Class.forName(className), reporterClassFile);
                inst.redefineClasses(reporterDef);
                System.out.println("替换类:" + className + " 成功");
    		}
        	
        	
        	
//        	//把新的User类文件的内容读出来
//        	System.out.println(1);
//            File f = new File(Thread.currentThread().getContextClassLoader().getResource("newClass/IpConstants.class").getPath());
//            System.out.println(2);
//            byte[] reporterClassFile = new byte[(int) f.length()];
//            System.out.println(3);
//            DataInputStream in = new DataInputStream(new FileInputStream(f));
//            System.out.println(4);
//            in.readFully(reporterClassFile);
//            System.out.println(5);
//            in.close();
//            System.out.println(6);
//            //把User类的定义与新的类文件关联起来
//            System.out.println(7);
//            ClassDefinition reporterDef = new ClassDefinition(Class.forName("com.cainiao.boson.constants.IpConstants"), reporterClassFile);
//            System.out.println(8);
//            //重新定义User类， 妈呀， 太简单了
//            inst.redefineClasses(reporterDef);
//            System.out.println(9);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        System.out.println("=========premain方法执行完毕========");
    }
    
    protected static String[] getMethodParamNames(CtMethod cm) throws NotFoundException {
        CtClass cc = cm.getDeclaringClass();
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
                .getAttribute(LocalVariableAttribute.tag);
        if(attr == null) {
        	return null;
        }
        String[] paramNames = null;
        paramNames = new String[cm.getParameterTypes().length];
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < paramNames.length; i++) {
            paramNames[i] = attr.variableName(i + pos);
        }
        return paramNames;
    }
    

    /**
     * 如果不存在 premain(String agentOps, Instrumentation inst) 
     * 则会执行 premain(String agentOps)
     *
     * @param agentOps
     * @author SHANHY
     * @create  2016年3月30日
     */
    public static void premain(String agentOps) {
        System.out.println("=========premain方法执行2========");
        System.out.println(agentOps);
    }

}
