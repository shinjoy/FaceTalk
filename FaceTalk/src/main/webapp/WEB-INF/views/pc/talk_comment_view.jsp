<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		
	});

	function searchList(searchForm,page) {

		var userId = searchForm.chatId.value;
		var bbsSeq = searchForm.bbsSeq.value;
		var bbsCommentSeq = searchForm.bbsCommentSeq.value;
	
		
		document.location.href = "/pc/talk_comment.go?userId="+userId+"&bbsSeq="+bbsSeq+"&page="+page+"&bbsCommentSeq="+bbsCommentSeq;

		return false;
	}
	function formCheck(formData, jqForm, options) {
		return true; 
		/*
		if (bbsEditForm.photo == undefined) {
		} else {
			alert("댓글은 한개의 이미지만 등록할 수 있습니다.");
			return false;
		}
		*/
	}
	function formSuccess(responseText, statusText, xhr, $form) {
		//alert('status: ' + statusText + '\n\nresponseText: \n' + responseText );
		var idx = 0;
		if (bbsEditForm.photo != undefined) {
			idx = 1;
		};
		var json = JSON.parse(responseText);
		try {
			if(json.photo!=""){
				var str = '';
				str += '<li class="thumb" id="new-img'+idx+'">';
				str += '	<div class="photo-preview" style="background-image:url(\''+ json.path + json.photo +'\');">';
				str += '		<button type="button" class="cancel-btn" onclick="delfileNow(\''+ json.path +'/'+ json.photo +'\','+idx+',this.form)">X</button>';
				str += '	</div>';
				//str += '	<input type="text" name="photo" value="'+ json.path + json.photo +'">';
				//str += '	<input type="hidden" name="thumb" value="'+ json.path +"thumb/"+ json.photo +'">';
				str += '</li>';
				$(".file-add").html(str);
				bbsEditForm.photo.value = json.path +'/'+ json.photo;
			}
		
		} catch (e) {
			pop.openAlert('',json.message); 
		}
	}

	function delfile(num){
		if(confirm("삭제하시겠습니까?")){
			$("#li"+num).remove();
			
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
					$("#new-img"+num).remove();
					bbsEditForm.photo.value = '';
				}
			}); 
		}
		
		return false;
	}
	
	function checkEnter(e, frm, obj) {
		if(e.keyCode == 13){
			submitForm(frm);
			//return false;
		}else{
			e.keyCode == 0;
			return;
		}
	}
	
	function submitForm(frm) {
			
		var param = {
			bbsCommentSeq : frm.bbsCommentSeq.value,
			rCommentSeq : frm.rCommentSeq.value,
			bbsSeq	: frm.bbsSeq.value,
			userId :frm.userId.value,
			bbsContents : frm.bbsContents.value,
			files : frm.photo.value,
			rLevel : 1
		};
		
		$.ajax({
			type:"POST",
			url:"/m/bbs_comment_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href="/pc/talk_comment.go?userId="+frm.userId.value+"&bbsCommentSeq="+frm.rCommentSeq.value+"&bbsSeq="+frm.bbsSeq.value;
				}
			}
		});

		return false;
	}
	function bbsCommentDelete(seq,rcomment,bbsSeq,frm) {
		if(confirm("댓글을 삭제하시겠습니까?")) {
			
		
			
				var param = {
						bbsSeq : bbsSeq,	
						bbsCommentSeq : seq,
						pbbsCommentSeq : rcomment,
						userId:searchForm.userId.value
				};
				
				$.ajax({
					type:"POST",
	
					url :"/m/bbs_Comment_delete_do.go",
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
	function openUser(ImgId,userId) {
		if (ImgId=="") {
			alert("가상계정을 설정해주세요.");
		} else{
			pop.openWindow("/pc/userProfile.go?userId="+userId, 'chatUser', 250, 360, 'no', 'no');
		}
		
	}
	function openSelectorPop(frm) {
		pop.openWindow("/pc/chat_user.go?agentId="+frm.userId.value, 'chatUser', 1040, 400, 'yes', 'yes');
	}
	
	function reloadPage() {
		//document.location.reload();
		//window.location = "jscall://callAddUser?"+searchForm.chatId.value+","+searchForm.chatName.value;
		document.location.href="/pc/talk_comment.go?bbsSeq="+searchForm.bbsSeq.value+"&userId="+searchForm.chatId.value+"&page=1"+"&bbsCommentSeq="+searchForm.bbsCommentSeq.value;
		//alert("/pc/talk_comment.go?bbsSeq="+searchForm.bbsSeq.value+"&userId="+searchForm.chatId.value+"&page=1&bbsCommentSeq="+searchForm.bbsCommentSeq.value);
	}

	function zoom(fileName) {
		
		img1= new Image(); 
		 img1.src=(fileName); 
		 imgControll(fileName); 
		return true;
	}

	function imgControll(img){ 
		 if((img1.width!=0)&&(img1.height!=0)){ 
		    viewImage(img); 
		  } 
		  else{ 
		     controller="imgControll('"+img+"')"; 
		     intervalID=setTimeout(controller,20); 
		  } 
		}
	function viewImage(img){ 
		 W=img1.width; 
		 H=img1.height+50; 
		 O="width="+W+",height="+H+",scrollbars=yes"; 
		 imgWin=window.open("","",O); 
		 imgWin.document.write("<html><head><title>:*:*:*: 이미지상세보기 :*:*:*:*:*:*:</title></head>");
		 imgWin.document.write("<body topmargin=0 leftmargin=0>");
		 imgWin.document.write("<img src="+img+" onclick='self.close()' style='cursor:pointer;' title ='클릭하시면 창이 닫힙니다.'>");
		 imgWin.document.close();
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
</style>
</head>
<body style="background-color:#eee;">

	<form method="post" name="searchForm" id="listForm" onsubmit="return searchList(this,1); return false;">
	<input type="hidden" name="userId" value="${USER_ID}">
	<input type="hidden" name="page" value="1">
	<input type="hidden" name="keyword" value="">
	<input type="hidden" name="bbsSeq" value="${bbsSeq}">
	<input type="hidden" name="bbsCommentSeq" value="${bbs.bbsCommentSeq}">
	<header class="notice">
		<div class="left-btn"><button type="button" onclick="history.back(-1);"><img src="/images/bullet_arrow_left.png" style="width:30px; padding:2px 10px;"></button></div>
		<div class="right-btn">
			<input type="text" name="chatId" value="${IMG_ID}" readonly="readonly">
			<input type="hidden" name="chatName" value="${IMG_NAME}">
			<button type="button" class="btn-blue" onclick="openSelectorPop(this.form);">변경하기</button>
		</div>
	</header>
	

	<div class="top-blank"></div>
	
	<ul>
		<li>
			
			<div style="height:10px;"></div>
			
			<div style="width:100%; padding-top:10px; background-color:#fff;">
				<table style="margin:0 10px 10px;">
				<colgroup>
					<col width="60">
					<col width="*">
				</colgroup>
				<tr>
					<td>
						<c:choose>
							<c:when test="${bbs.gender==1}">
								<div class="photo-detail" style="background-image:url('${bbs.photo == '' ? '/images/icon_man.png' : bbs.photo}')" onclick="openUser('${IMG_ID}','${bbs.userId}');"></div>
							</c:when>
							<c:otherwise>
								<div class="photo-detail" style="background-image:url('${bbs.photo == '' ? '/images/icon_woman.png' : bbs.photo}')" onclick="openUser('${IMG_ID}','${bbs.userId}');"></div>
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<b>${bbs.user_name} </b>
						<br>
						${bbs.area} ${bbs.userAge} ${bbs.genderText} 
						<br>
					
					</td>
				</tr>
				</table>
				
				<div style="padding:10px; border-top:1px solid #aaa;">
					<% pageContext.setAttribute("LF", "\n"); %>
					${fn:replace(bbs.bbsContents, LF,'<br>')}
				</div>
				
				<div style="padding:10px;">
					
						<div style="padding:10px;">
						
								<img src="${bbs.files}" style="max-width:100%;" onclick="zoom('${bbs.files}')">
							
						</div>
				
				</div>

			</div>

		</li>
	</ul>

	<div style="height:10px;"></div>
	<div>					
	<ul>
		<c:choose>	
			<c:when test="${commentList.size() > 0}">
				<c:forEach var="it" items="${commentList}">
					<li class="">
						
						<div style="width:100%; padding-top:10px; background-color:#fff; border-bottom:1px solid #aaa;">
							<table style="margin:0 10px 10px;">
							<colgroup>
								<col width="60">
								<col width="*">
							</colgroup>
							<tr>
								<td>
									<c:choose>
										<c:when test="${it.gender==1}">
											<div class="photo-detail" style="background-image:url('${it.photo == '' ? '/images/icon_man.png' : it.photo}')" onclick="openUser('${IMG_ID}','${it.userId}');"></div>
										</c:when>
										<c:otherwise>
											<div class="photo-detail" style="background-image:url('${it.photo == '' ? '/images/icon_woman.png' : it.photo}')" onclick="openUser('${IMG_ID}','${it.userId}');"></div>
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<b>${it.user_name} </b>
									<br>
									${it.area} ${it.userAge} ${it.genderText} 
									<br>
									
							
									<c:if test="${it.userId ==IMG_ID}">
										<button name="edit" class="btn" onclick="location.href='/pc/talk_comment_edit.go?userId=${IMG_ID}&bbsCommentSeq=${it.bbsCommentSeq}&bbsSeq=${bbs.bbsSeq}';return false">수정</button>
										<button name="del" class="btn-red" onclick="bbsCommentDelete('${it.bbsCommentSeq}','${it.rCommentSeq}','${bbs.bbsSeq}',this.form)">삭제</button>
									</c:if>
									
										
									
								</td>
							</tr>
							</table>
							
							<div style="padding:0px 5px 0px 68px;">
								<% pageContext.setAttribute("LF", "\n"); %>
								<a href="/pc/talk_comment.go?userId=${IMG_ID}&bbsCommentSeq=${it.bbsCommentSeq}&bbsSeq=${bbs.bbsSeq}"></a>${fn:replace(it.bbsContents, LF,'<br>')}
							</div>
							
							<div style="clear:both; padding:10px 10px 10px 70px;">
								<c:if test="${it.files!=''}">
									<img src="${it.files}" style="max-width:100%;" onclick="zoom('${it.files}')">
								</c:if>
							</div>


						</div>
					</li>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:if test="${bbs.rLevel <1 }">
				<li style="height:50px; text-align:center; padding-top:30px; "> 댓글이 없습니다.</li>
				</c:if>
			</c:otherwise>												
		</c:choose>
	 ${paging}	
	</ul>
	</div>
	</form>
	
	
<c:if test="${IMG_ID!=''}">
 <form method="post" name="bbsEditForm" id="bbsEditForm" enctype="multipart/form-data" action="/m/photo_upload.go" style="display:block;">
	<input type="hidden" name="userId" value="${IMG_ID}">
	<input type="hidden" name="bbsCommentSeq" value="-1">
	<input type="hidden" name="bbsSeq" value="${bbsSeq }">
	<input type="hidden" name="path" value="bbs">
	<input type="hidden" name="photo" value="">
	<input type="hidden" name="rCommentSeq" value="${bbs.bbsCommentSeq}">
	<c:if test="${bbs.rLevel <1 }">
		<div class="file-box-ed">
			<ul class="file-add">
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
			<div class="add-text">
				<input type="text" name="bbsContents" class="itext" onkeypress="checkEnter(event, this.form, this);">
			</div>
			<div class="add-btn">
				<button type="button" class="btn" onclick="submitForm(this.form);">전송</button> 
			</div>
		</div>
	</c:if>
	</form>
</c:if>
</body>
</html>