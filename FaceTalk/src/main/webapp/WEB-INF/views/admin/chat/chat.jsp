<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script>


<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(3,1);
	searchList(listForm,1);
});


function searchList(frm,page) {

	var param = {
		page	:	page,
		keyword : frm.keyword.value,
		age : frm.age.value,
		gender : frm.gender.value,
		area : frm.area.value,
		distance : 0,
		latitude : 0,
		longitude : 0,
		admin : 1
	};
	
	$.ajax({
		type:"POST",
		url:"/m/chat_list.go",
		dataType:"html",
		data:param,
		success:function(msg){
			$("#contents-list").html(msg);
		}
	});
	return false;
}
function deleteRoom(chatRoomSeq){
	var param = {
			chatRoomSeq	:	chatRoomSeq
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/chat_room_delete.go",
			dataType:"json",
			data:param,
			success:function(json){
				if(json.result){
					alert(json.message);
					document.location.reload();
				}
				
			}
		});
	
}




function listOrder(colname, sort) {

	listForm.colname.value = colname;
	listForm.sort.value = sort;
	searchList(listForm, 1);
	var cSort ="asc";
	var point ="▼";
	if(sort=='asc'){
		cSort="desc";
		point="▲";
	}
	
	var str = "<button type='button' class='"+colname+"' onclick=\"listOrder('"+colname+"','"+cSort+"');\">"+point+"</button>";
	$("#"+colname).html(str);
}


function order(item,itemValue){
	
	var param = {
			item	:	item,
			itemValue : itemValue,

		};
		
		$.ajax({
			type:"POST",
			url:"/admin/user/siteValue.go",
			dataType:"html",
			data:param,
			success:function(msg){
				

			}
		});
		return false;
	
	
	
}
function openSelectorPop(seq) {
	pop.openWindow("/admin/chat/user_list.go?chatRoomSeq="+seq+"&page=1", 'chatUser', 350, 300, 'yes', 'yes');
}
</script>

<style>
.select-search { border:1px solid #ddd; padding-bottom:4px;}
</style>


</head>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 채팅방관리 > 채팅방 목록
			</header>
		
			<div class="contents-block">
			
				<h1>채팅방 목록</h1>
				
				<div class="contents-main">
					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<input type="hidden" name="gen" value="${gen}">
						<input type="hidden" name="colname" value="reg_date">
						<input type="hidden" name="sort" value="desc">
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool">		
									<input type="text" name="keyword" value="${keyword}" placeholder="검색" class="itext">
									<select name="age" class="list-age no-border">
										<option value="0">연령</option>
										<option value="10">10대</option>
										<option value="20">20대</option>
										<option value="30">30대</option>
										<option value="40">40대</option>
										<option value="50">50대 이상</option>
									</select>
									<select name="gender" class="list-gender no-border">
										<option value="0">성별</option>
										<option value="1">남자</option>
										<option value="2">여자</option>
									</select>
									<select name="area" class="list-area no-border">
										<option value="">지역</option>
										<c:forEach items="${areaList}" var="it">
											<option value="${it.areaSido }" ${area==it.areaSido ? 'selected=\"selected\"' : ''}>${it.areaSido}</option>
										</c:forEach>
									</select>

									<button type="submit" class="btn">검색</button>
								</div>
								<div class="btn-tools">&nbsp;</div>
							</div>
						</div>
						<table class="list">
							<colgroup>
								<col width="3%">
								<col width="3%">
								<col width="5%">
								<col width="*">
								<col width="30%">
								<col width="15%">
								<col width="5%">
								<col width="5%">
								<col width="5%">
								<col width="6%">
								<col width="10%">
								<col width="5%">
							</colgroup>
							<thead>
								<tr>
									<th>확성기</th>
									<th>익명</th>
									<th>운영사이트</th>
									<th>방제목</th>
									<th>참여자</th>
									<th>채팅관리자</th>
									<th>연령</th>
									<th>성별</th>
									<th>지역</th>
									<th>참가자수</th>
									<th>개설일시</th>
									<th>관리</th>
								</tr>
							</thead>
							
						</table>	
						<div id="contents-list"></div>
					</form>
				</div>
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>