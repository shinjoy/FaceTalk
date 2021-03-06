<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/pc/head.jsp"  %>

<script type="text/javascript">
	
	$(document).ready(function() {

		if (localStorage.getItem("chatId") != undefined) {
			searchForm.chatId.value = localStorage.getItem("chatId");
		}

		/* 폼 ajax전송 : http://malsup.com/jquery/form/#ajaxForm */
		var options = {
			//target :		'#user-join-result',
			beforeSubmit :	formCheck,
			success :		formSuccess
		};
		$('#bbsEditForm').ajaxForm(options);
		//getCategory();
	});

	function formCheck(formData, jqForm, options) {
		
		return true;
	}
	function formSuccess(responseText, statusText, xhr, $form) {
		//alert('status: ' + statusText + '\n\nresponseText: \n' + responseText );
		/* var idx = 0;
		if (bbsEditForm.photo != undefined) {
			idx = bbsEditForm.photo.length + 1;
		}; */
		var idx = 1;
		var json = JSON.parse(responseText);
		try {
			$(".file-add").empty();
			if(json.photo!=""){
				var str = '';
				str += '<li class="thumb" id="new-img1">';
				str += '	<div class="photo-talk" style="background-image:url(\''+ json.path +'/'+ json.photo +'\');">';
				str += '		<button type="button" class="cancel-btn" onclick="delfileNow(\''+ json.path +'/'+ json.photo +'\','+idx+',this.form)">X</button>';
				str += '	</div>';
				str += '	<input type="hidden" name="photo" value="'+ json.path +'/'+ json.photo +'">';
				str += '</li>';
				$(".file-add").append(str);
			}
			bbsEditForm.photo.value = json.path +'/'+ json.photo;
		} catch (e) {
			pop.openAlert('',json.message); 
		}
	}

	function delfile(num,fileName){
		if(confirm("삭제하시겠습니까?")){
			$("#li"+num).remove();
			
			var str = '<input type="hidden" name="delphoto" value="'+fileName+'">';
			$(".delfile").append(str);
			
		}
		return false;
	}
	
	
	function delfileNow(filename,num,frm){
		if(confirm("삭제하시겠습니까?")){
			
			var param = {
				file : filename
			
			}
			$.ajax({
				type:"POST",
				url:"/m/file_delete.go",
				dataType:"json",
				data:param,
				success:function(json){
					pop.openAlert('',json.message);
					$("#new-img1").empty();
					/* $("#photoCount").val(json.fileCount); */
				}
			}); 
		}
		return false;
	}
	
	function submitForm(frm) {
			
		var param = {
			bbsSeq	: frm.bbsSeq.value,
			bbsCommentSeq : frm.bbsCommentSeq.value,
			userId :  searchForm.chatId.value,
			bbsContents : frm.bbsContents.value,
			rCommentSeq : frm.rCommentSeq.value,
			rLevel :  frm.rLevel.value,
			files : getListValue("photo",",")
		
		};
		
		$.ajax({
			type:"POST",
			url:"/m/bbs_comment_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
				
					document.location.href = "/pc/talk_comment.go?userId="+searchForm.chatId.value+"&bbsCommentSeq="+json.bbsCommentSeq+"&bbsSeq="+frm.bbsSeq.value;
				}
			}
		});

		return false;
	}
	
	function openSelectorPop(frm) {
		pop.openWindow("/pc/chat_user.go?agentId="+frm.userId.value, 'chatUser', 1040, 400, 'yes', 'yes');
	}

</script>

<style>

	header.notice { position:fixed; top:0; display:table; width:100%; background-color:#acf; height:40px; }
	header.notice .left-btn { display:table-cell; width:20%; text-align:left; vertical-align:middle; }
	header.notice .right-btn { display:table-cell; width:80%; text-align:right; vertical-align:middle; }
	header.notice input { background-color:rgba(255,255,255,0); width:110px; border:0; color:#fff; text-align:right; }
	header.notice button { margin:0 10px; }
	.top-blank { height:40px; }
	.tbl-list TD{
		border-bottom: 1px solid #ddd;
		padding: 5px;
	}
	.add-contents { height:300px; padding:10px; background-color:#fff; }
	textarea { width:100%; height:100%; border:0; }
	.bbs-category { width:100%; padding:5px; border:1px solid #aaa; }
	.bbs-category:hover { padding:5px; border:1px solid #aaa; }
	.photo-talk { float:left; width:100px; height:100px;  background-size:cover;
	 background-repeat:no-repeat; background-position:center; border:1px solid #ccc; 
	 background-color:#fff; }
	
</style>
</head>
<body style="background-color:#eee;">

	<form method="post" name="searchForm" id="listForm" onsubmit="return searchList(this,1); return false;">
	<input type="hidden" name="userId" value="${USER_ID}">
	<input type="hidden" name="page" value="1">
	<input type="hidden" name="keyword" value="">
	
	<header class="notice">
		<div class="left-btn"><button type="button" onclick="history.back(-1);"><img src="/images/bullet_arrow_left.png" style="width:30px; padding:2px 10px;"></button></div>
		<div class="right-btn">
			<input type="text" name="chatId" value="${IMG_ID}" readonly="readonly">
			<input type="hidden" name="chatName" value="">
			<!-- <button type="button" class="btn-blue" onclick="openSelectorPop(this.form);">변경하기</button> -->
		</div>
	</header>
	</form>

	<div class="top-blank"></div>
	
	<form method="post" name="bbsEditForm" id="bbsEditForm" enctype="multipart/form-data" action="/m/photo_upload.go" style="display:block;">
	<input type="hidden" name="userId" value="${IMG_ID}">
	<input type="hidden" name="bbsSeq" value="${bbsSeq}">
	<input type="hidden" name="bbsCommentSeq" value="${bbs.bbsCommentSeq}">
	<input type="hidden" name="rCommentSeq" value="${bbs.rCommentSeq}">
	<input type="hidden" name="rLevel" value="${bbs.rLevel}">
	<input type="hidden" name="path" value="bbs">
	
		
		
		<div class="add-contents">
			<textarea name="bbsContents">${bbs.bbsContents }</textarea>
			
		</div>
		<div class="file-box-ed">
			<ul class="file-add">
					<c:if test="${bbs.files!=''}">
						<li class="thumb images" id="li1">
							<span class ="cphoto p1"> 
								<input type="hidden" name="photo" value="${bbs.files}">
									<div class="photo-talk" style="background-image:url('${bbs.files}'); ">
										<button type="button" class="cancel-btn" onclick="delfile(1,'${bbs.files}')">X</button>
									</div>
							</span>	
						</li> 
					</c:if>
						
							
					
			</ul>
		</div> 
		
		
		
		<div class ="delfile"></div>
		
		<div class="add-comment">
			<div class="add-img">
				<img src="/images/icon_picture_dw.png" class="file-up" style="cursor:pointer;">
				<div class="file-btn">
					<input type="file" name="filename" class="file" onchange="document.getElementById('upload-btn').click();" style="cursor:pointer;" title="이미지">
					<button type="submit" id="upload-btn" class="hidden-submit-btn">a</button>
				</div>
			</div>
			<div class="add-text"></div>
			
				<div class="add-btn">
					<button type="button" class="btn-blue" style="width:100px;" onclick="submitForm(this.form);">작성</button> 
				</div>
			
		</div>
	</form>


</body>
</html>