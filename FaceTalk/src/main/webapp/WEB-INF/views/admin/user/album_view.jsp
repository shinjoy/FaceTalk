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
				
				<%@ include file="/WEB-INF/views/admin/user/user_detail.jsp"  %>

			
				<div class="tab-bar">
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view.go?userId=${user.userId}';"><span>방명록</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/talk_view.go?userId=${user.userId}';"><span>토크</span></button>
					<button type="button" class="tab active" onclick="document.location.href='/admin/user/album_view.go?userId=${user.userId}';"><span>앨범</span></button>
				</div>				
				
				<ul class="detail-tab">
					<c:choose>	
						<c:when test="${list.size() > 0}">
							<c:forEach var="it" items="${list}">
								<li class="imglist">
									<div class="photo-show" style="background-image:url('${it.fileName == '' ? '/images/img_logo.png' : it.fileName}')" onclick="pop.zoom('${it.fileName}');"></div>
									<div class="photo-show-info">
										<b>${it.userName} ( ${it.userAge} )</b>
										<br>
										${it.genderText} | ${it.area}
										<br>
										${fn:substring(it.regDate,0,16)}
									</div>
								</li>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<li style="height:100px; text-align:center; padding-top:80px; "> 조회된 데이터가 없습니다.</li>
						</c:otherwise>												
					</c:choose>
					
				</ul>
			</div>

			<div style="clear:both;"></div>
			${paging}	
			
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>