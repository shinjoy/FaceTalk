<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>


<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(1,9);
	//searchList(listForm,1);
});

function friendReqOpen() {
	pop.openWindow("/pc/friendReqOpen.go?userId="+listForm.agentId.value+"&friendId="+listForm.userId.value, 'chatUser', 200, 200, 'yes', 'yes');
}

function friendReq() {
	var param = {
			friendId: listForm.friendId.value,
			userId	: listForm.userId.value,
			message	: listForm.message.value
	};
		
	
	$.ajax({
		type:"POST",
		url:"/m/request_friend.go",
		dataType:"json",
		data:param,
		success:function(json){
			alert(json.message);
			window.close();
			
			
		}
	});
	return false;
	
}




</script>

<style>
</style>


</head>
<body style="background-color: #ccc;">

	<!-- <section class="main-cover main-row" > -->
	
	<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
	<input type="hidden" name="friendId" value="${friendId}">
	<input type="hidden" name="userId" value="${userId}">
		
		<div style="padding:10px;">
			<textarea  cols="30" rows="3"  name="message" style="padding:5px;">우리 친구해요~^^*</textarea>
		</div>
		<div style="text-align:center;">
			<button type="button" class="btn-blue" onclick="friendReq();"><span style="color: yellow;">신청</span></button>
		</div>
		
	</form>



</body>
</html>