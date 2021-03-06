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




function submitForm(frm) {

	if (frm.levelNum.value == "") {
		alert("레벨를 입력해주세요.");
		return false;
	}
	if (frm.benefit.value == "") {
		alert("아이템을 입력해주세요.");
		return false;
	}
	if (frm.period.value == "") {
		alert("최대 경험치를 입력해주세요.");
		return false;
	}
	if (frm.limitCount.value == "") {
		alert("채팅 방 개설 수를 입력해주세요.");
		return false;
	}
	
	var param = {
			itemSeq : frm.itemSeq.value,
			levelNum : frm.levelNum.value,
			benefit : frm.benefit.value,
			period : frm.period.value,
			limitCount : frm.limitCount.value
	};
	
	
	$.ajax({
		type:"POST",
		url:"/admin/user/item_edit_do.go",
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




function submitLevelForm(frm) {

	if (frm.levelNum1.value == "") {
		alert("레벨를 입력해주세요.");
		return false;
	}
	if (frm.benefit1.value == "") {
		alert("아이템을 입력해주세요.");
		return false;
	}
	if (frm.period1.value == "") {
		alert("최대 경험치를 입력해주세요.");
		return false;
	}
	
	if (frm.limitCount1.value == "") {
		alert("최대 경험치를 입력해주세요.");
		return false;
	}
	
	var param = {
			itemSeq : frm.itemSeq.value,
			levelNum : frm.levelNum1.value,
			benefit : frm.benefit1.value,
			period : frm.period1.value,
			limitCount : frm.limitCount1.value
	};
	
	
	$.ajax({
		type:"POST",
		url:"/admin/user/item_edit_do.go",
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

function deletePoint(itemSeq) {
	if(confirm("삭제하시겠습니까?")) {
		var param = {
				itemSeq	:	itemSeq
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/user/item_delete_do.go",
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
				 ■ 홈 > 회원관리 > 레벨 아이템 정책
			</header>
		
			<div class="contents-block">
			
				<h1>레벨 아이템 정책 설정</h1>
				
<!-- 				<div class="contents-main"> -->
						<div class="contents-top">
							<div class="top-tools">
								<div class="tab-bar">
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point.go';"><span>포인트 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point_charge.go';"><span>포인트 충전 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point_money.go';"><span>포인트/Money 전환 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_level.go';"><span>레벨 정책 설정</span></button>
									<button type="button" class="tab active" onclick="document.location.href='/admin/user/level_item.go';"><span>레벨 아이템</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/charge_item.go';"><span>구매 아이템</span></button>
								</div>		
							</div>
						</div>

						<div id="contents-list">
							<table class="list" style="border: 1px solid #ddd;">
								<colgroup>
									<col width="25%">
									<col width="15%">
									<col width="20%">
									<col width="20%">
									<col width="15%">			
								</colgroup>
								<thead>
									<tr>
										<th>레벨(Lv)</th>
										<th>아이템</th>
										<th>기간</th>
										<th>채팅 방 개설 수</th>
										<th>관리</th>
									</tr>
								</thead>
								<tbody class="rl">
								<c:choose>
									<c:when test="${list.size() > 0}">
										<c:forEach var="it" items="${list}">
										<form method="post" name="levelForm" onsubmit="return false;">
											<input type="hidden" name="itemSeq"  value="${it.itemSeq}"> 
											<tr>
												<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="levelNum1" value="${it.levelNum}"></td>
												<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="benefit1" value="${it.benefit}"></td>
												<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="period1" value="${it.period}"></td>
												<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="limitCount1" value="${it.limitCount}"></td>
												<td>
													<button type="button" class="btn-blue" onclick="submitLevelForm(this.form);">수정</button>
													<button class="btn-red" onclick="deletePoint(${it.itemSeq});">삭제</button>
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

							</div>		
							<div style="margin-top:30px;">
								<h2>아이템 정책 설정 등록</h2>
								<form method="post" name="pointForm" onsubmit="return submitForm(this); return false;">
									<input type="hidden" name="itemSeq"  value="0"> 
									<table class="list" style="border: 1px solid #ddd;">
										<tr>
											<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="levelNum" placeholder="레벨"></td>
											<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="benefit" placeholder="아이템"></td>
											<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="period" placeholder="기간"></td>
											<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="limitCount" placeholder="채팅방 개설 수"></td>
											<td><button class="btn">추가</button></td>	
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