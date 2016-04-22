<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(1,1);
	});
</script>

<style>
.tbl-list TD{
	border-bottom: 1px solid #ddd;
	padding: 5px;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>
<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 회원관리 > 일반회원 상세보기
			</header>
		
			<div class="contents-block">
			
				<h1>일반회원 상세보기</h1>

				<%@ include file="/WEB-INF/views/admin/user/user_detail.jsp"%>
						
				<div class="tab-bar">
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view.go?userId=${user.userId}';"><span>방명록</span></button>
					<button type="button" class="tab active" onclick="document.location.href='/admin/user/talk_view.go?userId=${user.userId}';"><span>토크</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/album_view.go?userId=${user.userId}';"><span>앨범</span></button>
				</div>
				
				<ul class="detail-tab">
					<c:choose>
						<c:when test="${list.size() > 0}">
							<c:forEach var="it" items="${list}">
							 
								<li class="imglist bbs">

	
									<a href="/admin/talk/talk_view.go?bbsSeq=${it.bbsSeq}">
										<div class="round-box" style="width:220px; padding:10px;">
											<table>
											<colgroup>
												<col width="60">
												<col width="*">
											</colgroup>
											<tr>
												<td>
													<div class="photo-detail" style="background-image:url('${it.photo == '' ? '/images/img_pro_defult.png' : it.photo}')"></div>
												</td>
												<td>
													<b>${it.userName} ( ${it.userAge} )</b>
													<br>
													${it.genderText} | ${it.area}
													<br>
													<span class="good"><img src='/images/btn_good_dw.png' style="height:12px;"> ${it.likeCount}</span>
													<span class="good"><img src='/images/btn_reply_dw.png' style="height:12px;"> ${it.commentCount}</span>
													<span class="good">신고 ${it.reportCount}</span>
												</td>
											</tr>
											</table>
											
											<hr>
											
											<div class="bbs-contents-preview">
												<c:choose>
													<c:when test="${it.bbsContents.length() > 200}">
														${it.bbsContents.substring(0,198)} ...
													</c:when>
													<c:otherwise>
														${it.bbsContents}
													</c:otherwise>
												</c:choose>
												
											</div>
	
											<div class="img-thumb">
												<c:if test="${it.files!=''}">
													<c:forEach items="${it.fileList}" var="it2" varStatus="i">
														<c:if test="${i.index lt 3 }">
															<div class="photo-talk"
																style="background-image:url('${it2}') "></div>
														</c:if>
														<c:if test="${i.index == 4 }">
															<h3>MORE..</h3>
														</c:if>
													</c:forEach>
												</c:if>
											</div>
											
											<hr>
											${fn:substring(it.regDate,0,16)}
	
										</div>
									</a>								
								</li>
							
							</c:forEach>
						</c:when>
						<c:otherwise>
							<li style="height: 100px; text-align: center; padding-top: 80px;">조회된 데이터가 없습니다.</li>
						</c:otherwise>
					</c:choose>

				</ul>
				<div style="clear:both;"></div>
					${paging}
				</div>
				
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>