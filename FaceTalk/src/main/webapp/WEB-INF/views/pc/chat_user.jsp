<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>


<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(1,9);
	searchList(listForm,1);
});


function searchList(listForm,page) {

	var param = {
		page		:	page,
		keyword : listForm.keyword.value,
		agentId : listForm.agentId.value,
		gender : listForm.gender.value,
		age : listForm.age.value,
		areaSido : listForm.areaSido.value,
		colname : listForm.colname.value,
		sort : listForm.sort.value
	};
	
	$.ajax({
		type:"POST",
		url:"/pc/chat_user_list.go",
		dataType:"html",
		data:param,
		success:function(msg){
			$("#contents-list").html(msg);
		}
	});
	return false;
}


function searchArea(frm) {
	
	if(frm.areaSido.value == "0"){
		$('areaSido').find('option:first').attr('selected', 'selected');
	}
	
	return false;
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
function useThisId(userId,userName) {

	
	
	opener.searchForm.chatId.value = userId;
	
	opener.searchForm.chatName.value = userName;
	
	opener.reloadPage();
	
	//opener.changeChatId();
	
	localStorage.setItem("chatId", userId);

	//opener.location.reload();
	window.close();
}

</script>

<style>
.select-search { border:1px solid #ddd; padding-bottom:4px;}
</style>


</head>
<body>

<section class="main-cover main-row" style="width:800px;min-width:800px;">
	<section id="main">
		
		<section id="contents">
		
			<div class="contents-block">
			
				<h1>${user.userName}님의 가상회원</h1>
				
				<div class="contents-main">
					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<input type="hidden" name="agentId" value="${user.userId}">
						<input type="hidden" name="colname" value="reg_date">
						<input type="hidden" name="sort" value="desc">
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool" style="width:90%;">		
									<input type="text" name="keyword" value="${keyword}" placeholder="아이디 검색" class="itext">
									<select name="gender"  class="select-search">
										<option value="0">=성별전체=</option>
										<option value="1">남자</option>
										<option value="2">여자</option>								
									</select>
									<select name="age" id="age" style="width:106px;" class="select-search">
										<option value="0">=나이전체=</option>
										<option value="1">10대</option>
										<option value="2">20대</option>	
										<option value="3">30대</option>
										<option value="4">40대</option>	
										<option value="5">50대이상</option>	
									</select>
									<select onchange="searchArea(this.form);" name="areaSido" style="width:106px;" class="select-search">
										<option value="">=지역선택=</option>
										<c:forEach items="${location}" var="it" >
											<option value="${it.areaSido}">${it.areaSido}</option>								
										</c:forEach>		
									</select>

									<button type="submit" class="btn">검색</button>
								</div>
								<!-- <div class="btn-tools"><button class="btn-blue" onclick="document.location.href='/admin/user/user_imagine_edit.go?userSeq=0';">가상 회원 등록</button></div> -->
								<div class="btn-tools"><button class="btn-blue" onclick="document.location.href='/pc/chat_user_edit.go?userSeq=0&userId=${user.userId}';">가상 회원 등록</button></div>
							</div>
						</div>
						<table class="list">
							<colgroup>
								<col width="8%">
								<col width="*">
								<col width="15%">
								<col width="15%">
								<col width="7%">
								<col width="7%">
								<col width="7%">
								<col width="15%">
								<col width="10%">
							</colgroup>
								<thead>
									<th>이미지 </th>
									<th>아이디
										<span id="user_id">
											 <button type="button" onclick="listOrder('user_id','asc');" class="user_id" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>휴대폰 번호
										<span id="phone_number">
											 <button type="button" onclick="listOrder('phone_number','asc');" class="phone_number" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>이름 
										<span id="user_name">
										<button type="button" onclick="listOrder('user_name','asc');" class="user_name" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>나이
										<span id="birth_year">
										 <button type="button" onclick="listOrder('birth_year','asc');" class="birth_year" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>성별 
										<span id="gender">
										<button type="button" onclick="listOrder('gender','asc');" class="gender" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>지역 
										<span id="area">
										<button type="button" onclick="listOrder('area','asc');" class="area" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>등록일자 
										<span id="reg_date">
										<button type="button" onclick="listOrder('reg_date','asc');" class="reg_date" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>사용하기</th>
								</thead>
							</table>
						<div id="contents-list">
						</div>
					
					</form>
				</div>
			</div>
		</section>
	</section>
</section>


</body>
</html>