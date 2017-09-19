<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setAttribute("scheme", request.getScheme());
	request.setAttribute("serverName", request.getServerName());
	request.setAttribute("serverPort", request.getServerPort());
	request.setAttribute("contextPath", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome!</title>
</head>
<body>
	<h1>登录成功，欢迎你</h1>
	<p>用户名： ${user.name}</p>
	<p>密码：${user.password}</p>
	<p>后台返回的msg：${msg}</p>
	<p>页面使用的协议：${scheme}</p>
	<p>页面所在服务器名称：${serverName}</p>
	<p>页面所在的服务器使用的端口：${serverPort}</p>
	<p>页面所在应用名称：${contextPath}</p>
	<a href="${basePath}/borrow/borrowsInit">前往投资</a><br>
	<a href="${basePath}/user/modifyPayPasswordInit">修改支付密码</a><br>
	<a href="<%=basePath%>/user/rechargeInit">充值</a><br>
	<a href="<%=basePath%>/logout">退出</a>
</body>
</html>