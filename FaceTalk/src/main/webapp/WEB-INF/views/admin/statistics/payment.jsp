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

	function searchList(listForm,page) {
		var param = {
			keyword		: 	listForm.keyword.value,
			startDate		: 	listForm.startDate.value,
			endDate		: 	listForm.endDate.value,
			colName : listForm.colname.value,
			sort : listForm.sort.value,
			page		:	page
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/statistics/payment_list.go",
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


	
</script>

<style>
.device { font-weight: bold; font-size:25px; color:#464242; }	
.guard { border-bottom: 3px solid #535b60; padding-top:15px; width: 1000px;}	
.search-sec .down { float: left; padding-left:680px; text-align: right; }
</style>


</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 포인트관리 관리 > 결제 내역 조회
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
									<input type="text" name="startDate" class="itext datepicker" value="${startDate}" placeholder="시작일" style="width:80px;" onchange="startChange(listForm);">
									<input type="text" name="endDate" class="itext datepicker" value="${endDate}" placeholder="종료일" style="width:80px;">
									<button type="submit" class="btn" >검색</button>
								</div>

							</div>
						</div>
						
						<table class="list">
							<colgroup>
								<col width="10%">
								<col width="*">
								<col width="10%">
								<col width="10%">
								<col width="10%">
								<col width="20%">				
							</colgroup>
							<thead>
								<tr>												
									<th>번호 
										<span id="order_seq">
										<button type="button" onclick="listOrder('order_seq','asc');" class="order_seq" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>아이디
										<span id="user_id">
											 <button type="button" onclick="listOrder('user_id','asc');" class="user_id" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>충전포인트
										<span id="price_sum">
											 <button type="button" onclick="listOrder('price_sum','asc');" class="price_sum" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>결제금액
										<span id="total_fee">
											 <button type="button" onclick="listOrder('total_fee','asc');" class="total_fee" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>결제유형
										<span id="payment_type">
											 <button type="button" onclick="listOrder('payment_type','asc');" class="payment_type" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>결제날짜 
										<span id="order_date">
										<button type="button" onclick="listOrder('order_date','asc');" class="order_date" style="margin-top:3px;">▼</button>
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