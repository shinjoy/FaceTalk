<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(1,1);
	});

	function deleteBook(bookSeq) {

		if(confirm("방명록을 삭제하시겠습니까?")) {
			var param = {
					bookSeq	:	bookSeq
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/user/book_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.reload();
					}
				}
			});
		}
		return false;
	}
	

</script>

<style>
.tab-list TD{
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
		
			<div class="contents-block" >
			
				<h1>일반회원 상세보기</h1>
				
				<%@ include file="/WEB-INF/views/admin/user/user_detail.jsp"  %>
					
				<div class="tab-bar">
					<button type="button" class="tab active" onclick="document.location.href='/admin/user/user_view.go?userId=${user.userId}';"><span>방명록</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/talk_view.go?userId=${user.userId}';"><span>토크</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/album_view.go?userId=${user.userId}';"><span>앨범</span></button>
				</div>				
				
				<div class="tab-list">
					<table class="list" style="width:100%;">
					<colgroup>
						<col width="150">
						<col width="100">
						<col width="*">
						<col width="120">
						<col width="100">
					</colgroup>
					<thead>
						<th>아이디</th>
						<th>이름</th>
						<th>내용</th>
						<th>등록일자</th>
						<th>관리</th>
					</thead>
					<tbody class="rl">
						<c:choose>
							<c:when test="${list.size() > 0}">
								<form method="post" name="userForm">
									<c:forEach var="it" items="${list}">
										<input type="hidden" name="bookSeq" value="${it.bookSeq}">
										<tr>
											<td>${it.guestId}</td>										
											<td>${it.userName}</td>
											<td style="text-align:left; padding-left:10px;">${it.contents}</td>
											<td>${it.regDate.substring(0,16)}</td>
												<td><button type="button" class="btn-red" onclick="deleteBook('${it.bookSeq}');">삭제</button></td>
										</tr>
									</c:forEach>
								</form>
							</c:when>
							<c:otherwise>
								<tr><td colspan="4" style="height:200px; text-align: center;">조회된 데이터가 없습니다.</td></tr>
							</c:otherwise>
						</c:choose>
					</tbody>
					</table>
					${paging}
				</div>		
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>