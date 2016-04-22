<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/pc/head.jsp"  %>


<script type="text/javascript">

$(document).ready(function() {
	if (localStorage.getItem("chatId") != undefined) {
		listForm.chatId.value = localStorage.getItem("chatId");
	}
	searchList(searchForm,1);

});



function searchList(searchForm,page) {
	
	var param = {
		agentId		: 	searchForm.userId.value,
		userId		: 	searchForm.chatId.value,
		keyword : searchForm.keyword.value,
		category   :   searchForm.category.value,
		gender     :   searchForm.gender.value,
		area : searchForm.area.value,
		kind : searchForm.kind.value,
		sort :searchForm.sort.value,
		page :page
	};
	
	$.ajax({
		type:"POST",
		url:"/pc/talk_list.go",
		dataType:"html",
		data:param,
		success:function(msg){
			$("#contents-list").html(msg);
			$('html,body').scrollTop(0);
		}
	});
	return false;
}

function openSelectorPop(frm) {
	pop.openWindow("/pc/chat_user.go?agentId="+frm.userId.value, 'chatUser', 1040, 800, 'yes', 'yes');
}



function onAddBbs() {
	if (searchForm.chatId.value=="") {
		alert("가상계정을 설정해주세요.");
	} else {
		document.location.href = "/pc/talk_edit.go?userId="+searchForm.chatId.value+"&bbsSeq=0";
	}
}

function reloadPage() {
	
	//window.location = "jscall://callAddUser?"+searchForm.chatId.value+","+searchForm.chatName.value;
	document.location.reload();
	
}
function changeChatId() {

}


</script>

<style>

	header.notice { position:fixed; top:0; display:table; width:100%; background-color:#acf; height:40px; }
	header.notice .left-btn { display:table-cell; width:20%; text-align:left; vertical-align:middle; }
	header.notice .right-btn { display:table-cell; width:80%; text-align:right; vertical-align:middle; }
	header.notice input { background-color:rgba(255,255,255,0); width:110px; border:0; color:#fff; text-align:right; }
	header.notice button { margin:0 10px; }
	.top-blank { height:40px; }


	.chat-user { background-color:#acf; padding:10px; }
	.chat-user input { background-color:rgba(255,255,255,0); width:180px; border:0; color:#fff; }

	table.menuedit th {
	   vertical-align: top;
	   text-align :left;
	   padding:5px;
	   font-weight: bold;
	}
	
	table.menuedit td {
		padding:5px;
		text-align :left;
	    vertical-align: top;
	    padding: 3px;
	    border-bottom: 1px solid #ddd;
	}	


</style>


</head>
<body>

	<a name="list-top"></a>

	<form method="post" name="searchForm" id="listForm" onsubmit="return searchList(this,1); return false;">
	<input type="hidden" name="userId" value="${userId}">
	<input type="hidden" name="page" value="1">
	<input type="hidden" name="keyword" value="">
	
		<header class="notice">
			<div class="left-btn">
				<button type="button" class="btn-blue" onclick="onAddBbs();">등록하기</button>
			</div>
			<div class="right-btn">
				<input type="text" name="chatId" value="" readonly="readonly">
				<input type="hidden" name="chatName" value="">
				<button type="button" class="btn-blue" onclick="openSelectorPop(this.form);">변경하기</button>
			</div>
		</header>

	<div class="top-blank"></div>

	<header class="list-filter">
		<ul>
			<li>
				<select name="category" id="category" class="no-border" onchange="searchList(this.form,1);">
					<option value="0">주제</option>
					<c:forEach var="it" items="${themeList}">
						<option value="${it.categorySeq}">${it.categoryName}</option>
					</c:forEach>
				</select>
			</li>
			<li>
				<select name="gender" id="gender" class="no-border" onchange="searchList(this.form,1);">
					<option value="0">성별</option>
					<option value="1">남자</option>
					<option value="2">여자</option>								
				</select>
			</li>
			<li>
				<select name="area" class="no-border" onchange="searchList(this.form,1);">
					<option value="">지역</option>
					<c:forEach items="${sidoList}" var="it" >
						<option value="${it.areaSido}">${it.areaSido}</option>								
					</c:forEach>		
				</select>
			</li>
			<li>
				<select name="sort" class="no-border" onchange="searchList(this.form,1);">
					<option value="0">거리</option>
					<c:forEach items="${distanceList}" var="it" >
						<option value="${it.distanceValue}">${it.distanceName}</option>								
					</c:forEach>		
				</select>
			</li>
			<li>
				<select name="kind" class="no-border" onchange="searchList(this.form,1);">
					<option value="">전체</option>	
					<option value="1">내친구</option>
					<option value="2">MY</option>
				</select>
			</li>
		</ul>
	</header>

	<div id="contents-list" style="background-color:#eee;"></div>
					
 </form>
				

</body>
</html>