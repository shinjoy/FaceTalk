<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>

<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(1,2);
	searchList(listForm,1);
});


function searchList(listForm,page) {
	var param = {
		page		:	page,
		keyword : listForm.keyword.value,
		site : listForm.site.value,
		userType : listForm.userType.value,
		colname : listForm.colname.value,
		sort : listForm.sort.value
		
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/user/admin_list.go",
		dataType:"html",
		data:param,
		success:function(msg){
			$("#contents-list").html(msg);
		}
	});
	return false;
}

$(function() {
    $.datepicker.setDefaults( $.datepicker.regional[ "ko" ] );
    $( ".datepicker" ).datepicker(
        {
        	dateFormat: 'yy-mm-dd',
 	       showButtonPanel: true
        }
    );
}); 

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


function searchCategory(frm) {
	
	if(frm.category.value == "10"){
		$('categoryName').find('option:first').attr('selected', 'selected');
	}
	
	var param = {
		parentSeq	: frm.category.value
	};
	
			
	$.ajax({
		type:"POST",
		url:"/admin/catory/catory.go",
		dataType:"json",
		data:param,
		success:function(json){
			var list = json.list;
			var str = '<option value="">== 검색결과 ==</option>';
			
			for (var i=0; i<list.length; i++) {
				str += '<option value="'+list[i].cateSeq+'">'+list[i].categoryName+'</option>';
			}
			$("#categoryName").html(str);
		}
	});
 
	return false;
}

//액셀 다운로드
function excelDownload() {
	var frm = listForm;
	document.location.href = "/admin/user/admin_list_excel.go";
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
				 ■ 홈 > 회원관리 > 관리자
			</header>
		
			<div class="contents-block">
			
				<h1>관리자</h1>
				
				<div class="contents-main">

					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<input type="hidden" name="colname" value="reg_date">
						<input type="hidden" name="sort" value="desc">
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool">	
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
									<select name="userType" style="width:200px;" class="select-search">
										<option value="0">=관리 레벨=</option>	
										<option value="1">최고관리자</option>
										<option value="2">일반관리자</option>
										<option value="3">조회관리자</option>					
		
									</select>
									<input type="text" name="keyword" value="${keyword}" placeholder="아이디/관리자명 검색" class="itext">
									<button type="submit" class="btn">검색</button>
								</div>
								<div class="btn-tools"><button class="btn-blue" onclick="document.location.href='/admin/admin_insert.go?seq=0';">신규 관리자 등록</button></div>
								<div class="btn-tools"><button class="btn-green" onclick="excelDownload()">엑셀다운로드</button></div>
							</div>
						</div>
						
						<table class="list">
							<colgroup>
								<col width="15%">
								<col width="15%">
								<col width="20%">
								<col width="*">
								<col width="10%">
							</colgroup>
								<thead>
									<tr>
										<th>운영사이트 
											<span id="site_name">
												<button type="button" onclick="listOrder('site_name','asc');" class="site_name" style="margin-top:3px;">▼</button>
											</span>
										</th>
					
										<th>관리레벨 
											<span id="user_type">
											<button type="button" onclick="listOrder('user_type','asc');" class="user_type" style="margin-top:3px;">▼</button>
											</span>
										</th>
										
										<th>관리자명 
											<span id="user_name">
											<button type="button" onclick="listOrder('user_name','asc');" class="user_name" style="margin-top:3px;">▼</button>
											</span>
										</th>
										<th>아이디
											<span id="user_id">
												 <button type="button" onclick="listOrder('user_id','asc');" class="user_id" style="margin-top:3px;">▼</button>
											</span>
										</th>
										<th>등록일자 
											<span id="reg_date">
											<button type="button" onclick="listOrder('reg_date','asc');" class="reg_date" style="margin-top:3px;">▼</button>
											</span>
										</th>
									</tr>
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