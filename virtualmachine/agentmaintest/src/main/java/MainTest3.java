import java.io.IOException;
import java.lang.reflect.Field;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

/**
 * @author:�ھ�
 * @date:2017/12/14 0:22
 */
public class MainTest3 {

    public static void main(String[] args)
        throws IllegalAccessException, NoSuchFieldException, IOException, AttachNotSupportedException,
        AgentLoadException, AgentInitializationException {
        System.setProperty("java.library.path", "D:\\Program Files\\Java\\jdk1.6.0_45\\jre\\bin");
        Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
        fieldSysPath.setAccessible(true);
        fieldSysPath.set(null, null);
        VirtualMachine vm = VirtualMachine.attach("175080");
        vm.loadAgent("D:/agent.jar", "om.zjm.module.User3|getName");
    }
}
