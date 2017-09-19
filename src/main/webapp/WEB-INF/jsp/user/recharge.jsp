<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>充值</title>
</head>
<body>
	<h1> ${user.name},你的账户余额：${user.balance}</h1>

	<div>
		请输入充值金额：<input type="text" id="balance" name="balance" value="">
	</div>

	<input type="button" onclick="recharge();" value="提交">
	
</body>
<script type="text/javascript" src="${basePath}/js/common/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function recharge() {
		var balance = $("#balance").val();
		$.ajax({
			type: "POST",
			url: '<%=basePath%>/user/recharge',
	    	data: {balance:balance,reqDate:new Date()},
			dataType:'json',
			cache: false,
			success: function(data){
				if(data.result){
					alert(data.msg);
					window.location.reload();
				} else {
					alert(data.msg);
				}
			}
		});
	}
</script>
</html>