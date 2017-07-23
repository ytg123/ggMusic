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
				<input type="text" name="account" placeholder="请输入邮箱..."
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
			<a href="javascript:void(0);" id="login" class="loginbtn">登陆</a>
		</div>
		<div class="wangj">
			还没账号?赶紧去<a href="${basePath }/resg">注册</a>
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
		
		/*登陆方法*/
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
				beforeSend:function(){$("#login").off("click").css("padding","10px 100px").text("登陆中...");},
				error:function(){$("#login").on("click",tm_login).text("登陆");},
				url:"${basePath}/logined/"+account+"/"+password,
				success:function(data){
					if(data){
						//历史页面
						var rurl = document.referrer;
						if(!rurl){
							//调整到首页去
							window.location.href = "/list";
						}else{
							if(rurl.indexOf("resg")>0){
								//调整到首页去
								window.location.href = "/list";
							}else{
								//跳往历史页面
								window.location.href = rurl;
							}
						}
						
					}else{
						showmessage("请正确输入账号和密码 ！");
						$("#account").select();
						$("#password").val("");
						$("#login").on("click",tm_login).css("padding","10px 140px").text("登陆");
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
