<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script>


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

	document.location.href="/admin/user/set_point_charge.go?site="+frm.site.value;	


}
function submitForm(frm) {

	if (frm.chargeMoney.value == "") {
		alert("충전액을 입력해주세요.");
		return false;
	}
	if (frm.chargePoint.value == "") {
		alert("충전포인트를 입력해주세요.");
		return false;
	}
	if (frm.point.value == "") {
		alert("적용포인트를 입력해주세요.");
		return false;
	}
	
	if (frm.comment.value == "") {
		alert("혜택을 입력해주세요.");
		return false;
	}

	if (frm.site.value == "") {
		alert("사이트를 선택해주세요.");
		return false;
	}
	if (frm.code.value == "") {
		alert("코드번호를 입력해주세요.");
		return false;
	}
	var param = {
			chargeSeq : frm.chargeSeq.value,
			chargeMoney : frm.chargeMoney.value,
			chargePoint : frm.chargePoint.value,
			site : frm.site.value,
			point : frm.point.value,
			code : frm.code.value,
			comment : frm.comment.value
			
	};
	
	
	$.ajax({
		type:"POST",
		url:"/admin/user/charge_edit_do.go",
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

function pointChargeForm(frm) {

	if (frm.chargeMoney1.value == "") {
		alert("충전액을 입력해주세요.");
		return false;
	}
	if (frm.chargePoint1.value == "") {
		alert("충전포인트를 입력해주세요.");
		return false;
	}
	if (frm.point1.value == "") {
		alert("적용포인트를 입력해주세요.");
		return false;
	}
	
	if (frm.comment1.value == "") {
		alert("혜택을 입력해주세요.");
		return false;
	}
	if (frm.site.value == "") {
		alert("사이트를 선택해주세요.");
		return false;
	}
	if (frm.code1.value == "") {
		alert("코드를 입력해주세요.");
		return false;
	}
	var param = {
		chargeSeq : frm.chargeSeq.value,
		chargeMoney : frm.chargeMoney1.value,
		chargePoint : frm.chargePoint1.value,
		point : frm.point1.value,
		site : frm.site.value,
		code : frm.code1.value,
		comment : frm.comment1.value
	};
	
	
	$.ajax({
		type:"POST",
		url:"/admin/user/charge_edit_do.go",
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


function deletePoint(chargeSeq) {
	if(confirm("삭제하시겠습니까?")) {
		var param = {
				chargeSeq	:	chargeSeq
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/user/charge_delete_do.go",
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
			
				<h1>포인트 충전 설정</h1>
					
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
									<button type="button" class="tab active" onclick="document.location.href='/admin/user/set_point_charge.go';"><span>포인트 충전 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point_money.go';"><span>포인트/Money 전환 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_level.go';"><span>레벨 정책 설정</span></button>
								<button type="button" class="tab" onclick="document.location.href='/admin/user/charge_item.go';"><span>아이템 설정</span></button>
								</div>	
							</div>
						</div>

						<div id="contents-list">
								<table class="list" style="border: 1px solid #ddd;">
									<colgroup>
										<col width="15%">
										<col width="15%">
										<col width="15%">
										<col width="25%">
										<col width="10%">
										<col width="10%">				
									</colgroup>
									<thead>
										<tr>
											<th>충전액(원)</th>
											<th>충전포인트(P)</th>
											<th>적용포인트(P)</th>
											<th>혜택</th>
											<th>코드</th>
											<th>관리</th>
										</tr>
									</thead>
									<tbody class="rl">
									<c:choose>
										<c:when test="${list.size() > 0}">
											<c:forEach var="it" items="${list}">
											<form method="post" name="pointEditForm" onsubmit="return false;">
												<input type="hidden" name="chargeSeq"  value="${it.chargeSeq}"> 
												<input type="hidden" name="site" value="${site}">
												<tr>
													<td style="text-align:left; padding-left:10px;"><input type="text" class="itext"  name="chargeMoney1" value="${it.chargeMoney}"></td>
													<td style="text-align:left; padding-left:10px;"><input type="text" class="itext" name="chargePoint1" value="${it.chargePoint}"></td>
													<td style="text-align:left; padding-left:10px;"><input type="text" class="itext" name="point1" value="${it.point}"></td>
													<td style="text-align:left; padding-left:10px;"><input type="text" class="itext" name="comment1" value="${it.comment}"></td>
													<td style="text-align:left; padding-left:10px;"><input type="text" class="itext" name="code1" value="${it.code}"></td>
													<td>
														<button type="button" class="btn-blue" onclick="pointChargeForm(this.form);">수정</button>
														<button class="btn-red" onclick="deletePoint(${it.chargeSeq});">삭제</button>
													</td>					
												</tr>
											</form>	
										</c:forEach>
										</c:when>
										<c:otherwise>
										<tr>
											<td colspan="7" style="height:200px;">조회된 데이터가 없습니다.</td>
										</tr>
										</c:otherwise>
									</c:choose>
									</tbody>
								</table>
							
							<div style="margin-top:30px;">
								<h2>포인트 충전 설정</h2> 
								<form method="post" name="pointForm" onsubmit="return submitForm(this); return false;">
									<input type="hidden" name="chargeSeq"  value="0"> 
									<input type="hidden" name="site" value="${site}">
									<table class="list" style="border: 1px solid #ddd;">
										<tr>
											<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="chargeMoney" placeholder="충전액(원)"></td>
											<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="chargePoint" placeholder="충전포인트(P)"></td>
											<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="point" placeholder="적용포인트(P)" ></td>
											<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="comment" placeholder="혜택"></td>
											<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="code" placeholder="코드번호"></td>
											<td><button class="btn">추가</button>  </td>
										</tr>
									</table>
								</form>		
							</div>
							
						</div>
				</div>
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>