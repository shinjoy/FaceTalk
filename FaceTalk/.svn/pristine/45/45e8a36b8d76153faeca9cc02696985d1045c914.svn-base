<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<link rel="stylesheet" type="text/css" href="/lib/smarteditor/smart_editor2.css" />
<script type="text/javascript" src="/lib/smarteditor/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
	$(document).ready(function() {
 		aside.setActive(1,4);
	});
	
	function submitForm(frm) {
		
		
		if (frm.siteName.value == "") {
			alert("사이트명을 입력해주세요.");
			return false;
		}
		
		if (frm.siteDomain.value == "") {
			alert("사이트 도메인을 입력해주세요.");
			return false;
		}
		
		var param = {
			siteSeq	: frm.siteSeq.value,
			siteName: frm.siteName.value,
			siteDomain : frm.siteDomain.value,
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/site/site_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/admin/site/site.go";
				}
			}
		});

		return false;
	}
	
	
	
	
	function deleteNotice(siteSeq) {
		if(confirm("사이트를 삭제하시겠습니까?")) {
			var param = {
					siteSeq	:	siteSeq
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/site/site_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/site/site.go";
					}
				}
			});
		}
		return false;

	}
	
</script>

</head>

<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 게시판관리 > 강제 업데이트
			</header>
		
			<div class="contents-block">
			
				<h1>사이트 등록</h1>
				
				<div class="contents-edit">

					<form method="post" name="noticeForm" id="noticeEdit" onSubmit="return submitForm(this); return false;">
					<input type="hidden" name="siteSeq" value="${site.siteSeq}"/>
					<input type="hidden" name="userId" value="${USER_ID}"/>
					<input type="hidden" name="userName" value="${USER_NAME}"/>
					
						<table class="edit">
						<colgroup>
							<col width="100">
							<col width="*">
						</colgroup>
<!-- 						<tr> -->
<!-- 							<th>OS</th> -->
<!-- 							<td class="radio"> -->
<%-- 								<label><input type="radio" name="sendPush" value="21" ${notice.sendPush == 21 ? 'checked=\"checked\"' : ''}>안드로이드</label> --%>
<%-- 								<label><input type="radio" name="sendPush" value="22" ${notice.sendPush == 22 ? 'checked=\"checked\"' : ''}>IOS</label> --%>
<!-- 							</td> -->
<!-- 						</tr> -->
						<tr>
							<th>사이트명</th>
							<td><input type="text" name="siteName" class="itext" value="${site.siteName}"></td>
						</tr>
						<tr>
							<th>도메인</th>
							<td><input type="text" name="siteDomain" class="itext" style="width:590px;" value="${site.siteDomain}"></td>
						</tr>
						
						</table>
						
					</div>
					<div class="btn-tools">
						<button type="submit" class="btn-blue" style="width:200px;">저장</button>
						<c:if test="${site.siteSeq > 0}">
							<button type="button" class="btn-red" onclick="deleteNotice(${site.siteSeq});">삭제</button>
						</c:if>
						<button type="button" class="btn" onclick="document.location.href='/admin/site/site.go';" >목록으로</button>
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
