import com.kaishengit.dao.UserDao;
import com.kaishengit.dao.UserDaoImpl2;
import com.kaishengit.service.BookService;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemoTestCase  {

    @Test
    public void testGetUserDao() {

        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        UserService userService = (UserService) context.getBean("userService");

        userService.sayHi();

    }


}
