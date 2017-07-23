<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tlds/tz.tld" prefix="tz" %>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>潭州学院-高级班项目实战-KeKe老师</title>
  <!-- 通用页面 -->
  <%@include  file="/WEB-INF/common/common.jsp"%>  
 </head>
 <body>
 	<!-- 头部 -->
	<%@include  file="/WEB-INF/common/header.jsp"%>  
	<div class="titlebox">
		<div class="twrap">
			<!--标题部分-->
			<h1>
				<span class="number cycle">
					<c:if test="${period.period<10}">0${period.period}</c:if>
					<c:if test="${period.period>10}">${period.period}</c:if>
				</span>
				<span class="title">${period.title}</span>
			</h1>
		</div>
	</div>
	<div id="warp">
		<!--封面图部分-->
			<div class="imgbox">
				<img src="${basePath}/${period.img}" alt="挥挥手 谁要走" class="vol-cover ">	
				<span class="icon-pause-pp play"><img src="${basePath}/images/play128.png"/></span>
				<span class="icon-pause-pp pause" style="display: none;"><img src="${basePath}/images/pause128.png"/></span>
			</div>
			<!--内容描述部分-->
			<div class="content">
				<div class="vol-desc">
					${period.description}
				</div>
				<br>
				<div class="vol-desc">
					类型：${period.musicType.name}
				</div>
				<br>
				<div class="vol-desc">
					标签：${period.label}
				</div>
				<!-- audiobox -->
				<div id="audiobox"></div>
			</div>
			<!--音乐播放区域-->
			
			<!--播放区域部分-->
			<div class="musicbox">
				<div class="left">
					<img class="imagetarget cycle" src="${basePath}/images/bg.jpg">
					<span class="icon-pause-lg play"><img src="${basePath}/images/play.png"/></span>
					<span class="icon-pause-lg pause" style="display: none;"><img src="${basePath}/images/pause.png"/></span>
				</div>
				<div class="right">
					<div class="m_title">未知</div>
					<div class="m_art">专辑：<span class="m_article">未知</span></div>
					<div class="m_author">歌者：<span class=m_singer>未知</span></div>
				</div>
				<!--播放器区域-->
				<div class="btn-wrapper">
					<a href="javascript:;" id="prev" class="jp-previous" title="上一曲">
						<span class="icon-prev-lg icon"></span>
					</a>
					<a href="javascript:;" id="play" class="play" title="播放">
						<span class="icon-play-lg icon"></span>
					</a>
					<a href="javascript:;" id="pause" class="pause" title="暂停" style="display: none;">
						<span class="icon-pause-lg icon"></span>
					</a>
					<a href="javascript:;" id="next" class="jp-next margin-right-0" title="下一曲">
						<span class="icon-next-lg icon"></span>
					</a>
				</div>
				<!--音乐播放器进度条-->
				<div class="program">
					<div class="load" id="load"></div>
					<div class="tz_per" id="percent"></div>
				</div>
				<!-- 声音进度条 -->
				<div class="fl">
					<a href="javascript:void(0);"  class="staticvolume fl"></a>
					<a href="javascript:void(0);"  class="m_loop fl"></a>
					<div class="m_volume_program fl">
						<div class="m_volume_load"></div>
						<div class="m_volume_percent"></div>
					</div>
				</div>
				<!--显示时间-->
				<div style="float:left;width:190px;margin-left: 50px;margin-top: 5px;">
					<span id="ctime">00:00</span>/<span id="stime">00:00</span>/<span id="time">00:00</span>
				</div>
			</div>
			<!---音乐列表-->
			<div id="playlist" style="width:100%;margin-top:20px;">
				<ul >
					<c:forEach items="${period.musics}" var="music" varStatus="muiscindex">
						<li class="track-item">
							<div class="track-wrapper clearfix">	
								<a href="javascript:;" data-img="${music.img}" title="${music.title}" data-article="${music.article}" data-singer="${music.singer}" data-src='${basePath}/${music.path}' data- class="trackname playitems ">${muiscindex.index+1}. </a>
								<span class="artist btn-play">${music.title}&nbsp;${music.article}</span>
								<span class="k_time" style="padding-left: 10px;">${tz:audiotime(music.timer)}</span>
							</div>		
							<!--track-detail-wrapper end-->
						</li>	
					</c:forEach>
				</ul>
		</div>
		<div class="contentbox">
			<div class="editor rounded">
				<textarea class="t_content"  placeholder="请输入评论长度少于200..." id="commentEditor"></textarea>
			</div>
			<div class="editorbtn">
				<span class="fl">同步到：腾讯，微博，网易。</span>
				<span class="fr"><a href="javascript:void(0);" id="submitcomment" data-pid="${period.id}" onclick="tm_saveComment(this,event)" class="btn">发布</a></span>
			</div>
		</div>
		<div class="comment-list" id="comment-listbox">
			<div style="width: 100%;text-align: center;"><img src="${basePath}/images/loading64.gif"/></div>
		</div>
	</div>
	<div style="height: 200px;background: transparent;width: 100%;">
	</div>
<!-- 底部 -->
<%@include  file="/WEB-INF/common/footer.jsp"%> 
<script type="text/javascript" src="${basePath}/js/tz_audio.js"></script>
<%-- <script type="text/javascript" src="${basePath}/js/music/tz_index.js"></script> --%>
<script type="text/javascript">
	$(function(){
		var pno = 0;//分页的数量
		var psize = 10;//每页显示的数量
		var loaded = true;//控制滚动的开关
        var periodId = $("#submitcomment").data("pid");//获取期刊id
		$(window).scroll(function () {
            var totalCount = $(".item").eq(0).data("itemcount");//获取总数13
	        if(totalCount <= psize){//如果总数都小于当前显示的数量，代表只有一页
	        	loaded = false;
	        	return;
	        }
	        var ppno = Math.ceil(totalCount / psize); //总页数 1
	 		if(pno >= ppno){
	 			loaded =false;
	 			return ;
	 		}      
	        var bot = 2; //bot是底部距离的高度
	        if (loaded && (bot + $(window).scrollTop()) >= ($(document).height() - $(window).height())) {
	           //当底部基本距离+滚动的高度〉=文档的高度-窗体的高度时；
	            //我们需要去异步加载数据了
	            loaded = false;
	            pno++;
	            loading("评论努力加载中....");
	            $.ajax({
	        		type:"post",
	        		url:"/ncomment/template/"+periodId+"/"+(pno * psize)+"/"+psize,
	        		error:function(){loaded=true;loading("remove");},
	        		success:function(jdata){
	        			loading("评论加载成功...",1);
	        			loaded = true;
	        			$("#comment-listbox").append(jdata);
	        		}
	            });
	        }
	    });
	});
	
	//第一：把textarea替换成ckeditor或者百度ueditor
	//第二：分类的自定义标签，或者ajax查询
	
</script>
</body>
</html>
