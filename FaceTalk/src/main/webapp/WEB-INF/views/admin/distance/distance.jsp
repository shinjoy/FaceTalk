<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	
	$(document).ready(function() {

 		aside.setActive(1,8);
	});
	/* 저장 */
	function submitFormCategory(frm) {

		var param = {
				distanceSeq : frm.distanceSeq.value,
				distanceValue : frm.distanceValue.value,
				distanceName : frm.distanceName.value
		};
		//console.log(param);
		$.ajax({
			type:"POST",
			url:"/admin/distance/distance_edit_do.go",
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


	function deleteCategory(distanceSeq) {
		if(confirm("거리를 삭제하시겠습니까?")) {
			var param = {
					distanceSeq	:	distanceSeq
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/distance/distance_delete_do.go",
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
 	table.cat tbody td { padding:5px; } 
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
				 ■ 홈 > 회원관리 > 거리관리
			</header>
		
			<div class="contents-block">
			
				<h1>거리관리</h1>
				
				
				<div style="float:left; width:300px;">				
							<table class="cat">	
								<thead>
									<form method="post" name="parentseqForm">
										<tr>
											<td><h2>&nbsp;</h2></td>	
											<td>
												&nbsp;
											
											</td>
										</tr>
									</form>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${distance.size() > 0}">
										<tr><td><b>거리(m)</b></td><td><b>거리명</b></td></tr>
											<div id="sortable">
											<c:forEach var="it" items="${distance}">
												<form method="post" name="seqForm">
												
													<tr>
														<input type="hidden" name="distanceSeq" value="${it.distanceSeq}">
														<td><input type="text" class="itext" name="distanceValue" value="${it.distanceValue}" ></td>
														<td><input type="text" class="itext" name="distanceName" value="${it.distanceName}"></td>
														<td><button type="button" class="btn-blue" onclick="submitFormCategory(this.form);" >수정</button></td>			
														<td><button type="button" class="btn-red" onclick="deleteCategory(${it.distanceSeq});">삭제</button></td>																						
													</tr>
											
												</form>				
											</c:forEach>
											</div>
											<form method="post" name="seqForm">
												<tr>									
													<input type="hidden" name="distanceSeq" value="0">
													<td><input type="text" class="itext" name="distanceValue" ></td>
													<td><input type="text" class="itext" name="distanceName" ></td>
													<td><button type="button" class="btn-blue" onclick="submitFormCategory(this.form);">추가</button></td>		
												</tr>
											</form>
										</c:when>	
										<c:otherwise>
											<form method="post" name="seqForm">
												<tr>									
													<input type="hidden" name="distanceSeq" value="0">
													<td><input type="text" class="itext" name="distanceValue" ></td>
													<td><input type="text" class="itext" name="distanceName" ></td>
													<td><button type="button" class="btn-blue" onclick="submitFormCategory(this.form);">추가</button></td>		
												</tr>	
											</form>	
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
				</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>