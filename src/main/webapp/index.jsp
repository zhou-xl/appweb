
<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<body>
	
	返回信息：
	<div id="msg">
	</div>
	
	<div>
		<input type="button" value="登录" onclick="login()" />
	</div>

	<div>
		<form action="/appweb/apis/areaReg">
			<input type="hidden" name="type" value="1" />
			<input type="hidden" name="actionType" value="1" />
			<table>
				<tr>
					<td>区域名称：</td>
					<td><input type="text" name="areaName" /></td>
					<td>经度：</td>
					<td><input type="text" name="lon" /></td>
					<td>维度：</td>
					<td><input type="text" name="lat" /></td>
					<td>半径：</td>
					<td><input type="text" name="radius" /></td>
				</tr>
			</table>
			<input type="submit" value="提交" />
		</form>
	</div>
	
	<div>
		<form action="/appweb/apis/vnoReg">
			车牌号：
			<input type="text" name="vnos" required="京A12345_1(1蓝色，2黄色)" />
			区域ID：
			<input type="text" name="areaId" />
			<input type="submit" value="提交">
		</form>
	</div>

<script type="text/javascript">

function login(){
	$.post("/appweb/apis/login" ,{} ,function (msg){
		$("#msg").html(msg);
	})
}

</script>
</body>
</html>
