<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	
	$(document).ready(function() {
// 		aside.setActive(3,1);
 		aside.setActive(2,2);
	});

	/* 저장 */
	function submitFormCategory(frm) {

		if (frm.categoryName.value == "") {
			alert("주제를 입력해 주세요.");
			return false;
		}

		var param = {
				categorySeq : frm.categorySeq.value,
				categoryName : frm.categoryName.value
		};
		//console.log(param);
		$.ajax({
			type:"POST",
			url:"/admin/talk/subject_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json) {
				alert(json.message);
				if(json.result) {
					document.location.reload();
				}
			}
		});
	}


	
	function deleteParentCategory(categorySeq) {
		if(confirm("토크 주제를 삭제하시겠습니까?")) {
			var param = {
					categorySeq	:	categorySeq
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/talk/subject_delete_do.go",
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
	table.cat { margin-left:20px; }
	table.cat tbody td { padding-left:20px; padding-bottom:10px; }
	td h2 { margin-top:10px; }
</style>

</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 토크주제
			</header>
		
			<div class="contents-block">
			
				<h1>토크주제</h1>
				

				<table class="list">
				<colgroup>
					<col width="300">
					<col width="100">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>주제</th>
						<th>관리</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${general.size() > 0}">
							<c:forEach var="it" items="${general}">
								<form method="post" name="seqForm">
									<tr>
										<input type="hidden" name="categorySeq" value="${it.categorySeq}">
										<td><input type="text" class="itext" name="categoryName" value="${it.categoryName}"></td>
										<td>
											<button type="button" class="btn-blue"  onclick="submitFormCategory(this.form);" >수정</button>			
											<button type="button" class="btn-red" onclick="deleteParentCategory(${it.categorySeq});">삭제</button>
										</td>
										<td>&nbsp;</td>																		
									</tr>
								</form>				
							</c:forEach>
						</c:when>	
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</tbody>
				<tfooter>
					<form method="post" name="seqForm">
						<tr style="background-color:#be7;">									
							<input type="hidden" name="categorySeq" value="0">
							<td><input type="text" class="itext" name="categoryName" value="${it.categoryName}"></td>
							<td><button type="button" class="btn-blue" onclick="submitFormCategory(this.form);">추가</button></td>		
							<td>&nbsp;</td>																		
						</tr>	
					</form>	
				</tfooter>
				</table>
				
			</div>
			
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>