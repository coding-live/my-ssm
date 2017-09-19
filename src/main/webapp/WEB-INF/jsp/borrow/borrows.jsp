<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投资页面</title>
</head>
<body>
<div style="text-align: center;">请输入投标金额：<input type="text" id="investAmount" name="investAmount" value=""></div>
<div style="text-align: center;"><a href="${basePath}/borrow/export">导出记录</a><br></div>
<table width="50%" align="center">
	<tbody>
		<tr>
		    <th>项目序号</th>
		    <th>项目标题</th>
		    <th>借款金额</th>
		    <th>已投金额</th>
		    <th>剩余金额</th>
		    <th>操作</th>
		</tr>
		<c:forEach items="${borrows}" var="borrow" varStatus="s">
			<tr align="center">
				<td>${s.index + 1}</td>
				<td>${borrow.borrowName}</td>
				<td>${borrow.borrowAmount}</td>
				<td>${borrow.hasInvestAmount}</td>
				<td>${borrow.borrowAmount - borrow.hasInvestAmount}</td>
				<td>
					<c:choose>
						<c:when test="${borrow.borrowAmount == borrow.hasInvestAmount }">
							满标
						</c:when>
						<c:otherwise>
							<a href="javascript:void(0);" onclick="invest('${borrow.id}')">投资</a>	
						</c:otherwise>
					</c:choose>
					
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
<script type="text/javascript" src="${basePath}/js/common/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function invest(borrowId) {
		var investAmount = $("#investAmount").val();
		$.ajax({
			type: "POST",
			url: '<%=basePath%>/borrow/invest',
	    	data: {investAmount:investAmount,borrowId:borrowId,reqDate:new Date()},
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