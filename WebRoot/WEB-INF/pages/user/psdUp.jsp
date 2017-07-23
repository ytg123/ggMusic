<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
	<meta charset="UTF-8">
	<title>用户管理</title>
	<%@ include file="/WEB-INF/common/common.jsp" %>
	<link rel="stylesheet" type="text/css" href="${basePath }/css/bootstrap.min.css"/>
	<script type="text/javascript" src="${basePath }/js/bootstrap.min.js"></script>
	<style type="text/css">
		#formUser{margin-top:50px;}
	</style>
  </head>
  
  <body>
  	<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<form class="form-horizontal" id="formUser">
	            		  <input type="hidden" id="uid" value="${userM.id }" name="id"/>
						  <div class="form-group">
						    <label>原始密码:</label>
						    <input type="password" class="form-control" id="password" name="password" placeholder="请输入原始密码" />
						  </div>
						  <div class="form-group">
						    <label>新密码:</label>
						    <input type="password" class="form-control" id="passwordNew" name="password" placeholder="请输入手机"/>
						  </div>
						  <div class="form-group">
						    <label>确认新密码:</label>
						    <input type="password" class="form-control" id="passwordNew2" name="password" placeholder="请输入年龄"/>
						  </div>
						  <input type="button" class="btn btn-primary btn-md" onclick="updatePsd()" value="确认修改"/>
					</form>
				</div>
			</div>
	</div>
  <script type="text/javascript">
  		//修改密码
  		function updatePsd(){
  			var oldPsd = $("#password").val(),
  			newPsd = $("#passwordNew").val(),
  			newPsdN = $("#passwordNew2").val();
	  		if(isEmpty(oldPsd)){
	  			loading("原始密码不能为空!",4);
	  		}else if(isEmpty(newPsd)){
	  			loading("新密码不能为空!",4);
	  		}else if(newPsd != newPsdN){
	  			loading("您输入的新密码不一致,请您重新输入!",4);
	  			$("#passwordNew").focus();
	  		}else{
	  			$.ajax({
	  				type:"post",
	  				url:"/user/updatePsd",
	  				data:{
	  					odlPsd:oldPsd,
	  					newPsd:newPsd
	  				},
	  				success:function(data){
	  					if(data.result == 1){
	  						loading("修改密码成功!",4);
	  						$("#password").val("");
	  			  			$("#passwordNew").val("");
	  			  			$("#password").focus();
	  			  			$("#passwordNew2").val("");
	  					}else{
	  						loading("修改密码失败!",4);
	  					}
	  				}
	  			});
	  		}
  		}
  </script>
  </body>
</html>
