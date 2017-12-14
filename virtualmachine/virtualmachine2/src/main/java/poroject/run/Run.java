package poroject.run;

import poroject.client.User;

/**
 * @author:黑绝
 * @date:2017/12/11 23:13
 */
public class Run {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            //替换前，打印出 firstName.lastName
            //被替换后，打印lastName.firstName
            System.out.println(new User("firstName","lastName").getName());
            Thread.sleep(5000);
        }
    }
}
