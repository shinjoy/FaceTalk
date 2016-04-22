<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/pc/head.jsp"  %>

<script type="text/javascript">

$(document).ready(function() {
	searchList(pageForm,1);
});

function talkGo(keySeq,userId){
	
	localStorage.setItem("chatId", userId);
	
	document.location.href="/pc/talk_view.go?bbsSeq="+keySeq+"&userId="+userId;
	
	
	//<a href="/pc/talk_view.go?bbsSeq='+msg.keySeq+'&userId='+msg.userId+'">'
}
function commentGo(bbsCommentSeq,userId){
	localStorage.setItem("chatId", userId);
	document.location.href="/pc/talk_comment.go?bbsCommentSeq="+bbsCommentSeq+"&userId="+userId+"&bbsSeq=0";
	//<a href="/pc/talk_comment.go?bbsCommentSeq='+msg.keySeq+'&userId='+msg.userId+'&bbsSeq='+0+'">';
	
}





function searchList(frm,page) {

	var param = {
		userId	: frm.userId.value,
		page : page
	};
	
	$.ajax({
		type:"POST",
		url:"/m/message_list.go",
		dataType:"json",
		data:param,
		success:function(json){
			var list = json.list;
			var str = '';
			if (list.length > 0) {
				$("#chat-list").empty();
				for (var i=0; i<list.length; i++) {
					var msg = list[i];
					if (msg.viewCheck==0) {
						str += '<li class="list-cell">';
					} else {
						str += '<li class="list-cell readed">';
					}
					str += '			<ul class="chat-list-cell">';
					str += '		<li class="c1">';
					if(msg.friendPhoto==""){
						if (msg.friendGender==1) {
							str += '			<div class="thumb40" style="background-image:url(\'/images/icon_man.png\')"></div>';
						} else {
							str += '			<div class="thumb40" style="background-image:url(\'/images/icon_woman.png\')"></div>';
						}
					}else{
						str += '			<div class="thumb40" style="background-image:url(\''+msg.friendPhoto+'\')"></div>';
					}
					str += '		</li>';
					str += '		<li class="c2" style="vertical-align:middle;">';
					str += '			<input type="hidden" name="messageSeq" class="messageSeq" value="'+msg.messageSeq+'">'; 
					if(msg.messageType==7){ //댓글등록
						  str += '		<a href="javascript:talkGo('+msg.keySeq+',\''+msg.userId+'\')">';
					  //str += '		<a href="/pc/talk_view.go?bbsSeq='+msg.keySeq+'&userId='+msg.userId+'">';
					  str += '			<h3>'+msg.contents+'</h3>';
					  str += ' </a>';
					}else if(msg.messageType==15){ //좋아요
						  str += '		<a href="javascript:talkGo('+msg.keySeq+',\''+msg.userId+'\')">';
						  str += '			<h3>'+msg.contents+'</h3>';
						  str += ' </a>';
					}else if(msg.messageType==16){ //공유
						  str += '		<a href="javascript:talkGo('+msg.keySeq+',\''+msg.userId+'\')">';
						  //str += '		<a href="/pc/talk_comment.go?bbsCommentSeq='+msg.keySeq+'&userId='+msg.userId+'&bbsSeq='+0+'">';
						  str += '			<h3>'+msg.contents+'</h3>';
						  str += ' </a>';
					}else if(msg.messageType==17){ //댓글의댓글
						  str += '		<a href="javascript:commentGo('+msg.keySeq+',\''+msg.userId+'\')">';
						  //str += '		<a href="/pc/talk_comment.go?bbsCommentSeq='+msg.keySeq+'&userId='+msg.userId+'&bbsSeq='+0+'">';
						  str += '			<h3>'+msg.contents+'</h3>';
						  str += ' </a>';
					}else{
						 str += '			<h3>'+msg.contents+'</h3>';
					}
					
					str +='				<div style="border: 1px solid #48BAE4; height: auto; padding:10px;"><h4><b>'+msg.friendName+'('+msg.friendGendertxt+'/'+msg.friendAge+')</b></h4>';
					if(msg.message!=null &&msg.message!=""){
						str += '<br><hr>'+msg.message+'';
					}
					str +='		</div>		<h4><br><b>['+msg.userId+']</b></h4>';
					str += '			<p>'+msg.regDate.substring(0,16)+'</p>';
					
					str += '		</li>';
					str += '		<li class="c3">';
					str += '			<p>';
					if (msg.messageType==2) {//친구신청
					
						str += '	<button type="button" class="btn-blue" onclick="freindStatus('+msg.keySeq+',1,'+msg.messageSeq+')">수락</button>';
						str += '	<button type="button" class="btn-blue" onclick="freindStatus('+msg.keySeq+',2,'+msg.messageSeq+')">거절</button>';
					}
					else if (msg.messageType==4) { //대화요청
						//str += '  <p><span style="color:#5F00FF;">'+msg.message+'</span></p>';('+msg.keySeq+',1,'+msg.messageSeq+','
						str += '				<button type="button" class="btn-blue" onclick="chatStatus('+msg.keySeq+',1,'+msg.messageSeq+')">수락</button>';
						str += '				<button type="button" class="btn-blue" onclick="chatStatus('+msg.keySeq+',2,'+msg.messageSeq+')">거절</button>';
					} 
					else{
						str += '	<button type="button" class="btn-blue" onclick="onRead('+msg.messageSeq+')">읽음</button>';
						str += '	<button type="button" class="btn-blue" onclick="onDelete('+msg.messageSeq+')">삭제</button>';
					}
					str += '			</p>';
					str += '		</li>';
					str += '	</ul>';
					str += '</li>';
	
				}
			} else {
				str += '<li class="list-cell empty">알림내역이 없습니다.</li>';
			}
			$("#chat-list").append(str);
			$(".paging-block").html(json.paging);
		}
	});
	return false;
}

//삭제
function onDelete(seq) {
	var param = {
		messageSeq	: seq
	};
	
	$.ajax({
		type:"POST",
		url:"/m/message_delete.go",
		dataType:"json",
		data:param,
		success:function(json){
			alert(json.message);
			document.location.reload();
		}
	});
	return false;
}

// 전체 삭제
function onDeleteAll(frm) {
	
	 var arr = new Array();
		var arrSeq = "" ; 
		
		$.each($(".messageSeq"), function( index, obj ) {
			arr.push($(obj).val());
	});
	 
	var param = {
			userId	: pageForm.userId.value,
			type :"pc",
			messageSeq :arr.toString()
	};
	
	$.ajax({
		type:"POST",
		url:"/m/message_delete.go",
		dataType:"json",
		data:param,
		success:function(json){
			alert(json.message);
			document.location.reload();
		}
	});
	return false;
}

//읽음
function onRead(seq) {
	var param = {
		messageSeq	: seq
	};
	
	$.ajax({
		type:"POST",
		url:"/m/message_visited.go",
		dataType:"json",
		data:param,
		success:function(json){
			//alert(json.message);
			document.location.reload();
		}
	});
	return false;
}

//전체 읽음
function onReadAll(frm) {
	
	
	
	var param = {
			userId	: pageForm.userId.value,
	};
		
	
	$.ajax({
		type:"POST",
		url:"/pc/message_visited_all.go",
		dataType:"json",
		data:param,
		success:function(json){
			alert(json.message);
			document.location.reload();
		}
	});
	return false;
}

// 대화 요청 수락
/*
/m/request_chat_answer.go
seq
answer
*/
function chatStatus(seq,answer,msgSeq) {
	
	
	
	var param = {
			seq	: seq,
			answer	: answer
			
	};
		
	
	$.ajax({
		type:"POST",
		url:"/m/request_chat_answer.go",
		dataType:"json",
		data:param,
		success:function(json){
			alert(json.message);
			onRead(msgSeq);
			
			if(json.result==true && answer==1){ 
				//alert("jscall://callRequestChatAccept?"+json.userId+","+json.userName+","+json.userfre+","+json.userfreName);
				 window.location = "jscall://callRequestChatAccept?"+json.userId+","+json.userName+","+json.userfre+","+json.userfreName;
			}                  
		}
	}); 
	return false;
}

function freindStatus(seq,answer,msgSeq) {
	
	
	
	var param = {
			seq	: seq,
			answer	: answer,
	};
		
	
	$.ajax({
		type:"POST",
		url:"/m/request_friend_answer.go",
		dataType:"json",
		data:param,
		success:function(json){
			alert(json.message);
			onRead(msgSeq);
		}
	});
	return false;
}
/* function searchList(searchForm,page) {

	var userId = searchForm.userId.value;
	document.location.href = "/m/message_list.go?userId="+userId+"&page="+page;

	return false;
} */

</script>

<style>
	header.notice { display:table; width:100%; height:40px; background-color:#acf; }
	header.notice .left-btn { display:table-cell; width:50%; text-align:left; }
	header.notice .right-btn { display:table-cell; width:50%; text-align:right; }
	header.notice button { margin:5px 10px; }
	h3 { font-weight:bold; font-size:14px; margin-bottom:5px; }
	li.readed { background-color:#ddd; }
</style>


</head>
<body>

	<form method="post" name="pageForm" onsubmit="return searchList(this,1); return false;">
		<input type="hidden" name="userId" value="${userId}">
		<header class="notice">
			<div class="left-btn"><button type="button" class="btn-blue" onclick="onDeleteAll(this.form)">전체삭제</button></div>
			<div class="right-btn"><button type="button" class="btn-blue" onclick="onReadAll(this.form)">모두읽음</button></div>
		</header>
	
		<article class="list-chat">
			<ul class="list" id="chat-list">
			</ul>
			<div class="paging-block"></div>
		</article>
	</form>
</body>
</html>