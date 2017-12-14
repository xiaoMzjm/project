package com.zjm.project;

import javax.annotation.PostConstruct;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@ComponentScan("com.zjm.project")
@SpringBootApplication
@EnableScheduling
public class Virtualmachine3Application {

	public static void main(String[] args) {
		SpringApplication.run(Virtualmachine3Application.class, args);
	}

	@PostConstruct
	public void init() throws InterruptedException, NotFoundException, CannotCompileException, ClassNotFoundException {
		ClassPool cp = ClassPool.getDefault();
		CtClass cc = cp.get("com.zjm.project.User");
		CtMethod m = cc.getDeclaredMethod("getName");
		m.setBody("{return \"hello\";}");
		Class c = cc.toClass();
		cp.getClassLoader().loadClass("com.zjm.project.User");
		Class.forName("com.zjm.project.User");
	}

	@Scheduled(fixedRate=5000)
	public void init3() throws InterruptedException, NotFoundException, CannotCompileException, ClassNotFoundException {

		System.out.println("==================");
		//替换前，打印出 firstName.lastName
		//被替换后，打印lastName.firstName
		System.out.println(new User().getName());
	}

	//@Scheduled(fixedRate=5000)
	//public void init2() throws InterruptedException, Exception {
	//	try {
	//		HotreplaceLoader loader = new HotreplaceLoader();
	//		Class cls = loader.loadClass("com.zjm.project.test2.SimpleA");
	//		//Object aService = cls.newInstance();
	//		//Method m = aService.getClass().getMethod("add", new Class[]{int.class, int.class});
	//		//Object count = m.invoke(aService, new Object[]{1, 1});
	//		SimpleA as = (SimpleA)cls.newInstance();
	//		int count = as.add(1, 1);
	//		System.out.println(String.valueOf(count));
	//		try{
	//			Thread.sleep(1000);
	//		}catch(InterruptedException e){
	//			e.printStackTrace();
	//		}
	//	}catch (Exception e) {
	//		e.printStackTrace();
	//	}
	//}


}
