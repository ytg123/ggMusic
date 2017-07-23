<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--炫彩带-->
<div id="xcd">
	<span class="move"></span>
	<span class="move d1"></span>
	<span class="move d2"></span>
</div>
<div class="header">
	<div class="logo">
		<span>
			<a href="${basePath}/list">静...</a>
		</span>
		<div id="tz_usermessagebox">
			<c:if test="${empty user}">
				<span class="sgl_items"><a href="${basePath}/resg">注册</a>&nbsp;<a href="${basePath}/login">登陆</a></span>
			</c:if>
		
			<c:if test="${not empty user}">
				<span class="sgl_items">
				<img alt="${user.username}" data-address="${user.address }" id="infoUser" src="/${user.headerPic}" width="30" height="30" style="border-radius:50%;"/>
				 <a href="${basePath }/user/index">【${user.username}】</a><a href="${basePath}/logout">【退出】</a>
				</span>
			</c:if>
		</div>
	</div>
</div>