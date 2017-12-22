
<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<%-- <script type="text/javascript" src="${ctx }/static/jquery-3.2.1.slim.min.js"></script> --%>
<body>
	
	<div id="msg">
	</div>
	
	<br>
	
	<div>
		<input type="button" value="登录" onclick="location='${ctx}/apis/login'" />
	</div>

	<br>
	<hr>

	<div>
		车辆位置查询：
		<form action="${ctx }/apis/vLastLocationV3">
			车牌号：
			<input type="text" name="vcln" placeholder="陕YH0009"/>
			<input type="submit" value="提交" />
		</form>
	</div>

	<br>
	<hr>

	<div>
		车辆驶入驶出 区域订阅
		<form action="${ctx }/apis/areaReg">
			<input type="hidden" name="type" value="1" />
			<table>
				<tr>
					<td>事件类型：</td>
					<td>
						<label><input type="radio" name="actionType" value="1" checked="checked"/> 进区域通知</label>
						<label><input type="radio" name="actionType" value="2" /> 出区域通知</label>
					</td>
				</tr>
				<tr>
					<td>区域名称：</td>
					<td><input type="text" name="areaName" /></td>
					<td>经度：</td>
					<td><input type="text" name="lon" /></td>
					<td>维度：</td>
					<td><input type="text" name="lat" placeholder="" /></td>
					<td>半径：</td>
					<td><input type="text" name="radius" placeholder="半径（米）" /></td>
				</tr>
				<tr>
					<td colspan="8">
						<input type="submit" value="提交" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<br>
	<hr>
	
	<div>
		车辆驶入驶出 车辆订阅
		<form action="${ctx }/apis/vnoReg">
			车牌号：
			<input type="text" name="vnos" placeholder="京A12345_1(1蓝色，2黄色)" />
			区域ID：
			<input type="text" name="areaId" />
			<input type="submit" value="提交">
		</form>
	</div>
	
	<br>
	<hr>

</body>
</html>
