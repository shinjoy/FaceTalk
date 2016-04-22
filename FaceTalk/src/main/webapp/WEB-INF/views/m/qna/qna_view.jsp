<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/header5.jsp"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>		

<script type="text/javascript">
window.addEventListener('load',function() { 
	setTimeout(scrollTo, 0, 0, 0); 
}, false); 

$("body").bind('touchmove', function(e){e.preventDefault()}); //스크롤방지


window.addEventListener('body',function() { setTimeout(scrollTo, 0, 0, 1); }, false); 

$("body").bind('touchmove', function(e){e.preventDefault()}); //스크롤방지


function qnaEdit(seq) {
	window.ANDROID.editQna(seq);
}

function qnaDelete(seq) {
	if (confirm("문의 내용을 삭제하시겠습니까?")) {
		
		var param = {
			qnaSeq : seq
		};
		
		$.ajax({
			type:"POST",
			url:"/m/qna_delete.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/m/qna/qna.go?userId="+pageForm.userId.value;
				}
			}
		});

	}
}


</script>	
	
	<form name="pageForm">
	<input type="hidden" name="userId" value="${userId}">
	</form>
	
	<article>
		
		<ul class="bbs-list" >
			<li>
				<div class="bbs">
					<div class="bbs-info">
						<div class="write-info">
							<p class="user-info">${qna.userName}&nbsp;(${fn:substring(qna.regDate,0,10)})</p>
						</div>
					</div>
					<div class="bbs-contents">
						<% pageContext.setAttribute("LF", "\n"); %>
						${fn:replace(qna.contentsHtml, LF,'<br>')} 
					</div>
<!-- 					<div class="bbs-contents" style="width:100%;"> -->
<%-- 						${qna.files} --%>
<!-- 					</div> -->
				</div>
			</li>
		</ul>
		<div class="bbs-toolbar">
		<c:if test="${commentList.size() == 0}">
			<button type="button" onclick="qnaEdit(${qna.noticeSeq});" class="btn">수정</button>
			<button type="button" onclick="qnaDelete(${qna.noticeSeq});" class="btn">삭제</button>
		</c:if>	
		</div>
		
		<c:if test="${commentList.size() > 0}">
			<ul class="bbs-list comment">
				<li>
					<div class="bbs">
						<c:forEach var="it" items="${commentList}">
							<div class="comment-info">				
								<div class="write-info">
									<p class="user-info">${it.userName}&nbsp;(${fn:substring(it.rRegDate,0,10)})</p>
								</div>
							</div>
							<div class="bbs-comment">
								<% pageContext.setAttribute("LF", "\n"); %>
								${fn:replace(it.contentsHtml, LF,'<br>')} 
							
							</div>
						</c:forEach>
					</div>
				</li>
			</ul>	
		</c:if>
				
<!-- 		<div class="left-tool" style="text-align:center; width:100%;"> -->
<!-- 			<a href="/m/qna/qna.go?userId=" class="btn">목록으로</a> -->
<!-- 		</div> -->
		
	</article>

