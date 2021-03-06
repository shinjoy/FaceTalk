<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>


<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(1,4);
});

function submitForm(frm) {
	if (frm.siteName.value == "") {
		alert("사이트명을 입력하세요.");
		return false;
	}
	if (frm.siteUrl.value == "") {
		alert("사이트 URL을 입력하세요.");
		return false;
	}
	if (frm.packageName.value == "") {
		alert("패키지명을 입력하세요.");
		return false;
	}
	if (frm.serviceId.value == "") {
		alert("서비스 아이디를 입력하세요.");
		return false;
	}
	var param = {
		siteSeq : frm.siteSeq.value,
		siteName : frm.siteName.value,
		siteUrl : frm.siteUrl.value,
		packageName : frm.packageName.value,
		serviceId : frm.serviceId.value,
		companyName : frm.companyName.value,
		companyPhone : frm.companyPhone.value,
		managerName : frm.managerName.value
	}; 
	
	
	$.ajax({
		type:"POST",
		url:"/admin/user/site_edit_do.go",
		dataType:"json",
		data:param,
		success:function(json){
			alert(json.message);
			if (json.result) {
				document.location.href = "/admin/user/site.go";
			}
		}
	});
	return false;
}

function deleteNotice(siteSeq) {
	if(confirm("운영사이트를 삭제하시겠습니까?")) {
		var param = {
				siteSeq	:	siteSeq
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/user/site_delete_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/admin/user/site.go";
				}
			}
		});
	}
	return false;

}


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
				 ■ 홈 > 회원관리 > 운영사이트 관리
			</header>
		
			<form method="post" name="siteForm" onsubmit="return submitForm(this); return false;">
			<%-- <input type="hidden" name="userId" value="${user.userId}"> --%>
			<input type="hidden" name="siteSeq" value="${site.siteSeq}">

				<div class="contents-block">
				
					<h1>운영사이트 관리</h1>
				
					<table class="register">
					<colgroup>
						<col width="150">
						<col width="*">
					</colgroup>
					<tr>
						<th><div class="icon">* 사이트명</div></th>
						<td><input type="text" class="itext" name="siteName"  value="${site.siteName}" style="width:300px;"></td> 
					</tr>
					<tr>
						<th><div class="icon">* 서비스 ID</div></th>
						<td><input type="text" class="itext" name="serviceId"  value="${site.serviceId}" style="width:300px;"></td> 
					</tr>
					<tr>
						<th><div class="icon">* 패키지명</div></th>
						<td><input type="text" class="itext" name="packageName"  value="${site.packageName}" style="width:300px;"></td> 
					</tr>
					<tr>
						<th><div class="icon">* 사이트 URL</div></th>
						<td><input type="text" class="itext" name="siteUrl"  value="${site.siteUrl}" style="width:600px;"></td> 
					</tr>
					<tr>
						<th><div class="icon">업체명</div></th>
						<td><input type="text" class="itext" name="companyName"  value="${site.companyName}" style="width:300px;"></td> 
					</tr>
					<tr>
						<th><div class="icon">업체연락처</div></th>
						<td><input type="text" class="itext" name="companyPhone"  value="${site.companyPhone}" style="width:300px;"></td> 
					</tr>
					<tr>
						<th><div class="icon">업체관리자</div></th>
						<td><input type="text" class="itext" name="managerName"  value="${site.managerName}" style="width:300px;"></td> 
					</tr>
					</table>

				</div>

				<div  class="btn-tools" >
					<c:choose>
						<c:when test="${site.siteSeq < 1 }">	
							<button type="submit" class="btn-blue" >등록</button>
						</c:when>
						<c:otherwise>
							<button type="submit" class="btn-blue" >수정</button>
							<button type="button" class="btn-red" onclick="deleteNotice('${site.siteSeq}');">삭제</button>
						</c:otherwise>
					</c:choose>
					<button type="button" class="btn" onclick="document.location.href='/admin/user/site.go';">취소</button>
				</div>

			</form>	
			
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>