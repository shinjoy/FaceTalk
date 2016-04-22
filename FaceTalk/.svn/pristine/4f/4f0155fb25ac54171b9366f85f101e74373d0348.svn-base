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

	if (frm.level.value == "") {
		alert("레벨를 입력해주세요.");
		return false;
	}
	if (frm.minEx.value == "") {
		alert("최소 경험치를 입력해주세요.");
		return false;
	}
	if (frm.maxEx.value == "") {
		alert("최대 경험치를 입력해주세요.");
		return false;
	}
	
	
	var param = {
			levelSeq : frm.levelSeq.value,
			level : frm.level.value,
			minEx : frm.minEx.value,
			maxEx : frm.maxEx.value
	};
	
	
	$.ajax({
		type:"POST",
		url:"/admin/user/level_edit_do.go",
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

	if (frm.level1.value == "") {
		alert("레벨를 입력해주세요.");
		return false;
	}
	if (frm.minEx1.value == "") {
		alert("최소 경험치를 입력해주세요.");
		return false;
	}
	if (frm.maxEx1.value == "") {
		alert("최대 경험치를 입력해주세요.");
		return false;
	}
	
	
	var param = {
			levelSeq : frm.levelSeq.value,
			level : frm.level1.value,
			minEx : frm.minEx1.value,
			maxEx : frm.maxEx1.value
	};
	
	
	$.ajax({
		type:"POST",
		url:"/admin/user/level_edit_do.go",
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

function deletePoint(levelSeq) {
	if(confirm("삭제하시겠습니까?")) {
		var param = {
				levelSeq	:	levelSeq
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/user/level_delete_do.go",
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
			
				<h1>레벨 정책 설정</h1>
				
<!-- 				<div class="contents-main"> -->
						<div class="contents-top">
							<div class="top-tools">
								<div class="tab-bar">
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point.go';"><span>포인트 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point_charge.go';"><span>포인트 충전 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point_money.go';"><span>포인트/Money 전환 설정</span></button>
									<button type="button" class="tab active" onclick="document.location.href='/admin/user/set_level.go';"><span>레벨 정책 설정</span></button>
							<button type="button" class="tab" onclick="document.location.href='/admin/user/charge_item.go';"><span>아이템 설정</span></button>
								</div>		
							</div>
						</div>

						<div id="contents-list">
							<table class="list" style="border: 1px solid #ddd;">
								<colgroup>
									<col width="25%">
									<col width="15%">
									<col width="20%">
									<col width="15%">			
								</colgroup>
								<thead>
									<tr>
										<th>레벨(Lv)</th>
										<th>최소 경험치(M)</th>
										<th>최대 경험치(M)</th>
										<th>관리</th>
									</tr>
								</thead>
								<tbody class="rl">
								<c:choose>
									<c:when test="${list.size() > 0}">
										<c:forEach var="it" items="${list}">
										<form method="post" name="levelForm" onsubmit="return false;">
											<input type="hidden" name="levelSeq"  value="${it.levelSeq}"> 
											<tr>
												<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="level1" value="${it.level}"></td>
												<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="minEx1" value="${it.minEx}"></td>
												<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="maxEx1" value="${it.maxEx}"></td>
												<td>
													<button type="button" class="btn-blue" onclick="submitLevelForm(this.form);">수정</button>
													<button class="btn-red" onclick="deletePoint(${it.levelSeq});">삭제</button>
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
								<h2>레벨 정책 설정 등록</h2>
								<form method="post" name="pointForm" onsubmit="return submitForm(this); return false;">
									<input type="hidden" name="levelSeq"  value="0"> 
									<table class="list" style="border: 1px solid #ddd;">
										<tr>
											<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="level" placeholder="레벨"></td>
											<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="minEx" placeholder="최소 경험치"></td>
											<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="maxEx" placeholder="최대 경험치"></td>
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