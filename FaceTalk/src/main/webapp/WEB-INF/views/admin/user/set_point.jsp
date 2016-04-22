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

	document.location.href="/admin/user/set_point.go?site="+frm.site.value;	


}

function submitForm(frm) {

	if (frm.eventName.value == "") {
		alert("이벤트 이름을 입력해주세요.");
		return false;
	}
	if (frm.point.value == "") {
		alert("포인트를 입력해주세요.");
		return false;
	}
	if (frm.money.value == "") {
		alert("CASH를 입력해주세요.");
		return false;
	}
	
	if (frm.times.value == "") {
		alert("횟수를 선택해주세요.");
		return false;
	}
	if (frm.site.value == "") {
		alert("사이트를 선택해주세요.");
		return false;
	}
	var param = {
			pointSeq : frm.pointSeq.value,
			eventName : frm.eventName.value,
			point : frm.point.value,
			money : frm.money.value,
			period : frm.period.value,
			times : frm.times.value,
			commend : frm.commend.value,
			site : frm.site.value,
			pointCode : frm.pointCode.value
			
	};
	
	
	$.ajax({
		type:"POST",
		url:"/admin/user/point_edit_do.go",
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

	if (frm.eventName1.value == "") {
		alert("이벤트 이름을 입력해주세요.");
		return false;
	}
	if (frm.point1.value == "") {
		alert("포인트를 입력해주세요.");
		return false;
	}
	if (frm.money1.value == "") {
		alert("CASH를 입력해주세요.");
		return false;
	}
	if (frm.site.value == "") {
		alert("사이트를 선택해주세요.");
		return false;
	}
	if (frm.times1.value == "") {
		alert("횟수를 선택해주세요.");
		return false;
	}
	
	var param = {
			pointSeq : frm.pointSeq.value,
			pointCode : frm.pointCode.value,
			eventName : frm.eventName1.value,
			point : frm.point1.value,
			money : frm.money1.value,
			period : frm.period1.value,
			site : frm.site.value,
			times : frm.times1.value,
			commend : frm.commend1.value
	};
	
	
	$.ajax({
		type:"POST",
		url:"/admin/user/point_edit_do.go",
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

function deletePoint(pointSeq) {
	if(confirm("삭제하시겠습니까?")) {
		var param = {
				pointSeq	:	pointSeq
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/user/point_delete_do.go",
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
			
				<h1>포인트 설정</h1>
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
									<button type="button" class="tab active" onclick="document.location.href='/admin/user/set_point.go';"><span>포인트 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point_charge.go';"><span>포인트 충전 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point_money.go';"><span>포인트/Money 전환 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_level.go';"><span>레벨 정책 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/charge_item.go';"><span>아이템 설정</span></button>
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
									<col width="15%">				
								</colgroup>
								<thead>
									<tr>
										<th>이벤트</th>
										<th>Point(P)</th>
										<th>CASH</th>
										<th>횟수(회)</th>
										<th>비고</th>
										<th>관리</th>
									</tr>
								</thead>
								<tbody class="rl">
								<c:choose>
									<c:when test="${list.size() > 0}">
										<c:forEach var="it" items="${list}">
										<form method="post" name="editPointForm" onsubmit="return false;">	
											<input type="hidden" name="pointSeq" value="${it.pointSeq}">
											<input type="hidden" name="site" value="${site}">
											<tr>
												<td>
												<select name="pointCode">
													<option value="JOIN" ${it.pointCode=='JOIN' ? 'selected="/selected"/' : '' } >회원가입</option>
													<option value="LOGIN" ${it.pointCode=='LOGIN' ? 'selected="/selected"/' : '' }>출석</option>
													<option value="7LOGIN" ${it.pointCode=='7LOGIN' ? 'selected="/selected"/' : '' }>7일출석</option>
													<option value="CHAT" ${it.pointCode=='CHAT' ? 'selected="/selected"/' : '' }>채팅신청</option>
													<option value="APCHAT" ${it.pointCode=='APCHAT' ? 'selected="/selected"/' : '' }>채팅수락(여)</option>
													<option value="TALK" ${it.pointCode=='TALK' ? 'selected="/selected"/' : '' }>토크쓰기</option>
													<option value="COMMENT" ${it.pointCode=='COMMENT' ? 'selected="/selected"/' : '' }>댓글쓰기</option>
													<option value="BAD" ${it.pointCode=='BAD' ? 'selected="/selected"/' : '' }>신고하기</option>
													<option value="PHOTO" ${it.pointCode=='PHOTO' ? 'selected="/selected"/' : '' }>프로필사진등록</option>
													<option value="NAME" ${it.pointCode=='NAME' ? 'selected="/selected"/' : '' }>닉네임변경</option>
													<option value="ONECHAT" ${it.pointCode=='ONECHAT' ? 'selected="/selected"/' : '' }>채팅방생성(1:1)</option>
													<option value="ANYONECHAT" ${it.pointCode=='ANYONECHAT' ? 'selected="/selected"/' : '' }>채팅방생성(1:N)</option>
													<option value="NONAMECHAT" ${it.pointCode=='NONAMECHAT' ? 'selected="/selected"/' : '' }>채팅방생성(익명)</option>
													<option value="LEVELUP" ${it.pointCode=='LEVELUP' ? 'selected="/selected"/' : '' }>레벨업</option>
												</select>
											<!-- 	</td> -->
												<!-- <td style="text-align:left; padding-left:10px;"> -->
													<input type="text" class="itext" name="eventName1" value="${it.eventName}">
												</td>
												<td style="text-align:left; padding-left:10px;">
													<input type="text" class="itext"  name="point1" value="${it.point}">
												</td>
												<td style="text-align:left; padding-left:10px;">
													<input type="text" class="itext" name="money1" value="${it.money}">
												</td>
												<td style="text-align:left; padding-left:10px;">
													<select name="period1" style="width:80px;" class="select-search">
														<option value="1" ${it.period == 1 ? 'selected=\"selected\"' : ''}>최초</option>
														<option value="2" ${it.period == 2 ? 'selected=\"selected\"' : ''}>매번</option>
														<option value="3" ${it.period == 3 ? 'selected=\"selected\"' : ''}>건별</option>	
														<option value="4" ${it.period == 4 ? 'selected=\"selected\"' : ''}>1일</option>
													</select>
													<input type="text" class="itext" name="times1" value="${it.times}" style="width:40px;  margin-left:10px;">
												</td>
												<td><input type="text" class="itext" name="commend1" value="${it.commend}"></td>
												<td>
													<button type="button" class="btn-blue" onclick="pointEditForm(this.form);">수정</button>
													<button class="btn-red" onclick="deletePoint(${it.pointSeq});">삭제</button>
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
									<h2>포인트 정책 등록</h2>
									<span style="color: red;padding-left: 20px;"><b>1. 레벨업 설정시 비고 란에 해당 레벨을 적어주세요.ex) 5Lv -> 5</b>	</span>
									<br><span style="color: red;padding-left: 20px;"><b>2. 레벨업을 제외한 정책은 한가지만 가능합니다.</b></span>
								
								<form method="post" name="pointForm" onsubmit="return submitForm(this); return false;">
									<input type="hidden" name="pointSeq"  value="0"> 
									<input type="hidden" name="site"  value="${site}"> 
										<table class="list" style="border: 1px solid #ddd;">
											<tr>
												<td style="text-align:left; padding-left:10px;">
													<select name="pointCode">
														<option value="JOIN" ${it.pointCode=='JOIN' ? 'selected="/selected"/' : '' } >회원가입</option>
														<option value="LOGIN" ${it.pointCode=='LOGIN' ? 'selected="/selected"/' : '' }>출석</option>
														<option value="7LOGIN" ${it.pointCode=='7LOGIN' ? 'selected="/selected"/' : '' }>7일출석</option>
														<option value="CHAT" ${it.pointCode=='CHAT' ? 'selected="/selected"/' : '' }>채팅신청</option>
														<option value="APCHAT" ${it.pointCode=='APCHAT' ? 'selected="/selected"/' : '' }>채팅수락(여)</option>
														<option value="TALK" ${it.pointCode=='TALK' ? 'selected="/selected"/' : '' }>토크쓰기</option>
														<option value="COMMENT" ${it.pointCode=='COMMENT' ? 'selected="/selected"/' : '' }>댓글쓰기</option>
														<option value="BAD" ${it.pointCode=='BAD' ? 'selected="/selected"/' : '' }>신고하기</option>
														<option value="PHOTO" ${it.pointCode=='PHOTO' ? 'selected="/selected"/' : '' }>프로필사진등록</option>
														<option value="NAME" ${it.pointCode=='NAME' ? 'selected="/selected"/' : '' }>닉네임변경</option>
														<option value="ONECHAT" ${it.pointCode=='ONECHAT' ? 'selected="/selected"/' : '' }>채팅방생성(1:1)</option>
														<option value="ANYONECHAT" ${it.pointCode=='ANYONECHAT' ? 'selected="/selected"/' : '' }>채팅방생성(1:N)</option>
														<option value="NONAMECHAT" ${it.pointCode=='NONAMECHAT' ? 'selected="/selected"/' : '' }>채팅방생성(익명)</option>
														<option value="LEVELUP" ${it.pointCode=='LEVELUP' ? 'selected="/selected"/' : '' }>레벨업</option>
													</select>
													<input type="text" class="itext" name="eventName" placeholder="event">
												</td>
												<td style="text-align:left; padding-left:10px;">
													<input type="text" class="itext" name="point" placeholder="P">
												</td>
												<td style="text-align:left; padding-left:10px;">
													<input type="text" class="itext" name="money" placeholder="C">
												</td>
												<td style="text-align: left; padding-left:10px;">
													<select name="period" style="width:80px;" class="select-search">
														<option value="1" >최초</option>
														<option value="2">매번</option>
														<option value="3">건별</option>	
														<option value="4">1일</option>
													</select>
													<input type="text" class="itext" name="times" style="width:40px; margin-left:10px;" placeholder="회">
												</td>
												<td style="text-align: left; padding-left:10px;">
													<input type="text" class="itext" name="commend" placeholder="비고">
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