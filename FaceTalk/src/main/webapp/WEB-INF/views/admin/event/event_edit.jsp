<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link rel="stylesheet" type="text/css" href="/lib/smarteditor/smart_editor2.css" />
<script type="text/javascript" src="/lib/smarteditor/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
$(document).ready(function() {
		aside.setActive(4,4);
});

$(function() {
	$.datepicker.setDefaults( $.datepicker.regional[ "ko" ] );
	$( ".datepicker" ).datepicker(
		{
			
		dateFormat : "yy-mm-dd",	
		showButtonPanel: true
		}
	);
});	

var oEditors = [];


function submitForm(frm) {
	
	var start = parseInt(frm.startDate.value.replace(/-/g, ""));
	var end = parseInt(frm.endDate.value.replace(/-/g, ""));
	
	
	if (frm.title.value == "") {
		alert("제목을 입력해주세요.");
		return false;
	}
	if (frm.site.value == "") {
		alert("사이트를 선택해 주세요.");
		return false;
	}
	
	oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
	getPlainText(frm);
	
	var param = {
		seq	: frm.seq.value,
		userId	: frm.userId.value,
		notiType : frm.notiType.value,
		title	: frm.title.value,
		site	: frm.site.value,
		sendPush	:  $('input:radio[name="sendPush"]:checked').val(),
		startDate : frm.startDate.value,
		endDate : frm.endDate.value,
		ir1	: frm.ir1.value,
		ir1_text	: frm.ir1_text.value
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/event/event_edit_do.go",
		dataType:"json",
		data:param,
		success:function(json){
			alert(json.message);
			if (json.result) {
				document.location.href = "/admin/event/event.go";
			}
		}
	});

	
	//frm.action = "/admin/notice/notice_edit_do.go";
	//frm.submit();
	return false;
}
// html 태그제거
function fRemoveHtmlTag(string) { 
	string = string.replace("<br>","\r\n");
	var objReplace = new RegExp(); 
	objReplace = /[<][^>]*[>]/gi; 
	return string.replace(objReplace, ""); 
}
function getPlainText(frm) { 
	var html = oEditors.getById["ir1"].getIR(); 
	frm.ir1_text.value=fRemoveHtmlTag(html);
}

function deleteNotice(seq) {
	if(confirm("게시물을 삭제하시겠습니까?")) {
		var param = {
			seq	:	seq
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/event/event_delete_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/admin/event/event.go";
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
		userId : frm.userId1.value,
		bbsCommentSeq : frm.bbsCommentSeq.value,
		seq	:	frm.seq1.value,
		comment : frm.comment.value
	};
	 
	$.ajax({
		type:"POST",
		url:"/admin/event/event_comment_edit.go",
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
		bbsCommentSeq : frm.bbsCommentSeq1.value,
		seq	:	frm.seq.value,
		comment : frm.comment1.value
	};
	 
	$.ajax({
		type:"POST",
		url:"/admin/event/event_comment_edit.go",
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


function deleteComment(bbsCommentSeq1) {
	if(confirm("댓글을 삭제하시겠습니까?")) {
		var param = {
				bbsCommentSeq	:	bbsCommentSeq1
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/event/comment_delete_do.go",
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
				 ■ 홈 > 게시판관리 >이벤트 관리
		
			<div class="contents-block">
			
				<h1>이벤트 관리</h1>
				
				<div class="contents-edit" style="width:1000px;">

					<form method="post" name="noticeForm" id="noticeEdit" enctype="multipart/form-data" onSubmit="return submitForm(this); return false;">
					<input type="hidden" name="seq" value="${event.noticeSeq}"/>
					<input type="hidden" name="userId" value="${USER_ID}"/>
					<input type="hidden" name="userName" value="${USER_NAME}"/>
					<input type="hidden" name="ir1_text" value="${event.contentsText}"/>
					<input type="hidden" name="notiType" value="0"/>
						<table class="edit">
						<colgroup>
							<col width="100">
							<col width="*">
						</colgroup>
						<tr>
							<th>제목</th>
							<td><input type="text" name="title" class="itext" style="width:590px;" value="${event.title}"></td>
						</tr>
						<tr>
							<th>게시기간</th>
							<td>
								<input type="text" name="startDate" class="itext datepicker" style="width:150px;" value="${event.startDate.length() >= 10 ? event.startDate.substring(0,10) : event.startDate}"> ~
								<input type="text" name="endDate" class="itext datepicker" style="width:150px;" value="${event.endDate.length() >= 10 ? event.endDate.substring(0,10) : event.endDate}">
							</td>
						</tr>
						<c:choose>
							<c:when test="${USER_TYPE==1}">
							<tr>
								<th>사이트</th>
								<td class="radio">
								<c:forEach items="${list}" var="it">
									<label><input type="radio" name="site" value="${it.serviceId}" ${event.site == it.serviceId ? 'checked=\"checked\"' : ''}>${it.siteName}</label>
								</c:forEach>
								</td>
							</tr>	
							</c:when>
							<c:otherwise>
								<input type="hidden" name="site" value="${user.site}">
							</c:otherwise>
						</c:choose>						
						<tr>
							<th>푸시알림</th>
							<td class="radio">
							<c:choose>
									<c:when test="${event.noticeSeq >0 }">
										<label><input type="radio" name="sendPush" value="0" ${event.sendPush == 0 ? 'checked=\"checked\"' : ''}>안함</label>
										<label><input type="radio" name="sendPush" value="1" ${event.sendPush == 1 ? 'checked=\"checked\"' : ''}>전체 푸시알림 전송</label>
									</c:when>
									<c:otherwise>
										<label><input type="radio" name="sendPush" value="0" >안함</label>
										<label><input type="radio" name="sendPush" value="1" checked="checked">전체 푸시알림 전송</label>
									</c:otherwise>
							</c:choose>		
							</td>
						</tr>
						<tr>
							<th style="vertical-align: top;">내용</th>
							<td><textarea name="ir1" id="ir1" style="display:none; width:700px; height:200px;">${event.contentsHtml}</textarea></td>
						</tr>
						</table>	
						<div style="margin:20px; padding-left:300px;">
							<button type="submit" class="btn-blue" style="width:200px;">저장</button>
							<c:if test="${event.noticeSeq > 0}">
								<button type="button" class="btn-red" onclick="deleteNotice(${event.noticeSeq});">삭제</button>
							</c:if>
							<button type="button" class="btn" onclick="document.location.href='/admin/event/event.go';" >목록으로</button>
						</div>
					</form>
					
					<div class="tab-list" style="width:700px; margin-left:100px;">	
									
						<table class="request">	
							<c:choose>
								<c:when test="${commentList.size() > 0}">
									<colgroup>
								 		<col width="10%">
										<col width="40%">
										<col width="5%">
										<col width="15%" span="2">
									
									</colgroup>
									<thead>
										<tr>
											<th>답변</th>
											<th>내용</th>
											<th>작성자</th>
											<th>작성일자</th>
											
										</tr>
									</thead>
									<tbody>
										<c:forEach var="it" items="${commentList}">
										<tr>
											<form method="post" name="commentListForm" id="commentListEdit">
												<input type="hidden" name="bbsCommentSeq1" value="${it.bbsCommentSeq}">
												<input type="hidden" name="seq" value="${event.noticeSeq}"/>
												<td style="text-align: center;">${it.bbsCommentSeq}</td>
												
												<td> 
													${it.bbsContents}
												</td>
												<td> 
													${it.user_name}
												</td>
												<td style="text-align:right;">
													${it.rRegDate.substring(0,16)}
													
												</td>
												<td>
													<button type="button" class="btn-red" onclick="deleteComment(${it.bbsCommentSeq});" style="margin-left:10px;">삭제</button>
												</td>
											</form>
										</tr>
									</tbody>	
										</c:forEach>
								</c:when>
							</c:choose>
	
							<c:if test="${event.noticeSeq > 0}">
								<tr>
									<form method="post" name="commentForm" id="commentEdit">
										<td>&nbsp;</td>
										<td colspan="2"> 
											<input type="hidden" name="userId1" value="${USER_ID}"/>
											<input type="hidden" name="bbsCommentSeq" value="0">
											<input type="hidden" name="seq1" value="${event.noticeSeq}"/>
											<textarea rows="5" cols="90" name="comment"></textarea>
											<button type="button" class="btn-blue" style="width:50px;" onclick="onComment(this.form)">등록</button>
										</td>
									</form>						
								</tr>
							</c:if>	
							</tbody>
						</table>
					</div>

					</div>
					
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
								