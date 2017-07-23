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
		#progressBtn{width: 280px;height: 20px;border-radius:5px;border:1px solid #f00;float:left;margin-right:5px;}
		#progressBtn #prosub{width:1%;height:100%;border-radius:5px;background:#f50;}
		.percentsg{margin:0 5px;width:100px;display:inline-block;}
	</style>
  </head>
  
  <body>
  	<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<form class="form-horizontal" id="formUser">
	            		  <input type="hidden" id="uid" value="${userM.id }" name="id"/>
						  <div class="form-group">
						    <label>名称</label>
						    <input type="text" class="form-control" id="name" name="username" placeholder="请输入名称" value="${userM.username}" required="required"/>
						  </div>
						  <div class="form-group">
						    	<input type="file" id="file" style="display:none;" onchange="uploadFile()"/>
						    	<div id="progressBtn">
						    		<div id="prosub"></div>
						    	</div>
						    	<span class="percentsg"></span>
						    	<a href="javascript:;" class="btn btn-danger btn-xs" onclick="openSelectDialog()">上传图片</a>
						  </div>
						  <div class="form-group">
						    <label>手机</label>
						    <input type="number" class="form-control" id="telephone" name="telephone" placeholder="请输入手机" value="${userM.telephone }"/>
						  </div>
						  <div class="form-group">
						    <label>年龄</label>
						    <input type="number" class="form-control" id="age" required="required" name="age" placeholder="请输入年龄" value="${userM.age }"/>
						  </div>
						  <div class="form-group">
						    <label>地址</label>
						    <input type="text" class="form-control" id="address" required="required" name="address" placeholder="请输入地址" value="${userM.address }"/>
						  </div>
						  <div class="form-group">
						    <label>性别</label>
						    <select id="male" class="form-control"   name="male">
						    	<option value="0">女</option>
						    	<option value="1">男</option>
						    	<option value="2">保密</option>
						    </select>
						  </div>
						  <input type="button" class="btn btn-primary btn-md" onclick="updateUser()" value="确认修改"/>
					</form>
				</div>
			</div>
	</div>
  <script type="text/javascript">
			//模拟file选择弹出框
			function openSelectDialog(){
				var ie=navigator.appName=="Microsoft Internet Explorer" ? true : false; 
				if(ie){ //ie
					document.getElementById("file").click(); 
				}else{//google firefox s
					var a=document.createEvent("MouseEvents");//FF的处理 
					a.initEvent("click", true, true);  
					document.getElementById("file").dispatchEvent(a); 
				} 
			};
			function uploadFile(){
				//object FileList  ---百度:javascript FileList 搜索javascript 文件上传 ，javascript html5 文件上传
				var fileObj = document.getElementById("file").files[0];//drag上传
				/* var type = fileObj.type;//文件类型,mine-type
				var name = fileObj.name;//文件名称
				var size = fileObj.size;//文件大小
				if(type!="image/jpeg"){
					alert(" 请选择图片....");
					return;
				} */
				// FormData 对象，动态form表单
			    var formData = new FormData();
			    formData.append("doc", fileObj);                           // 文件对象
			    // 文件对象
			    // XMLHttpRequest 对象 ajax对象
			    var xhr = new XMLHttpRequest();
			    //请求服务器地址
			    xhr.open("post", "/upload", true);
			    xhr.onreadystatechange = function(){
					if(xhr.readyState==4 && xhr.status==200){//状态4和200代表和服务器端交互成功
						var data = JSON.parse(xhr.responseText),
							imgurl = data.url,
							id = $("#uid").val();
						$.ajax({
							type:"post",
							url:"${basePath}/user/userUpdate",
							data:{
								id:id,
								headerPic:imgurl
							},
							success:function(data){
								if(data == "success"){
									loading("上传头像成功!",5);
								}else{
									loading("上传头像失败!",5);
								}
							}
						});
					}
				};
				
				//监听文件上传往服务器传输的字节回调方法progress
				xhr.upload.addEventListener("progress", progressFunction, false);
				xhr.send(formData);//讲数据传输给服务器
			};
			//真实的进度,往服务器端传输了多少字节。
			function progressFunction(evt) {
		      var $progressBar = $("#prosub");
		      var $percentageDiv = $(".percentsg");
		      if (evt.lengthComputable) {
		      	$progressBar.attr("max",evt.total);
		      	$progressBar.attr("style","width:"+Math.round(evt.loaded / evt.total * 100) + "%"+";");
		          $percentageDiv.html(Math.round(evt.loaded / evt.total * 100) + "%");
		      }
		  	};
		  	//修改用户信息
		  	function updateUser(){
		  		var formInfos = $("#formUser").serialize();
		  		loading("数据提交中....");
		  		$.ajax({
		  			type:"post",
		  			url:"${basePath}/user/userUpdate",
		  			data:formInfos,
		  			success:function(data){
		  				if(data == "success"){
							loading("修改成功!",5);
						}else{
							loading("修改失败!",5);
						}
		  			}
		  		});
		  	}
  </script>
  </body>
</html>
