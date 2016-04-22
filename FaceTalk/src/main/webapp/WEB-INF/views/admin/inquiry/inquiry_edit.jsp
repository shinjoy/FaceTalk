<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link rel="stylesheet" type="text/css" href="/lib/smarteditor/smart_editor2.css" />
<script type="text/javascript" src="/lib/smarteditor/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
	$(document).ready(function() {
 		aside.setActive(4,5);
	});

	$(function() {
		$.datepicker.setDefaults( $.datepicker.regional[ "ko" ] );
		$( ".datepicker" ).datepicker(
			{
			showButtonPanel: true
			}
		);
	});	

	
	function submitForm(frm) {
		
		
		if (frm.title.value == "") {
			alert("제목을 입력해주세요.");
			return false;
		}
		if (frm.comment.value == "") {
			alert("내용을 입력해주세요.");
			return false;
		}
		
		var param = {
			seq	: frm.seq.value,
			userId	: frm.userWrite.value,
			notiType : frm.notiType.value,
			answer : $('input:radio[name="answer"]:checked').val(),
			title	: frm.title.value,
			site	: frm.site.value,
			ir1	: frm.ir1.value,
			comment : frm.comment.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/inquiry/inquiry_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/admin/inquiry/inquiry.go";
				}
			}
		});

		
		return false;
	}

	function deleteNotice(seq) {
		if(confirm("게시물을 삭제하시겠습니까?")) {
			var param = {
				seq	:	seq
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/inquiry/inquiry_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/inquiry/inquiry.go";
					}
				}
			});
		}
		return false;

	}
	
	function onComment(frm) {

		if(frm.comment.value==""){
			 alert("내용을 입력해주세요");
			 return false;
		 }
			
		var param = {
			seq	:	frm.seq2.value,
			userId  : frm.userId2.value,
			comment : frm.comment.value,
			userWrite	: frm.userWrite.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/inquiry/inquiry_comment_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.reload();
				}
			}
		});
		
		 return false;
		
	}
	
	function onCommentEdit(frm) {

		
		var param = {
			seq	:	frm.seq1.value,
			userId  : frm.userId1.value,
			comment : frm.comment1.value
		};
		 
		$.ajax({
			type:"POST",
			url:"/admin/inquiry/inquiry_modify_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.reload();
				}
			}
		});
		
		 return false;
		
	}
	
	
	function deleteComment(seq1) {
		if(confirm("댓글을 삭제하시겠습니까?")) {
			var param = {
					seq	:	seq1
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/inquiry/comment_delete_do.go",
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
	
	
</script>

</head>
<style>
.tab-list TD{padding: 5px;}
table.request th {
	border-bottom: 1px solid #ddd;
   vertical-align: top;
   text-align: center;
   padding:5px;
   font-weight: bold;
}
table.request td {
	border-bottom: 1px solid #ddd;
   vertical-align: top;
   padding:5px;
}
</style>

<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 게시판관리 >1:1문의 관리
		
			<div class="contents-block">
			
				<h1>1:1문의 관리</h1>
				
				<div class="contents-edit" style="width:1000px;">

					<form method="post" name="noticeForm" id="noticeEdit"  onSubmit="return submitForm(this); return false;">
					<input type="hidden" name="seq" value="${qna.noticeSeq}"/>
					<input type="hidden" name="userWrite" value="${qna.userId}"/>
					<input type="hidden" name="userName" value="${USER_NAME}"/>
					<input type="hidden" name="ir1_text" value="${qna.contentsText}"/>
					<input type="hidden" name="notiType" value="0"/>
					<input type="hidden" name="site" value="${qna.site}"/>
						<table class="edit">
						<colgroup>
							<col width="100">
							<col width="*">
						</colgroup>
						<tr>
							<th>등록자</th>
							<td>
								${qna.userName}(${qna.userId})
							</td>
						</tr>
						<tr>
							<th>제목</th>
							<td>
								<input type="hidden" name="title" value="${qna.title}"/>
								${qna.title}
							</td>
						</tr>
						<tr>
							<th>답변</th>
							<td class="radio">
								<label><input type="radio" name="answer" value="0" ${qna.answer == 0 ? 'checked=\"checked\"' : ''}>접수</label>
								<label><input type="radio" name="answer" value="1" ${qna.answer == 1 ? 'checked=\"checked\"' : ''}>답변완료</label>
							</td>
						</tr>
						<tr>
							<th style="vertical-align: top;">내용</th>
							<td>
							<input type="hidden" name="ir1" value="${qna.contentsHtml}">
								<% pageContext.setAttribute("LF", "\n"); %>
								${fn:replace(qna.contentsHtml, LF,'<br>')} 
						
							</td>
						</tr>

						</table>	
						<div style="margin:20px; padding-left:300px;">
							
							<c:if test="${qna.noticeSeq > 0}">
								<button type="button" class="btn-red" onclick="deleteNotice(${qna.noticeSeq});">삭제</button>
							</c:if>
							<button type="button" class="btn" onclick="document.location.href='/admin/inquiry/inquiry.go';" >목록으로</button>
						</div>
					
						
					<div class="tab-list" style="width:700px; margin-left:100px;">	
						<table class="request" style="width:100%;">	
							<colgroup>
								<col width="15%">
								
								<col width="*">
								<col width="10%">
							</colgroup>
							<c:choose>
								<c:when test="${commentList.size() > 0}">
							<thead>
								<!-- <th>답변</th> -->
								<th>작성자</th>
								<th>내용</th>
								<th>작성일자</th>
							</thead>
							<tbody>
								
								<c:forEach var="it" items="${commentList}">
								<tr>
							
									<td>${it.userName}</td>
									<td><% pageContext.setAttribute("LF", "\n"); %>
										${fn:replace(it.contentsHtml, LF,'<br>')}</td>
								
									
										<input type="hidden" name="userId1" value="${USER_ID}"/>
										<input type="hidden" name="rLevel" value="${it.rLevel}">
										<input type="hidden" name="seq1" value="${it.noticeSeq}"/>
										<td style="text-align:right;">
											${it.rRegDate.substring(0,16)}
											<button type="button" class="btn-red" onclick="deleteComment(${it.noticeSeq});" style="margin-left:10px;">삭제</button>
										</td>
									
								</tr>
								</c:forEach>
								</c:when>
							</c:choose>
	
							<c:if test="${qna.noticeSeq > 0}">
								<tr>
									<td>&nbsp;</td>
									<td colspan="2"> 
										<textarea rows="5" cols="90" name="comment"></textarea>  
										<button type="submit" class="btn-blue" style="width:200px;">저장</button>
								
									</td>
								</tr>
							</c:if>
							</tbody>
						</table>
					</div>
					</div>
					</form>
				</div>
			</div>
		</section>
	</section>
</section>

<script type="text/javascript">

// 추가 글꼴 목록
//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];



nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "ir1",
	sSkinURI: "/SmartEditor2Skin.html",	
	htParams : {
		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
		fOnBeforeUnload : function(){
			//alert("완료!");
		}
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
});


function pasteHTML() {
	var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
	oEditors.getById["ir1"].exec("PASTE_HTML", [sHTML]);
}

function showHTML() {
	var sHTML = oEditors.getById["ir1"].getIR();
	alert(sHTML);
}

function submitContents(elClickedObj) {
	oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
	
	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
	try {
		elClickedObj.form.submit();
	} catch(e) {}
}

function setDefaultFont() {
	var sDefaultFont = '궁서';
	var nFontSize = 24;
	oEditors.getById["ir1"].setDefaultFont(sDefaultFont, nFontSize);
}


</script>


<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>
								