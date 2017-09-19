package com.lin.controller.system;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lin.model.user.User;
import com.lin.service.user.UserService;

@Controller
public class IndexController {

	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping("/init")
	public String init() {
		System.out.println("进入index方法");
		//User user = (User) session.getAttribute("user");
		/*if(user == null){
			return "login";
		}else{
			// redirect重定向，不同Controller间跳转路径格式为：redirect:/路径/XX
			return "redirect:account"; 	
		}*/
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User loginUser,Model model) {
		System.out.println("进入login方法");
		User user = userService.login(loginUser);
		if(user == null){
			return "login";
		}
		session.setAttribute("user", user);
		model.addAttribute("user", user);
		model.addAttribute("msg", "从login方法返回的msg参数");
		return "welcome";  
		
	}
	
	@RequestMapping(value = "/login2", method = RequestMethod.GET)
	public ModelAndView login2() {
		System.out.println("进入login2方法");
		ModelAndView mav = new ModelAndView();   
		User loginUser = new User();
		loginUser.setName("李四");
    	loginUser.setPassword("123456");
		User user = userService.login(loginUser);
	    mav.addObject("user", user);   
	    mav.setViewName("welcome");
	    return mav;
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(Model model) {
		System.out.println("进入account方法");
		//查询用户状态,根据用户状态设置信息
		User user = (User) session.getAttribute("user");
		if(user == null ){
			return "login";
		} else {
			model.addAttribute("user", user);
		}
		return "welcome";  	
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(Model model) {
		return "user/account";  	
	}
	
	/**
	 * 退出系统
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		session.removeAttribute("user");
		return "login"; 
	}
}
