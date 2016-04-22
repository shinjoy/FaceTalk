<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link rel="stylesheet" type="text/css" href="/lib/smarteditor/smart_editor2.css" />
<script type="text/javascript" src="/lib/smarteditor/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
	$(document).ready(function() {
 		aside.setActive(4,2);
	});

	function submitForm(frm) {
		
		var start = parseInt(frm.startDate.value.replace(/-/g, ""));
		var end = parseInt(frm.endDate.value.replace(/-/g, ""));
		
		if (frm.imgFile != undefined) {
			if (frm.imgFile.value == "") {
				alert("이미지 파일을 선택해 주세요.");
				return false;
			}
		}
		if (frm.startDate.value == "") {
			alert("게시기간을 입력해 주세요.");
			return false;
		}
		if (frm.site.value == "") {
			alert("사이트를 선택해 주세요.");
			return false;
		}
		if (frm.endDate.value == "") {
			alert("게시기간을 입력해 주세요.");
			return false;
		}
		
		if(start>end){
			alert("게시기간 날짜를 확인해주세요.")
			return false;
		}
		
	 	var param = {
 			seq : frm.seq.value,
			//linkUrl	: frm.linkUrl.value,
			//backgroundColor : frm.backgroundColor.value,
			visible : frm.visible.value,
			startDate : frm.startDate.value,
			endDate : frm.endDate.value,
			site : frm.site.value
			//gugun : frm.areaGugun.value
	 	};
		
		frm.action = "/admin/guide/guide_edit_do.go";
		frm.submit();
		return false;
	}
	
	function deleteBanner(seq,contentsHtml) {
		if(confirm("이용안내 광고를 삭제하시겠습니까?")) {
			var param = {
				seq	:	seq,
				imgFile : contentsHtml
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/guide/guide_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/guide/guide.go";
					}
				}
			});
		}
		return false;

	}
	
	function deleteImg(seq,contentsHtml) {
		if(confirm("이미지를 삭제하시겠습니까?")) {
			var param = {
					seq	:	seq,
					imgFile : contentsHtml
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/guide/guide_file_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.reload();
					}
				}
			});
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
	
	function searchArea(frm) {
		
		if(frm.areaSido.value == "0"){
			$('areaSido').find('option:first').attr('selected', 'selected');
		}
		
		var param = {
				areaSido		: 	frm.areaSido.value
		};
		
				
		$.ajax({
			type:"POST",
			url:"/admin/area/search.go",
			dataType:"json",
			data:param,
			success:function(json){
				var list = json.list;
				var str = '<option value="">== 검색결과 ==</option>';
				
				for (var i=0; i<list.length; i++) {
					str += '<option value="'+list[i].gugun+'>'+list[i].gugun+'</option>';
				}
				$("#areaGugun").html(str);
			}
		});
	 
		return false;
	}
	
	function select(frm, obj) {
		frm.areaSeq.value = obj.value;
	}
	
	
</script>

</head>
<style>
table.register th {
   vertical-align: top;
   text-align: right;
   padding:10px;
   font-weight: bold;
}

table.register td {
	padding:10px;
    vertical-align: top;
}	
img.preview { max-width:500px; }
</style>

<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 게시판관리 > 이용안내 등록
			</header>
		
			<div class="contents-block">
			
				<h1>이용안내 등록</h1>
				
				<form method="post" name="printcenterForm"  enctype="multipart/form-data"  onsubmit="return submitForm(this); return false;">
				<input type="hidden" name="seq" value="${notice.noticeSeq}">
				<div class="contents-main">

					<div class="contents-box">
				
						<table class="register">
							<tr>
								<th><div class="icon">이미지</div></th>
								<td>
									<c:choose>
										<c:when test="${notice.contentsHtml == '' || notice.contentsHtml == null}">
											
											<input type="file" name="imgFile">(폭 700px의 이미지를 업로드해주세요.)
										</c:when>
										<c:otherwise>
											<img src="${notice.contentsHtml}" class="preview" >
											<button type="button" class="btn tiny" onclick="deleteImg('${notice.noticeSeq}','${notice.contentsHtml}');">삭제</button>	
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<th>게시</th>
								<td>
									<label><input type="radio" name="visible" value="1" ${notice.visible == 1 ? 'checked=\"checked\"' : ''}>게시</label>
									<label><input type="radio" name="visible" value="0" ${notice.visible == 0 ? 'checked=\"checked\"' : ''}>숨김</label>
								</td>
							</tr>
							<c:choose>
								<c:when test="${USER_TYPE==1}">
								<tr>
									<th>사이트</th>
									<td class="radio">
									<c:forEach items="${list}" var="it">
										<label><input type="radio" name="site" value="${it.serviceId}" ${notice.site == it.serviceId ? 'checked=\"checked\"' : ''}>${it.siteName}</label>
									</c:forEach>
									</td>
								</tr>	
								</c:when>
								<c:otherwise>
									<input type="hidden" name="site" value="${user.site}">
								</c:otherwise>
							</c:choose>		
							
							
							<tr>
								<th>게시기간</th>
								<td>
									<input type="text" name="startDate" class="itext datepicker" style="width:150px;" value="${notice.startDate.length() >= 10 ? notice.startDate.substring(0,10) : notice.startDate}"> ~
									<input type="text" name="endDate" class="itext datepicker" style="width:150px;" value="${notice.endDate.length() >= 10 ? notice.endDate.substring(0,10) : notice.endDate}">
								</td>
							</tr>

						</table>	
					</div>

				</div>
				
				<div class="btn-tools">
					<button type="submit" class="btn-blue">이용안내 저장</button>
					<c:if test="${notice.noticeSeq > 0 }">
						<button type="button" class="btn-red" onclick="deleteBanner(${notice.noticeSeq});">삭제</button>					
					</c:if>
					<button type="button" class="btn" onclick="document.location.href='/admin/guide/guide.go';">목록</button>
				</div>
				
				</form>
					
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>