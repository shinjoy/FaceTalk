<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>



<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(1,3);
	searchList(1);
});


function searchList(page) {
	var param = {
		page		:	page
	};
	
	return false;
}
function selectSite(frm) {

	document.location.href="/admin/user/charge_item.go?site="+frm.site.value;	


}
function submitForm(frm) {
	if (frm.itemName.value == "") {
		alert("아이템 이름을 입력해주세요.");
		return false;
	}
	if (frm.point.value == "") {
		alert("포인트를 입력해주세요.");
		return false;
	}
	if (frm.point.value != 0 ) {
		if(frm.point.value.indexOf("-")==-1){
			alert("포인트를 다시 입력해주세요.");
			return false;
		}
		
	}

	if (frm.limitCount1.value == "") {
		alert("횟수를 선택해주세요.");
		return false;
	}
	
	var param = {
			seq : frm.seq.value,
			itemName : $("select[name=itemName]").val(),
		
			point : frm.point.value,
			site : frm.site.value,
			limitCount : frm.limitCount1.value
			
	};
	
	
	$.ajax({
		type:"POST",
		url:"/admin/user/itemcharge_edit_do.go",
		dataType:"json",
		data:param,
		success:function(json){
			alert(json.message);
			if (json.result) {
				document.location.reload();
			}
		}
	});

	return false;
}


function pointEditForm(frm) {

	
	if (frm.point1.value == "") {
		alert("포인트를 입력해주세요.");
		return false;
	}
	
	if (frm.point1.value != 0 ) {
		if(frm.point1.value.indexOf("-")==-1){
			alert("포인트를 다시 입력해주세요.");
			return false;
		}
		
	}

	if (frm.limitCount.value == "") {
		alert("횟수를 선택해주세요.");
		return false;
	}
	
	var param = {
			seq : frm.seq.value,
			itemName : $("select[name=itemName]").val(),
		
			point : frm.point1.value,
			site : frm.site.value,
			limitCount : frm.limitCount.value
		
			
	};
	
	
	$.ajax({
		type:"POST",
		url:"/admin/user/itemcharge_edit_do.go",
		dataType:"json",
		data:param,
		success:function(json){
			alert(json.message);
			if (json.result) {
				document.location.reload();
			}
		}
	});

	return false;
}

function deletePoint(seq) {
	if(confirm("삭제하시겠습니까?")) {
		var param = {
				seq	:	seq
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/user/itemcharge_delete_do.go",
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

</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 회원관리 > 포인트 설정
			</header>
		
			<div class="contents-block">
			
				<h1>아이템 설정</h1>
				<form>
					<c:choose>
							<c:when test="${USER_TYPE==1}">
							<table>
							<tr>
								<td>
									<select name="site" onchange="selectSite(this.form);">
										<c:forEach items="${sitelist}" var="it">
											<option value="${it.serviceId}" ${it.serviceId==site ? 'selected=\"selected\"': '' }>${it.siteName}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							</table>
							</c:when>
						
					</c:choose>		
				</form>	

						<div class="contents-top">
							<div class="top-tools">
								<div class="tab-bar">
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point.go';"><span>포인트 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point_charge.go';"><span>포인트 충전 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point_money.go';"><span>포인트/Money 전환 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_level.go';"><span>레벨 정책 설정</span></button>
									<button type="button" class="tab active " onclick="document.location.href='/admin/user/charge_item.go';"><span>아이템 설정</span></button>
								</div>
							</div>
							
						</div>

						<div id="contents-list">
							<table class="list" style="border: 1px solid #ddd;">
								<colgroup>
									<col width="30%">
									<col width="10%">
									<col width="10%">
									<col width="20%">
							
									<col width="15%">				
								</colgroup>
								<thead>
									<tr>
										<th>아이템</th>
										<th>Point(P)</th>
									
										<th>횟수(회)</th>
									
										<th>관리</th>
									</tr>
								</thead>
								<tbody class="rl">
								<c:choose>
									<c:when test="${list.size() > 0}">
										<c:forEach var="it" items="${list}">
										<form method="post" name="editPointForm" onsubmit="return false;">	
											<input type="hidden" name="seq" value="${it.seq}">
											<input type="hidden" name="site" value="${site}">
											<tr>
												<td>
												<select name="itemName">
													<option value="MEGAPHONE" ${it.itemName=='MEGAPHONE' ? 'selected="/selected"/' : '' } >확성기</option>
													<%-- <option value="LOGIN" ${it.pointCode=='LOGIN' ? 'selected="/selected"/' : '' }>출석</option> --%>
													
												</select>

												</td>
												<td style="text-align:left; padding-left:10px;">
													<input type="text" class="itext"  name="point1" value="${it.point}">
												</td>
												<td style="text-align:left; padding-left:10px;">
													<input type="text" class="itext" name="limitCount" value="${it.limitCount}">
												</td>
												
												
												<td>
													<button type="button" class="btn-blue" onclick="pointEditForm(this.form);">수정</button>
													<button class="btn-red" onclick="deletePoint(${it.seq});">삭제</button>
												</td>
											</tr>
										</form>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr><td colspan="7" class="empty-data">조회된 데이터가 없습니다.</td></tr>
									</c:otherwise>
								</c:choose>
								</tbody>
							</table>
							
							<div style="margin-top:30px; ">
								
								<form method="post" name="pointForm" onsubmit="return submitForm(this); return false;">
									<input type="hidden" name="seq"  value="0"> 
									<input type="hidden" name="site" value="${site}">
										<table class="list" style="border: 1px solid #ddd;">
											<tr>
												<td style="text-align:left; padding-left:10px;">
													<select name="itemName">
													<option value="MEGAPHONE" ${it.itemName=='MEGAPHONE' ? 'selected="/selected"/' : '' } >확성기</option>
													<%-- <option value="LOGIN" ${it.pointCode=='LOGIN' ? 'selected="/selected"/' : '' }>출석</option> --%>
													
													</select>
												
												</td>
												<td style="text-align:left; padding-left:10px;">
													<input type="text" class="itext" name="point" placeholder="P">
												</td>
												<td style="text-align:left; padding-left:10px;">
													<input type="text" class="itext" name="limitCount1" placeholder="회">
												</td>
											
										
												<td><button class="btn">추가</button>  </td>
											</tr>
										</table>
									</form>		
								</div>
							
						</div>

				</div>
<!-- 			</div> -->
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>