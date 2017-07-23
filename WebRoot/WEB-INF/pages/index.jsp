<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>Document</title>
  <%@ include file="/WEB-INF/common/common.jsp" %>
  <link rel="stylesheet" type="text/css" href="${basePath }/css/bootstrap.min.css"/>
  <style type="text/css">
  	html,body{width:100%;height:100%;background:url(${basePath}/images/body1.jpg) no-repeat;background-size:100% 100%;}
  	/* 头部 */
  	.header{width:100%;height:59px;background:#e5e5e5}
  	.header .logo{font-size: 36px;width: 570px;margin: 0 auto;line-height: 50px;color: #999;font-family: 宋体;font-weight: 700;text-align: center;}
  	#tz_usermessagebox{display:inline-block;}
  	.sgl_items{padding-left:100px;}
	.sgl_items a{color:#999;font-size:12px;}
	/* 内容 */
	.titlebox{width:100%;text-align: center;background:#000;}
  	.titlebox .imgbox{padding-top:20px;}
	.titlebox .number{font-size:24px;background:#68595A;border-radius:32px;display:inline-block;width:64px;height:64px;text-align:center;line-height:64px;color:#fff;}
	.titlebox .title{margin-left:20px;line-height:135px;color:#fff;margin-top:10px;}
	.titlebox .twrap{width:640px;margin:0 auto;}
	
	
	.clickmore{display:block;width:100px;height:30px;line-height:30px;text-align:center;border-radius:5px;background:#f50;color:#fff;margin: 0 auto;}
  
  	/* 弹出登录框 */
  	.login_dialog{width: 260px; height: 300px; position: absolute; z-index: 4;background: url(${basePath}/images/bg2.jpg) no-repeat;background-size: 100% 100%;}
	.login_dialog .msg_header{text-align: center;line-height: 98px;font-size:26px;color:#444;vertical-align: bottom;}
	.login_dialog .msg_header h1{position: relative;top: 0px;z-index: -1;}
	.login_dialog .msg_box{text-align: center;position: relative;z-index:1}
	.login_dialog .inp{height:40px;width: 240px;border: none;text-indent: 1em;}
	.login_dialog .ua{border-bottom: 1px solid #ccc}
	.login_dialog .btnlogin{padding: 8px 106px;background:#CFE6C7;color:#fff;transition:all 0.3s linear;}
	.login_dialog .btnlogin:hover{background:#141414;transition:all 0.3s linear;}
	.login_dialog .erromessage{font-size:12px;color:red;padding-top:5px;}
	.login_dialog .corn{width:0px;height:0px;display: inline-block;border-top:10px solid transparent;border-right:10px solid transparent;
		border-bottom:10px solid #9ABF9D;border-left:10px solid transparent;position: relative;top:-20px;left:5px;z-index: 5}
	.f00{color:#f00;}
	.descp{text-indent:2em;line-height:30px;}
  </style>
 </head>
 <body>
	<!-- 头部 -->
 	<%@ include file="/WEB-INF/common/header.jsp" %>
    <c:if test="${mPeriod.status eq 1 }">
	    <!-- 内容 -->
		<div class="titlebox">
			<div class="twrap">
				<!--标题部分-->
		
				<!--封面图部分-->
				<div class="imgbox">
					<img src="${basePath}/${mPeriod.img }" alt="挥挥手 谁要走" class="vol-cover " width="100%" height="100%"/>	
				</div>
				<h1>
					<span class="number">
						<c:if test="${mPeriod.period<10}">0${mPeriod.period}</c:if>
						<c:if test="${mPeriod.period>10}">${mPeriod.period}</c:if>
					</span>
					<span class="title">${mPeriod.title}</span>
				</h1>
			</div>
		</div>
		
		 <div id="warp">
			<div class="txt">
				<div class="vol-desc descp">
					${mPeriod.description}
				</div>
				<br>
				<div class="vol-desc f00">
					类型：${mPeriod.musicType.name}
				</div>
				<br>
				<div class="vol-desc f00">
					标签：${mPeriod.label}
				</div>
			</div>
			<!--音乐播放器-->
			<div class="music">
				<div class="img fl">
					<img src="" alt="背景图"  id="musicbjF" class="animated01"/>
				</div>
				<div class="fn fr">
					<div class="top">
						<h4 style="margin-top: 30px;" id="h3">歌名:<span class="m_title1"></span></h4>
					</div>
					<div class="center">
						<h4 style="margin-top: 30px;">专辑：<span class="m_art1"></span></h4>
						<h4 style="margin-top: 30px;">歌手：<span class="m_author1"></span></h4>
						<div id="bow">
							<audio src="" crossorigin="anonymous" id="audio"></audio>
						</div>
					</div>
					<!--功能操作区-->
					<div class="bot">
						<input type="hidden" id="indexM"/>
						<a href="javascript:;" id="prev" class="glyphicon glyphicon-fast-backward"></a>
						<a href="javascript:;" id="playStop" class="glyphicon glyphicon-play"></a>
						<a href="javascript:;" id="next" class="glyphicon glyphicon glyphicon-fast-forward"></a>
						<!--进度条-->
						<div class="percent">
							<div id="pcs"></div>
							<div id="dot"></div>
							<div id="over"></div>
						</div>
						<div class="fr zsc">
							<span id="ztime">00:00</span>
							<span>--</span>
							<span id="current">00:00</span>
						</div>
						<!--音量-->
						<div class="percents">
							<div id="pcs2"></div>
							<div id="dot2"></div>
							<div id="over2"></div>
						</div>
						<!--静音-->
						<div id="mut" class="fr glyphicon glyphicon-volume-up"></div>
					</div>
				</div>
				<div style="clear:both;"></div>
				<!--列表-->
				<ul id="ul">
					<c:forEach items="${mPeriod.musics}" var="music" varStatus="muiscindex">
						<li data-img="${music.img}" data-title="${music.title}" data-article="${music.article}" data-singer="${music.singer}" data-src='${basePath}/${music.path}'>	
							<a href="javascript:;"  class="trackname playitems ">${muiscindex.index+1}. </a>
							<span class="artist btn-play ">${music.title}</span>
							<span class="">${music.article}</span>
							<span class="">${music.singer}</span>
							<span class="">${music.timer}</span>	
						</li>	
					</c:forEach>
				</ul>
			</div>
			<!--分栏-->
			<div class="wrap" id="wrapbox">
			</div>
			 <div class="contentbox">
				<div class="editor rounded">
					<textarea class="t_content" placeholder="请输入您的评论..." id="commentEditor" maxlength="200"></textarea>
					<input type="hidden" value="${mPeriod.id }" id="periodId"/>
				</div>
				<div class="editorbtn">
					<span class="fl">同步到：腾讯，微博，网易。</span>
					<span class="fr"><a href="javascript:void(0);" class="btn" onclick="tz_savePl(this,event)">发布</a></span>
				</div>
			</div>
			<div class="comment-list" id="comment-list">
				
			</div>
			<a href="javascript:;" onclick="next()" class="clickmore">点击加载更多</a>
		</div>
	</c:if>
	<c:if test="${mPeriod.status ne 1 }">
		<p style="width:100%;height:60px;text-align:center;line-height:60px;color:#f00;">此期刊还未发布,请您发布后再来观赏!<a href="${basePath }/list">返回</a></p>
	</c:if>
	<script src="${basePath }/js/music/util.js" type="text/javascript" charset="utf-8"></script>
	<script src="${basePath }/js/music/G_music.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="${basePath }/js/index.js"></script>
 </body>
</html>
