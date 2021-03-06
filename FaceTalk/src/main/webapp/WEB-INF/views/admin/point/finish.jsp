<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script>


<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(8,3);
	searchList(listForm,1);
});

$(function() {

    $.datepicker.setDefaults( $.datepicker.regional[ "ko" ] );

    $( ".datepicker" ).datepicker(

        {

               showButtonPanel: true,

               dateFormat: 'yy-mm-dd'

        }

    );

});
function searchList(frm,page) {
	
	var startDate = frm.startDate.value;
	var endDate = frm.endDate.value;
	
	var param = {
		page		:	page,
		keyword : frm.keyword.value,
		colName : frm.colname.value,
		sort : frm.sort.value,
	
		status : 1,
		startDate : startDate,
		endDate : endDate
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/point/finish_list.go",
		dataType:"html",
		data:param,
		success:function(msg){
			$("#contents-list").html(msg);
		}
	});
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
	function excelDownload(frm) {
		
		frm.action = "/admin/point/point_list_excel.go";
		frm.submit();
	}

	function chkAll(obj) {

		if ($(obj).is(":checked")) {
			$.each($(".chkok"), function(i, v) {
				$(".chkok").attr("checked", true);
			});

		} else {
			$.each($(".chkok"), function(i, v) {
				$(".chkok").attr("checked", false);
			});
		}
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
				 ■ 홈 > 포인트관리 > 환전완료
			</header>
		
			<div class="contents-block">
			
				<h1>환전완료</h1>
				
				<div class="contents-main">
					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<input type="hidden" name="colname" value="request_date">
						<input type="hidden" name="sort" value="desc">
						<input type="hidden" name="type" value="fmoney">
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool">		
									<input type="text" name="keyword" value="${keyword}" placeholder="아이디 검색" class="itext">
									
									<input type="text" name="startDate" class="itext datepicker"  placeholder="시작일" style="width:80px;">
									<input type="text" name="endDate" class="itext datepicker"  placeholder="종료일" style="width:80px;">
									<button type="submit" class="btn">검색</button>
								</div>
								
								<div class="btn-tools"><button class="btn-green" onclick="excelDownload(this.form);">엑셀 다운로드</button></div>
							</div>
						</div>
						<table class="list">
							<colgroup>
								<col width="5%"> 
								<col width="5%">
								<col width="10%">
								<col width="5%">
								<col width="10%">
								<col width="10%">
								<col width="20%">
								<col width="5%">
								<col width="5%">
							</colgroup>
								<thead>
									
								   <th>요청번호
										<span id="expense_seq">
											 <button type="button" onclick="listOrder('expense_seq','asc');" class="type" style="margin-top:3px;">▼</button>
										</span>
									</th>
									  <th>운영사이트
										<span id="site_name">
											 <button type="button" onclick="listOrder('site_name','asc');" class="type" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>아이디
										<span id="user_id">
											 <button type="button" onclick="listOrder('user_id','asc');" class="user_id" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>이름 
										<span id="user_name">
										<button type="button" onclick="listOrder('user_name','asc');" class="user_name" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>머니 
										<span id="point">
										<button type="button" onclick="listOrder('point','asc');" class="point" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>환전금액 
										<span id="pay_point">
										<button type="button" onclick="listOrder('pay_point','asc');" class="pay_point" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>비고 
										<span id="comment">
										<button type="button" onclick="listOrder('comment','asc');" class="comment" style="margin-top:3px;">▼</button>
										</span>
									</th>
									
									<th>요청날짜 
										<span id="request_date">
										<button type="button" onclick="listOrder('request_date','asc');" class="request_date" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>환급날짜 
										<span id="finish_date">
										<button type="button" onclick="listOrder('finish_date','asc');" class="finish_date" style="margin-top:3px;">▼</button>
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