<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>广广之家-音乐网站</title>
<meta name="keywords" content="keyword1,keyword2,keyword3">
<meta name="description" content="this is my page">
<meta name="content-type" content="text/html; charset=UTF-8">
<link href="${basePath}/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${basePath}/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${basePath}/js/tz_util.js"></script>
<link href="${basePath}/css/log-res.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="container">
		<div class="header">
			<h1 class="logo size_large">
				<a class="logo_anchor" href="javascript:void(0);">静.....</a>
			</h1>
		</div>
		<div class="signup_view account login" style="height: 160px;">
			<div class="form_row form_row_email">
				<input type="text" name="account" placeholder="请输入账号..."
					id="account"/>
			</div>
			<div class="form_row form_row_password">
				<input type="password" name="password" placeholder="请输入密码..."
					id="password"/>
			</div>
			<div class="errormsg hide">
				<span class="efont"></span>
			</div>
		</div>
		<div class="buttons">
			<a href="javascript:void(0);" id="login" class="loginbtn">注册</a>
		</div>
		<div class="wangj">
			已经有账号?赶紧去<a href="${basePath }/login">登录</a>
		</div>
	</div>
	<div class="lay_background" id="lay_background"
		style="width: 100%; height: 100%;">
		<div id="lay_background_img"
			class="lay_background_img lay_background_img_fade_out">
			<img src="${basePath}/images/lgbj.jpg" id="bgimg">
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#login").on("click",tm_login);
		});
		
		/*注册方法*/
		function tm_login(){
			var account = $("#account").val();
			var password = $("#password").val();
			
			if(isEmpty(account)){
				 $("#account").focus();
				 showmessage("请输入账号!");
				 return false;
			}
			
			if(isEmpty(password)){
				 $("#password").focus();
				 showmessage("请输入密码!");
				 return false;
			}
			
			$.ajax({
				type:"post",
				beforeSend:function(){$("#login").off("click").css("padding","10px 100px").text("注册中...");},
				error:function(){$("#login").on("click",tm_login).text("注册");},
				url:"${basePath}/resged/"+account+"/"+password,
				success:function(data){
					if(data.result == 1){
						location.href = "/login";
					}else{
						showmessage("注册失败!");
						$("#account").select();
						$("#password").val("");
						$("#login").on("click",tm_login).css("padding","10px 140px").text("注册");
					}
				}
			});
		};
		
		//显示错误信息
		function showmessage(message){
			$(".errormsg").show().removeClass("hide").fadeOut(2000).find("span").text(message);
		}
	
	</script>
</body>
</html>
