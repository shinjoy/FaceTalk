<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header5.jsp"  %>
<script type="text/javascript">

function onNoticeEdit(frm) {


	if(frm.title.value == ""){
		alert("제목을 입력해주세요.");
		return false;
	}
	
	if(frm.contents.value == ""){
		alert("내용을 입력해주세요.");
		return false;
	}

	
	var param = {
		qnaSeq : frm.noticeSeq.value,
		userId : frm.userId.value,
		title : frm.title.value,
		contents : frm.contents.value
	};
	
	$.ajax({
		type:"POST",
		url:"/m/qna_edit_do.go",
		dataType:"json",
		data:param,
		success:function(json){
			alert(json.message);
			if (json.result) {
				document.location.href = "/m/qna/qna.go?userId="+noticeForm.userId.value;
			}
		}
	});

	
// 	frm.action = "/m/qna/qna_edit_do.go";
// 	frm.submit();
	return false;

}


window.addEventListener('body',function() { setTimeout(scrollTo, 0, 0, 1); }, false); 

$("body").bind('touchmove', function(e){e.preventDefault()}); //스크롤방지


$("input").delegate("#inputFileUpload", 'click', function (e) {
	 
    window.Android.open( "contents", "imgThumbnail"); //"contents" : key , "imgThumbnail" : Thumbnail 이 표시될 div
     
});

$("button").delegate("#input_upload_sample", 'click', function (e) {
	 
    window.Android.open( "contents", "imgThumbnail"); //"contents" : key , "imgThumbnail" : Thumbnail 이 표시될 div
     
});


</script>

</head>

<body class="white-background">

<article>
	<div style="width:340px;">
		<div style="width:340px;" >
			<form method="post" name="noticeForm" id="noticeEdit" enctype="multipart/form-data" onSubmit="return onNoticeEdit(this); return false;" >
			<input type="hidden" name="noticeSeq" value="0"/>
			<input type="hidden" name="userId" value="${loginUser}"/>
			<input type="hidden">
				<div class="toolbox">
					<button type="submit" class="btn" style="margin:5px; width:350px; background-color:#366BB6; color:#fff; border:1px solid #366BB6; height:40px;"  >저장</button>
				</div>
				<div class="inputForm" style="width:340px;">
					<dl>
						<dt style="margin:5px;">제목</dt>
						<dd>						
							<input type="text" name="title" class="itext" style="margin-left:5px; width:337px; height:30px;" placeholder="제목을 입력해주세요.">
						</dd>
						<dt style="margin:5px;">내용</dt>
						<dd>
							<textarea name="contents" rows="10" cols="100" style="margin-left:5px; height:380px; width:337px;" placeholder="내용을 입력해주세요."></textarea>
						</dd>
<!-- 						<dd> -->
<!-- 							<input type="file" id="inputFileUpload" style="margin-left:5px; margin-top:5px;"> -->
<!-- 							<button type="button" class="btn" id="input_upload_sample" style="margin-left:5px; margin-top:5px;">파일 업로드</button> -->
<!-- 						</dd> -->
					</dl>
					<div class="clear"></div>
				</div>
				
			</form>
		</div>
	</div>
</article>

</body>

</html>