<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script>


<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(1,7);
	searchList(listForm,1);
});


function searchList(listForm,page) {

	var param = {
		page		:	page,
		keyword : listForm.keyword.value,
		site : listForm.site.value,
		gender : listForm.gender.value,
		gen : listForm.gen.value,
		age : listForm.age.value,
		areaSido : listForm.areaSido.value,
		colname : listForm.colname.value,
		sort : listForm.sort.value
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/user/user_drop_list.go",
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

//액셀 다운로드
function excelDownload() {
	var frm = listForm;
	document.location.href = "/admin/user/use_list_excel.go";
}

//유저 데이터 전체삭제
function deleteAll() {

	var arr = new Array();
	var arrSeq = "" ; 
	
	$.each($(".userId"), function( index, obj ) {

		if (($('.userId').eq(index).is(":checked"))) {
        	arr.push($(obj).val());
		}


    });
	
	arrSeq = arr.join(",");
	
	if(arrSeq.length==0){
		alert("회원을 선택해주세요");
		return false;
	}
	 var param = {

             arrSeq  : arrSeq

     };

		
		$.ajax({
			type:"POST",
			url:"/admin/user/user_delete_all.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if(json.result==true){
					document.location.reload();
				}
		
			}
		}); 
	
}
</script>

<style>
.select-search { border:1px solid #ddd; padding-bottom:4px;}
</style>


</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 회원관리 > 탈퇴회원
			</header>
		
			<div class="contents-block">
			
				<h1>탈퇴회원</h1>
				
				<div class="contents-main">
					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<input type="hidden" name="gen" value="${gen}">
						<input type="hidden" name="colname" value="reg_date">
						<input type="hidden" name="sort" value="desc">
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool">		
									<input type="text" name="keyword" value="${keyword}" placeholder="아이디 검색" class="itext">
									<c:choose>
										<c:when test="${USER_TYPE==1}">
										<select name="site" style="width:200px;" class="select-search">
											<option value="">=운영사이트=</option>
											<c:forEach items="${site}" var="it" >
												<option value="${it.serviceId}">${it.siteName}</option>								
											</c:forEach>		
										</select>
										</c:when>
										<c:otherwise>
											<input type="hidden" name="site" value="">
										</c:otherwise>
									</c:choose>
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
								<div class="btn-tools"><button class="btn-red" onclick="deleteAll();">선택삭제</button></div> 
								
								
							</div>
						</div>
						<table class="list">
							<colgroup>
								<col width="3%">
								<col width="3%">
								<col width="5%">
								<col width="5%">
								<col width="10%">
								<col width="10%">
								<col width="5%">
								<col width="5%">
								<col width="5%">
								<col width="5%">
								<col width="5%">
								<col width="5%">
								<col width="7%">
								<col width="7%">
								
							</colgroup>
								<thead>
									<th>선택 
										
									</th>
									<th>번호 
										<span id="user_seq">
											<button type="button" onclick="listOrder('user_seq','asc');" class="user_seq" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>운영사이트 
									<span id="site_name">
										<button type="button" onclick="listOrder('site_name','asc');" class="site_name" style="margin-top:3px;">▼</button>
									</span>
									</th>
									<th>이미지 </th>
									<th>아이디
										<span id="user_id">
											 <button type="button" onclick="listOrder('user_id','asc');" class="user_id" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>탈퇴사유
										<span id="drop_reason">
											 <button type="button" onclick="listOrder('drop_reason','asc');" class="drop_reason" style="margin-top:3px;">▼</button>
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
									<th>포인트(P) 
										<span id="point">
										<button type="button" onclick="listOrder('point','asc');" class="point" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>CASH
										<span id="income">
										<button type="button" onclick="listOrder('income','asc');" class="income" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>최근 접속일 
										<span id="last_logindate">
										<button type="button" onclick="listOrder('last_logindate','asc');" class="last_logindate" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>등록일자 
										<span id="reg_date">
										<button type="button" onclick="listOrder('reg_date','asc');" class="reg_date" style="margin-top:3px;">▼</button>
										</span>
									</th>
									
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

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>