import com.zjm.module.User1;
import com.zjm.module.User2;
import com.zjm.module.User3;

public class MainTest {
	
	public static void main(String[] args) {

	    System.out.println(User1.staticField);
	    System.out.println(User1.staticMethod());
	    System.out.println(new User1().classField);
		System.out.println(new User1().getName());
		System.out.println(new User2().getName());
		System.out.println(new User3().getName("test_params"));
	}

}
