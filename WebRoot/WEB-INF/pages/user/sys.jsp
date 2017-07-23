<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户管理</title>
		<%@ include file="/WEB-INF/common/common.jsp" %>
		<link rel="stylesheet" type="text/css" href="${basePath }/css/bootstrap.min.css"/>
		<script type="text/javascript" src="${basePath }/js/bootstrap.min.js"></script>
		<style type="text/css">
			.tzdialog_title{font-size:14px;}
			.form-inline .form-group{margin-bottom:15px;}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th class="text-center">ID</th>
								<th class="text-center">用户名</th>
								<th class="text-center">头像</th>
								<th class="text-center">手机</th>
								<th class="text-center">年龄</th>
								<th class="text-center">地址</th>
								<th class="text-center">性别</th>
								<th class="text-center">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userList }" var="ulist">
								<tr class="text-center m_items">
									<td>${ulist.id }</td>
									<td>${ulist.username }</td>
									<td>
										<img alt="头像" src="/${ulist.headerPic }" width="30" height="30"/>
									</td>
									<td>${ulist.telephone }</td>
									<td>${ulist.age }</td>
									<td>${ulist.address }</td>
									<td>${ulist.male }</td>
									<td>
										<c:if test="${logUser.id eq 1 }">	
											<a href="javascript:;" data-id="${ulist.id }" class="btn btn-wraning btn-xs" onclick="deleteU(this)">删除</a>
										</c:if>
										<a href="${basePath }/user/upUser/${ulist.id }"  class="btn btn-success btn-xs">修改</a>
									</td>
								</tr>
							</c:forEach>	
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			//删除
			function deleteU(obj){
				var id = $(obj).data("id");
				$.tzAlert({content:"您确定要删除吗?",callback:function(ok){
					if(ok){
						$.ajax({
							type:"post",
							url:"/user/deleteU/",
							data:{id:id},
							success:function(data){
								if(data=="success"){
									$(obj).parents(".m_items").fadeOut("slow",function(){
										$(this).remove();
									});
								}else if(data=="fail"){
									loading("删除失败...",2);
								}else if(data=="logout"){
									window.location.href = "/login";
								}
							}
						});
					}
				}});
			}
		</script>
	</body>
</html>

