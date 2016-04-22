<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script>


<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(8,1);
	searchList(listForm,1);
});


function searchList(frm,page) {

	var param = {
		page		:	page,
		keyword : frm.keyword.value,
		colName : frm.colname.value,
		sort : frm.sort.value,
		type : 'point'
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/point/require_list.go",
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
//선택 환전 
function changeGo(){
	
	var arr = new Array();
	var arrSeq = "" ; 
	
	$.each($(".chkok"), function( index, obj ) {
		if (($('.chkok').eq(index).is(":checked"))) {
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

		type : "POST",
				url : "/admin/point/changedo.go",
				dataType : "json",
				data : param,
				success : function(json) {
					alert(json.message);
					if (json.result) {
						document.location.reload();
					}
	
				}
	
			});

		return false;

	}

	//액셀 다운로드
	function excelDownload() {
		var frm = listForm;
		document.location.href = "/admin/point/prequire_list_excel.go";
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

	function order(item, itemValue) {

		var param = {
			item : item,
			itemValue : itemValue,

		};

		$.ajax({
			type : "POST",
			url : "/admin/user/siteValue.go",
			dataType : "html",
			data : param,
			success : function(msg) {

			}
		});
		return false;

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
				 ■ 홈 > 포인트관리 > 포인트환전신청
			</header>
		
			<div class="contents-block">
			
				<h1>포인트환전신청</h1>
				
				<div class="contents-main">
					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<input type="hidden" name="colname" value="request_date">
						<input type="hidden" name="sort" value="desc">
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool">		
									<input type="text" name="keyword" value="${keyword}" placeholder="아이디 검색" class="itext">
									<button type="submit" class="btn">검색</button>
								</div>
								<div class="btn-tools"><button class="btn-blue" onclick="changeGo();">선택환전</button></div>
								<div class="btn-tools"><button class="btn-green" onclick="excelDownload();">엑셀 다운로드</button></div>
							</div>
						</div>
						<table class="list">
							<colgroup>
								<col width="5%">
								<col width="5%">
								<col width="5%">
								<col width="20%">
								<col width="20%">
								<col width="5%">
								
							</colgroup>
								<thead>
									<th><input type="checkbox" class="chkAll" onclick="chkAll(this);"></th>
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
									<th>포인트(P) 
										<span id="point">
										<button type="button" onclick="listOrder('point','asc');" class="point" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>환전금액 
										<span id="pay_point">
										<button type="button" onclick="listOrder('pay_point','asc');" class="pay_point" style="margin-top:3px;">▼</button>
										</span>
									</th>
									
									<th>요청날짜 
										<span id="request_date">
										<button type="button" onclick="listOrder('request_date','asc');" class="request_date" style="margin-top:3px;">▼</button>
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