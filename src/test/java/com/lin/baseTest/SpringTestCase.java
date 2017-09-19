package com.lin.baseTest;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lin.model.user.User;
import com.lin.service.user.UserService;


//指定bean注入的配置文件  
@ContextConfiguration(locations = { "classpath*:spring/spring-dao.xml", "classpath*:spring/spring-service.xml" })  
//使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner  
@RunWith(SpringJUnit4ClassRunner.class)  
public class SpringTestCase {

	private static Logger logger = Logger.getLogger(SpringTestCase.class);  
	
	@Autowired  	
    private UserService userService; 

    @Test  
    public void selectUserByIdTest(){  
        //User user = userService.selectUserById(1L);  
    	
    	User loginUser = new User();
    	loginUser.setName("李四");
    	loginUser.setPassword("123456");
    	User user = userService.login(loginUser);
        System.out.println(ReflectionToStringBuilder.toString(user));
        System.out.println(user.getName() + ":" + user.getPassword());
        logger.info("User [userId=" + user.getId() + ", userName=" + user.getName()  
                + ", userPassword=" + user.getPassword() + "]");
    } 
}