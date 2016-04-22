<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script>


<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(2,1);
	searchList(listForm,1);
});


function searchList(listForm,page) {
	
	var param = {
		site		: 	listForm.site.value,
		keyword		: 	listForm.keyword.value,
		gender : listForm.gender.value,
		startDate   :   listForm.startDate.value,
		endDate     :   listForm.endDate.value,
		age : listForm.age.value,
		areaSido : listForm.areaSido.value,
		categorySeq : listForm.categorySeq.value,
		reportCount :listForm.reportCount.checked,
		blindCount :listForm.blindCount.checked,
		sort : listForm.sort.value,
		colName : listForm.colName.value,
		page		:	page
		
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/talk/talk_list.go",
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


$(function() {
    $.datepicker.setDefaults( $.datepicker.regional[ "ko" ] );
    $( ".datepicker" ).datepicker(
        {
        	dateFormat: 'yy-mm-dd',
 	       showButtonPanel: true
        }
    );
}); 


</script>

<style>
table.menuedit th {
   vertical-align: top;
   text-align :left;
   padding:5px;
   font-weight: bold;
}

table.menuedit td {
	padding:5px;
	text-align :left;
    vertical-align: top;
    padding: 3px;
    border-bottom: 1px solid #ddd;
}	


</style>


</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 토크관리 > 토크목록
			</header>
		
			<div class="contents-block">
			
				<h1>토크목록</h1>
				
				<div class="contents-main">

					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						
						<div class="contents-top">
						
							<div class="top-tools">
								<div class="search-tool">
									<input type="text" name="keyword" value="${keyword}" placeholder="내용/닉네임 검색" class="itext">
									

									<select name="gender" id="gender" class="select-search">
										<option value="0">=성별전체=</option>
										<option value="1">남자</option>
										<option value="2">여자</option>								
									</select>

									<select name="age" id="age" style="width:106px;" class="select-search">
										<option value="0">=나이 전체=</option>
										<option value="1">10대</option>
										<option value="2">20대</option>	
										<option value="3">30대</option>
										<option value="4">40대</option>	
										<option value="5">50대이상</option>	
									</select>

									<select onchange="searchArea(this.form);" name="areaSido" style="width:106px;" class="select-search">
										<option value="">=지역 선택=</option>
										<c:forEach items="${location}" var="it" >
											<option value="${it.areaSido}">${it.areaSido}</option>								
										</c:forEach>		
									</select>
									<select name="categorySeq" class="select-search" >
										<option value="0">=토크주제=</option>	
										<c:forEach items="${cateList }" var="it">
											
												<option value="${it.categorySeq }">${it.categoryName }</option>	
											
										</c:forEach>
									</select>
									<select name="colName" class="select-search" >
										<option value="" selected="selected">=정렬명=</option>	
										<option value="user_name">이름</option>	
										<option value="like_count">좋아요</option>	
										<option value="comment_count">댓글</option>	
										<option value="bbscom_count">댓글의 댓글</option>
										<option value="report_count">신고 수</option>
										<option value="reg_date" >날짜</option>	
									</select>
									
									<select name="sort">
										<option value="" selected="selected">=정렬순서=</option>	
										<option value="asc">오름차순</option>
										<option value="desc">내림차순</option>
									</select>
									<c:choose>
									<c:when test="${USER_TYPE==1}">
									<select name="site">
										<option value="" selected="selected">=사이트=</option>	
										<c:forEach	items="${sitelist}" var="it">
											<option value="${it.serviceId}">${it.siteName}</option>	
										</c:forEach>
									</select>
									</c:when>
									<c:otherwise>
										<input type="hidden" name ="site" value="">
									</c:otherwise>
									</c:choose>
									게시기간
									<input type="text" name="startDate" class="itext datepicker" value="${startDate}" placeholder="시작일" style="width:80px;" onchange="startChange(listForm);">
									<input type="text" name="endDate" class="itext datepicker" value="${endDate}" placeholder="종료일" style="width:80px;">
									
									<label style="margin-right:10px;"><input type="checkbox" name="reportCount" >신고 게시글</label>
									<label style="margin-right:10px;"><input type="checkbox" name="blindCount" >블라인드 게시글</label>
									<button type="submit" class="btn">검색</button>
								</div>
<!-- 								<div class="btn-tools"><button class="btn">엑셀 다운로드</button></div> -->
<!-- 								<div class="btn-tools"><button class="btn" onclick="document.location.href='/admin/firm/firm_edit.go';">신규 업체 등록</button></div> -->
							</div>
						</div>

						<div id="contents-list" style="background-color:#eee;">
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