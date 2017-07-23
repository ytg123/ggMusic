<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tz" uri="/WEB-INF/tlds/tz.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>广广音乐首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="Place your description here" />
<meta name="keywords" content="put, your, keyword, here" />
<meta name="author" content="collect from Cssmoban.com - website templates provider" />
<%@ include file="/WEB-INF/common/common.jsp" %>
<link href="${basePath}/css/liststyle.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 7]>
	 <script type="text/javascript" src="js/ie_png.js"></script>
	 <script type="text/javascript">
			 ie_png.fix('.png');
	 </script>
<![endif]-->
<style type="text/css">
	.green{color:green;}
	.red{color:#f00;}
	#je a{display:inline-block;width:60px;height:30px;border:1px solid #ccc;line-height:30px;margin:5px;cursor:pointer;text-align:center;line-height:30px;color:#fff;border-radius:5px;}
	.checkeded{background:#333;color:#fff;}
</style>
</head>
<body id="page1">
	<!-- tail-cont S -->
	<div class="tail-cont">
		<div class="tail-top-left"></div>
		<!-- tail-top S -->
		<div class="tail-top">
			<!-- container S -->
			<div class="container">
				<!-- header -->
				<div id="header">
					<div class="logo"><a href="${basePath}/list"><img src="${basePath}/images/logo.jpg" alt="" /></a></div>
					<ul class="nav">
						<c:if test="${empty user}">
							<li><a href="${basePath}/login">login</a></li>
							<li><a href="${basePath}/resg">register</a></li>
						</c:if>
						<c:if test="${not empty user}">
							<span class="sgl_items">
							<img alt="头像" data-address="${user.address }" id="infoUser" src="/${user.headerPic}" width="30" height="30" style="border-radius:50%;margin-top: -6px;"/>
							<a href="${basePath }/user/index">【${user.username}】</a><a href="${basePath}/logout">【退出】</a>
							</span>
						</c:if>
					</ul>
					<%-- <ul class="site-nav">
						<li><a href="${basePath}/list" class="act">Home</a></li>
					</ul> --%>
				</div>
				<!-- E header -->
				<!-- content S -->
				<div id="content">
					<div class="indent">
						<div class="indent1">
							<h2><em>欢迎您来到广广之家音乐俱乐部</em></h2>
							<p class="p1">
								Hello everyone. I feel pleased of being here as a representative of English Club to give you a short introductive speech on our organization. English Club has been established for 10 years. It is one of the popular organizations on the campus. English Club opens in every afternoon after class, and it now has a membership of 200 students. Moreover, all the members will have a get-together once each week. The main goal of English Club is to raise students' interest in learning English and help them to improve. Welcome to join us!
							</p>
						</div>
						<h3><em>期刊列表</em></h3>
						<%-- <ul class="list">
							<li><img src="${basePath}/images/icon4.gif" alt="" /></li>
							<li><img src="${basePath}/images/icon5.gif" alt="" /></li>
							<li><img src="${basePath}/images/icon4.gif" alt="" /></li>
						</ul> --%>
						<a href="${basePath }/period/addMusic" class="addPeriod">添加期刊</a>
						<table id="gTable">
							<thead>
								<tr>
									<th>ID</th>
				    				<th>封面</th>
				    				<th>标题</th>
				    				<th>类型</th>
				    				<th>点击数</th>
				    				<th>第几期</th>
				    				<th>创建时间</th>
				    				<th>发布状态</th>
				    				<th>操作</th>
								</tr>
							</thead>
							<tbody id="contentList">
								<c:forEach items="${periodList }" var="plist">
									<tr class="tz_items">
										<td>${plist.id }</td>
										<td>
											<a href="${basePath }/music/${plist.id }"><img alt="封面" src="${plist.img }" width="50" height="50" style="padding:5px;"/></a>
										</td>
										<td>${plist.title }</td>
										<td>${plist.musicType.name }</td>
										<td>${plist.hit }</td>
										<td>${plist.period }</td>
										<td>${tz:formateDate(plist.createTime,'yyyy-MM-dd HH:mm:ss') }</td>
										<td>
											<a href="javascript:;" onclick="tz_publish(this)" data-id="${plist.id }" data-status="${plist.status}">${plist.status == 1 ? "已发布" : "未发布" }</a>
										</td>
										<td>
											<a href="javascript:;" onclick="deletePeriod(this)" data-id="${plist.id }">删除</a>
											<a href="${basePath }/period/updateMusic/${plist.id }">修改</a>
											<a href="${basePath }/music/${plist.id }">预览</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- 分页 -->
						<ul id="je">
	
						</ul>
					</div>
				</div>
				<!-- E content -->
			</div>
			<!-- E container -->
		</div>
		<!-- E tail-top -->
	</div>
	<!-- E tail-cont -->
	<script type="text/javascript" src="${basePath }/js/jPages.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#je").jPages({
				containerID: "contentList",
				perPage: 3,
				first: "首页",
				previous: "上一页",
				next: "下一页",
				last: "尾页",
				animation:"animated bounceInDown",
				minHeight: false,
				clickStop: true
			});
			//背景
			$("#je").find("a").eq(2).addClass("checkeded");
	  		$("#je").find("a").click(function(){
	  			$(this).addClass("checkeded").siblings().removeClass("checkeded");
	  		});
		});
		//删除
		function deletePeriod(obj){
			var id = $(obj).data("id");
			$.tzAlert({title:"删除提示",content:"您确定删除吗?",callback:function(ok){
				 if(ok){
					 $.ajax({
						type:"post",
						url:"/period/delete",
						data:{"id":id},
						success:function(data){
							if(!data){
								loading("操作失败...",2);
							}else{
								$(obj).parents(".tz_items").fadeOut("slow",function(){
									$(this).remove();
	 							});
							}
						}
					 });
				 }
			 }});
		}
		//发布与否
		var timer = null;
  		function tz_publish(obj){
  		   clearTimeout(timer);
  		   timer=setTimeout(function(){
	  		    var opid = $(obj).data("id");
	  		    var status = $(obj).data("status");
	  		    var cstatus ;
	  		    if(status==1){
	  		    	cstatus=0;
	  		    }else{
	  		    	cstatus = 1;
	  		    }
	  		    
  				$.ajax({
  					type:"post",
  					beforeSend:function(){loading("操作进行中,请稍后...");},
  					error:function(){loading("操作失败...",2);},
  					url:"/period/update",
  					data:{"status":cstatus,"id":opid},
  					success:function(data){
  						if(!data){
  							loading("操作失败...",2);
  						}else{
  							if(status==1){
  				  		    	cstatus=0;
  				  		    	$(obj).toggleClass("green red").text("未发布");
  				  		    }else{
  				  		    	cstatus = 1;
  				  		    	$(obj).toggleClass("green red").text("已发布");
  				  		    }
  							$(obj).data("status",cstatus);
  							loading("remove");
  						}
  					}
  				 });
  			},200);
  		};
	</script>
</body>
</html>
