import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemoTestCase  {

    @Test
    public void testGetUserDao() {


        //工厂模式
        //默认情况下，Spring容器中管理的类将会变成单例类，类的对象会在容器启动时创建
        //懒汉 饿汉

        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        UserService userService = (UserService) context.getBean("userService");
        userService.sayHi();


        /*UserDao userDao = (UserDao) context.getBean("userDao");
        userDao.sayHello();*/

        /*UserDao userDao2 = (UserDao) context.getBean("userDao");

        System.out.println(userDao == userDao2);*/


    }


}
