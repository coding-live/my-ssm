<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改支付密码</title>
</head>
<body>
	
	<div id = "setOldPayPasswordDiv" <c:if test="${isNewUser }">style="display: none;"</c:if>>
		请输入原支付密码：<input type="text" id="oldPayPassword" name="oldPayPassword" value="">
	</div>

	<div id = "setPayPasswordDiv">
		请输入新支付密码：<input type="text" id="newPayPassword" name="newPayPassword" value="">
	</div>
	<div id = "truePayPasswordDiv">
		请确认新支付密码：<input type="text" id="truePayPassword" name="truePayPassword" value="">
	</div>
	
	<input type="button" onclick="modifyPayPassword();" value="提交">
	
</body>
<script type="text/javascript" src="${basePath}/js/common/jquery-1.8.3.js"></script>
<script type="text/javascript">


	// 获取项目根路径
	<%-- $(function(){ 
		var basePath = '<%=basePath%>';
		alert(basePath); 
	});  --%>
	
	function modifyPayPassword() {
		var oldPayPassword = $("#oldPayPassword").val();
		var newPayPassword = $("#newPayPassword").val();
		var truePayPassword = $("#truePayPassword").val();
		$.ajax({
			type: "POST",
			url: '<%=basePath%>/user/modifyPayPassword',
	    	data: {oldPayPassword:oldPayPassword,newPayPassword:newPayPassword,truePayPassword:truePayPassword},
			dataType:'json',
			cache: false,
			success: function(data){
				if(data.result){
					alert(data.msg);
					location.href = "${basePath}/account";
				} else {
					alert(data.msg);
				}
			}
		});
	}
</script>
</html>