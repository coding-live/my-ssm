package com.lin.controller.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SystemInterceptor implements HandlerInterceptor{
	
	@Autowired
	private HttpSession session;
	
	private List<String> excludeUrls;

	/**
	 * 返回值：表示我们是否需要将当前的请求拦截下来
	 * 如果返回false，请求将被终止
	 * 如果返回true，请求将继续执行
	 * Object arg2 表示的是被拦截的请求的目标对象
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("进入preHandle方法");
		
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		System.out.println(url);

		// 无需登录的请求
		if (excludeUrls.contains(url)) {
			System.out.println("进入无需登录请求");
			return true;
		}
		
		if (request.getSession().getAttribute("user") != null) {
			return true;
		} else {
			System.out.println("未登录跳转：" + url);
			request.getRequestDispatcher("/init").forward(request, response);
			return false;
		}
		// 针对ajax请求处理
		/*if (request.getHeader("X-Requested-With") != null) {
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			response.setHeader("url", basePath);
		} else {
			request.getRequestDispatcher("/user").forward(request, response);
		}
		return false;*/
		/*String requestType = request.getHeader("X-Requested-With");
		if (request != null) { // 获得所有请求参数名
			User user = (User) request.getSession().getAttribute("user");
			if (user == null) {
				if (!"XMLHttpRequest".equals(requestType)) {
					request.getRequestDispatcher("/").forward(request, response);
					return false;
				}
				return false;
			}
		}*/
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 可以通过ModelAndView参数来改变显示的视图，或修改发往视图的方法参数
		//modelAndView.addObject("postHandleMsg", "从postHandle方法返回是msg");
		System.out.println("进入postHandle方法");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("进入afterCompletion方法");
	}

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
}
