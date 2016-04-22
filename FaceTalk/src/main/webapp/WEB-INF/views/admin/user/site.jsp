<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>


<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(1,4);
});

</script>

<style>
.select-search { border:1px solid #ddd; padding-bottom:4px;}
</style>


</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 회원관리 > 운영사이트
			</header>
		
			<div class="contents-block">
			
				<h1>운영사이트</h1>
				
				<div class="contents-main">

					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<div class="contents-top">
							<div class="top-tools">
								<div class="btn-tools"><button type="button" class="btn-blue" onclick="document.location.href='/admin/user/site_edit.go?siteSeq=0';">신규 사이트 등록</button></div>
							</div>
						</div>

						<div id="contents-list">
		
		<table class="list">
			<thead>
				<tr>
					<th>사이트코드</th>
					<th>사이트명</th>
					<th>사이트 URL</th>
					<th>업체명</th>
					<th>업체연락처</th>
					<th>관리자명</th>
					<th>회원수</th>
					<th>관리자수</th>
					<th>등록일자</th>
				</tr>
			</thead>
			<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					<c:forEach var="it" items="${list}">
						
						<tr>
							<td><a href="/admin/user/site_edit.go?siteSeq=${it.siteSeq}">${it.siteSeq}</a></td>
							<td style="text-align: left; padding-left: 10px;">
								<a href="/admin/user/site_edit.go?siteSeq=${it.siteSeq}">${it.siteName}</a>
							</td>
							<td style="text-align: left; padding-left: 10px;">${it.siteUrl}</td>
							<td style="text-align: left; padding-left: 10px;">${it.companyName}</td>
							<td>${it.companyPhone}</td>
							<td>${it.managerName}</td>
							<td>${it.userCount}</td>
							<td>${it.managerCount}</td>
							<td>${it.regDate.substring(0,10)}</td>						
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="9" style="height:200px;">조회된 데이터가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
			
		${paging}
								</div>
					
					</form>
					
				</div>
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>