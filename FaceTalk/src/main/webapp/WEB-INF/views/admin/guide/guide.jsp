<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">



	$(document).ready(function() {
 		aside.setActive(4,7);
		searchList(listForm,1);
	});

	function searchList(listForm,page) {
		var param = {
			page		:	page
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/guide/guide_list.go",
			dataType:"html",
			data:param,
			success:function(msg){
				$("#contents-list").html(msg);
			}
		});
		return false;
	}
	
	//순서정렬
	function viewArray(){
		
		var sortCount = $(".sort").length;
		var arr = new Array();
		var arrSeq = "" ; 
		
		$.each($(".sort"), function( index, obj ) {
			arr.push($(obj).val());
		});
		
		arrSeq = arr.join(",");


		var param = {
				arrSeq  : arrSeq
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/guide/sort.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				document.location.reload();
				
			}
		});

		return false;
		


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
				 ■ 홈 > 게시물 관리 > 이용안내
			</header>
		
			<div class="contents-block">
			
				<h1>이용안내</h1>
				
				<div class="contents-main">
				
					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool">
									<input type="text" name="keyword" value="${keyword}" placeholder="제목/내용 검색" class="itext">
									<button type="submit" class="btn" >검색</button>
								</div>
								<div class="btn-tools"><button class="btn-blue" onclick="document.location.href='/admin/guide/guide_edit.go?seq=0';">이용안내 등록</button></div>
								<button type="button" class="btn" onclick="viewArray();">정렬 저장</button>
<!-- 								<div class="btn-tools"><button class="btn" onclick="windowOpen('/m/popup/popup.go','FaceTalk',320,0,'yes','no');">모바일 팝업</button></div> -->
							</div>
						</div>
						
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