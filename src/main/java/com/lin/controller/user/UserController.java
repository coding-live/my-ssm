package com.lin.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lin.model.user.User;
import com.lin.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/modifyPayPasswordInit", method = RequestMethod.GET)
	public String modifyPayPasswordInit(Model model) {
		System.out.println("进入modifyPayPasswordInit方法");
		User user = (User) session.getAttribute("user");
		/*if(user == null) {
			return "redirect:/init";
		} */
		if(StringUtils.isBlank(user.getPayPassword())) {
			model.addAttribute("isNewUser", true);
		} else {
			model.addAttribute("isNewUser", false);
		} 	
		return "user/password_edit";  
	}
	
	
	@RequestMapping(value = "/modifyPayPassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyPayPassword(String oldPayPassword, String newPayPassword, 
			String truePayPassword) {
		System.out.println("进入modifyPayPassword方法");
		User user = (User) session.getAttribute("user");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("result", false);
		if(user == null) {
			map.put("msg", "用户未登录");
			return map;
		} 
		if(!StringUtils.isBlank(oldPayPassword) 
				&& oldPayPassword.equals(userService.selectUserById(user.getId()).getPayPassword())) {
			return map;
		} 
		if (StringUtils.isBlank(newPayPassword) || StringUtils.isBlank(truePayPassword)) {
			map.put("msg", "密码不能为空");
		} else {
			if(newPayPassword.equals(truePayPassword)) {
				user.setPayPassword(newPayPassword);
				if(userService.modifyPayPassword(user)) {
					map.put("result", true);
					map.put("msg", "支付密码设置成功");
				} else {
					map.put("result", false);
					map.put("msg", "支付密码设置失败，请重试");
				}
			} else {
				map.put("msg", "两次输入的密码不相同");
			}
		}
		return map;
	}
	
	
	@RequestMapping(value = "/rechargeInit", method = RequestMethod.GET)
	public String rechargeInit(Model model) {
		System.out.println("进入rechargeInit方法");
		User account = userService.selectUserById(((User) session.getAttribute("user")).getId());
		model.addAttribute("user", account);
		return "user/recharge";  
	}
	
	@RequestMapping(value = "/recharge", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> recharge(double balance) {
		System.out.println("进入recharge方法");
		User user = (User) session.getAttribute("user");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("result", false);
		if(user == null) {
			map.put("msg", "用户未登录");
			return map;
		} 
		if(balance > 0) {
			if(userService.recharge(user, balance)) {
				System.out.println("充值成功");
				map.put("result", true);
				map.put("msg", "充值成功");
			} else {
				System.out.println("充值失败");
				map.put("msg", "充值失败，请重试");
			}
		} else {
			map.put("msg", "充值失败，充值金额不能为0");
		}
		return map;  
	}
}
