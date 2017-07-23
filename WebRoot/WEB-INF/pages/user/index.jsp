<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>用户管理中心</title>
    <meta charset="utf-8"/>
	<%@ include file="/WEB-INF/common/common.jsp" %>
	<style type="text/css">
		html,body{width:100%;height:100%;background:url(${basePath}/images/body1.jpg) no-repeat;background-size:100% 100%;}
		/* 头部 */
	  	.header{width:100%;height:59px;background:#e5e5e5}
	  	.header .logo{font-size: 36px;width: 570px;margin: 0 auto;line-height: 50px;color: #999;font-family: 宋体;font-weight: 700;text-align: center;}
	  	#tz_usermessagebox{display:inline-block;}
	  	.sgl_items{padding-left:100px;}
		.sgl_items a{color:#999;font-size:12px;}
		
		/* gmcontent S */
		.gmcontent{width:100%;height:100%;}
		.gmcontent .gm-left{width:20%;height:100%;box-shadow:0 0 5px #666;float:left;}
		.gmcontent .gm-left ul{width:100%;height:100%;}
		.gmcontent .gm-left ul li{width:100%;height:30px;background:#3A93FF;margin-bottom:2px;}
		.gmcontent .gm-left ul li a{display:block;width:100%;height:100%;text-align:center;line-height:30px;color:#fff;}
		.gmcontent .gm-left ul li a:hover{background:#fff;color:#333;}
		.gmcontent .gm-right{width:80%;height:100%;float:right;}
		#iframe{width:100%;height:100%;}
		/* E gmcontent */
	</style>
  </head>
  
  <body>
  	<!-- 头部 -->
 	<%@ include file="/WEB-INF/common/header.jsp" %>
 	<!-- 内容 gmcontent S -->
    <div class="gmcontent">
    	<div class="gm-left">
    		<ul>
    			<li><a href="${basePath }/user/userSys" target="right">用户管理</a></li>
    			<li><a href="${basePath }/user/PsdUpdate" target="right">密码修改</a></li>
    		</ul>
    	</div>
    	<div class="gm-right">
    		<iframe id="iframe" name="right"></iframe>
    	</div>
    </div>
    <!-- E gmcontent -->
  </body>
</html>
