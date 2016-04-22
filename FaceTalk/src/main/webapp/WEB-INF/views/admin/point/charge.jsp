<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script>


<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(8,7);
	
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
		type : frm.type.value,
		startDate : startDate,
		endDate : endDate
		
	};

	$.ajax({
		type:"POST",
		url:"/admin/point/charge_list.go",
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
		
		frm.action = "/admin/point/order_list_excel.go";
		frm.submit();
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
				 ■ 홈 > 포인트관리 > 결제 내역 조회
			</header>
		
			<div class="contents-block">
			
				<h1>결제 내역 조회</h1>
				
				<div class="contents-main">
					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<input type="hidden" name="colname" value="order_date">
						<input type="hidden" name="sort" value="desc">
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool">		
									<input type="text" name="keyword" value="${keyword}" placeholder="아이디 검색" class="itext">
									<select name="type">
										<option value="">=결제종류=</option>
										<option value="1">카드</option>
										<option value="2">소액결제</option>
									</select>
									<input type="text" name="startDate" class="itext datepicker"  placeholder="시작일" style="width:80px;">
									<input type="text" name="endDate" class="itext datepicker"  placeholder="종료일" style="width:80px;"> 
									
									<button type="submit" class="btn">검색</button>
								</div>
								
								<div class="btn-tools"><button class="btn-green" onclick="excelDownload(this.form);">엑셀 다운로드</button></div>
							</div>
						</div>
						<table class="list">
							<colgroup>
									<col width="7%">
									<col width="5%">			
									<col width="5%">
									<col width="10%">
									<col width="10%">
									<col width="8%">
									<col width="8%">
									<col width="8%">
									<col width="8%">
									
							</colgroup>
							
								<thead>
									
									<th>번호
										<span id="order_seq">
											 <button type="button" onclick="listOrder('order_seq','asc');" class="order_seq" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>운영사이트
										<span id="site_name">
											 <button type="button" onclick="listOrder('site_name','asc');" class="site_name" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>유형
										<span id="payment_type">
											 <button type="button" onclick="listOrder('payment_type','asc');" class="payment_type" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>고유코드
										<span id="order_title">
											 <button type="button" onclick="listOrder('order_title','asc');" class="order_title" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>아이디
										<span id="user_id">
											 <button type="button" onclick="listOrder('user_id','asc');" class="user_id" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>휴대폰번호 
										<span id="mobile">
										<button type="button" onclick="listOrder('mobile','asc');" class="mobile" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>충전포인트(P) 
										<span id="price_sum">
										<button type="button" onclick="listOrder('price_sum','asc');" class="price_sum" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>결제금액(원) 
										<span id="total_fee">
										<button type="button" onclick="listOrder('total_fee','asc');" class="total_fee" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>결제일 
										<span id="order_date">
										<button type="button" onclick="listOrder('order_date','asc');" class="order_date" style="margin-top:3px;">▼</button>
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