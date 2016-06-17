package spring;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.entity.User;
import com.spring.service.UserService;

public class UserDaoTest {

	private ApplicationContext act;

	@Before
	public void before(){
		act = new ClassPathXmlApplicationContext("beans.xml");
	}
	
	@After
	public void after(){
		act=null;
	}
	
	@Test
	public void saveUser() {
		UserService userService=(UserService) act.getBean("userService");
		User user=new User();
		user.setAddress("贵阳");
		user.setUserName("张三");
		user.setEmail("21113412@qq.com");
		user.setPassword("123");
		user.setSex('1');
		userService.saveEntity(user);
	}
	
	@Test
	public void deleteUser(){
		UserService userService=(UserService) act.getBean("userService");
		User user=new User();
		user.setId(1);
		userService.deleteEntity(user);
	}
	
	@Test
	public void queryAll(){
		UserService userService=(UserService) act.getBean("userService");
		List<User> users = userService.queryAll();
		System.out.println(users.size());
		for(User user:users){
			System.out.println(user);
		}
	}
	
	@Test
	public void queryMap(){
		UserService userService=(UserService) act.getBean("userService");
		List<Map<String, Object>> users = userService.queryMap();
		System.out.println(users.size());
		for(Map<String, Object> user:users){
			System.out.println(user);
		}
	}
	
	@Test
	public void queryEntity(){
		UserService userService=(UserService) act.getBean("userService");
		String sql="select * from users where id=?";
		User user = userService.queryEntity(sql, 5);
		System.out.println(user);
	}
	
}
