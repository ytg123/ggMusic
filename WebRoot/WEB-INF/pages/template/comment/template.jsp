<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tz" uri="/WEB-INF/tlds/tz.tld"%>
<c:forEach items="${comments }" var="comment">
	<div class='item animated rotateInUpLeft' data-itemcount="${totalCount}">
					<a href='javascript:;' class='avatar-wrapper' >
			<img src='${comment.user.headerPic }' alt='BLACK8' class='avatar rounded'>
		</a>
		<div class='item-wrapper'>
			<p class='helper'>
				<a href='javascript:;' class='username' target='_blank'>${comment.user.username }</a>
			</p>
			<div class='comment-ct'>
				<p class='the-comment'>${comment.content }</p>
			</div>
			<div class='helper clearfix' title="${tz:formateDate(comment.createTime,'yyyy-MM-dd HH:mm:ss') }">
				${tz:dataString(comment.createTime) }
				&nbsp;&nbsp;
				来自:${tz:ipAddress(comment.ip) }
				&nbsp;&nbsp;
				<a href='javascript:;' class='btn-reply btn-action-reply'>回复</a>&nbsp;&nbsp;
				<a class='btn-vote btn-action-vote' href='javascript:;'><span>赞</span></a>
			</div>
		</div>
	</div>
</c:forEach>

