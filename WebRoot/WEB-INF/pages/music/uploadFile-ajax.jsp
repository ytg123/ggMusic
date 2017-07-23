<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>潭州学院-音乐列表管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include  file="/WEB-INF/common/common.jsp"%>
	<style type="text/css">
		.pbox{text-align: center;line-height: 40px;}
		.pbox .progress{width:360px;height: 30px;float:left}
		.progbox{width: 460px;background:#DCCCCC;height: 88px;margin: 0 auto;padding:5px;}
		.pbox span.percent{float:left;position: relative;top: -5px;}
		.pbox .msg{float:left;font-size: 12px;}
		.pbox .msgs{float:left}
		/*上传按钮*/
		.upbtn{background:#1F989B;padding:5px 14px;text-decoration:none;color:#fff;border-radius:4px;}
	</style>
  </head>
  <body>
  	<!-- 布局,PS,js.Jquery -->
  	<div class="pbox">
  		<div class="progbox">
  			<div class="msg">当前正在上传：是对方说的发士大夫.txt</div>
	  		<div class="msgs">
	  			<progress class="progress"  value="0" max="100"></progress>
	  			<span class="percent">0%</span>
	  		</div>
  		</div>
  	</div>
  	
  	<form id="up_form" action="${basePath}/upload" method="post" enctype="multipart/form-data">
  		<input type="file" id="file" onchange="uploadFile(this)" name="doc" style="display: none;">
  		<div id="filename"></div>
  		<a href="javascript:void(0);" onclick="openSelectDialog()" class="upbtn">上传</a>
  	</form>
  	
  	<script type="text/javascript">
  		/*
  			1:怎么替换上传按钮
  			2:FormData --javascript对象
  			3:通过XMLHttpRequest进行传输.send(formdata)
  			//XMLHttpRequest --ajax
  		*/
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
  			var type = fileObj.type;//文件类型,mine-type
  			var name = fileObj.name;//文件名称
  			var size = fileObj.size;//文件大小
//   		if(type!="image/jpeg"){
//   			alert(" 请选择图片....");
//   			return;
//   		}
  			
  			$(".msg").html("当前上传文件是:"+name+"；大小是："+size+"；类型是:"+type);
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
  					var data = xhr.responseText;
  					alert(data);
  				}
  			};
  			
  			//监听文件上传往服务器传输的字节回调方法progress
   			xhr.upload.addEventListener("progress", progressFunction, false);
  			xhr.send(formData);//讲数据传输给服务器
  		};
  		
  		//真实的进度,往服务器端传输了多少字节。
  		function progressFunction(evt) {
  	        var $progressBar = $(".progress");
  	        var $percentageDiv = $(".percent");
  	        if (evt.lengthComputable) {
  	        	$progressBar.attr("max",evt.total);
  	        	$progressBar.attr("value",evt.loaded);
  	            $percentageDiv.html(Math.round(evt.loaded / evt.total * 100) + "%");
  	        }
  	    } ; 
  	</script>
  </body>
</html>
