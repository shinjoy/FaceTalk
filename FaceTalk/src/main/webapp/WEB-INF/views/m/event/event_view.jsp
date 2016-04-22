<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/header5.jsp"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>		
<script type="text/javascript">

function resize(obj) {
    obj.style.height = "1px";
    obj.style.height = (5+obj.scrollHeight)+"px";
  }
  
function onComment(frm) {

	if(frm.userId.value==""){
		 alert("로그인 후에 가능합니다.");
		 return false;
	}
	
	if(frm.commentContents.value==""){
		 alert("내용을 입력해주세요");
		 return false;
	}
	
	var param = {
		userId : frm.userId.value,
		bbsCommentSeq : frm.bbsCommentSeq.value,
		seq	:	frm.noticeSeq.value,
		comment : frm.commentContents.value
	};
	$.ajax({
	    type:"POST",
	    url:"/m/event/event_comment_edit.go",
	    dataType:"json",
	    data:param,
	    success:function(json) {
			alert(json.message);
			if (json.result) {
				document.location.reload();
			}
	    },
	    error:function(xhr, status, error) {
	        alert(error);
	    },
	    complete:function(data) {
	    }
	});
	
	return false;

}

</script>

	<article>
		<ul class="bbs-list">
			<li>
				<div class="bbs">
					<div class="bbs-info">
						<div class="write-info">
							<p class="user-info">관리자(${fn:substring(event.regDate,0,16)})</p>
						</div>
					</div>
					<div class="bbs-contents">
						${event.contentsHtml}
					</div>
				</div>
			</li>
		</ul>

		<ul class="bbs-list comment">
			<li>
				<div class="bbs">
					<c:forEach var="it" items="${commentList}">
						<div class="comment-info">				
							<div class="write-info">
								<p class="user-info">${it.user_name}&nbsp;(${fn:substring(it.rRegDate,0,10)})</p>
							</div>
						</div>
						<div class="bbs-comment">
							${it.bbsContents}
						</div>
					</c:forEach>
				</div>
			</li>
		</ul>		
	</article>
	
	<footer style="padding:10px 0;">
		<form method="post" name="commentForm" onsubmit="return onComment(this);">
			<input type="hidden" name="noticeSeq" value="${event.noticeSeq}">
			<input type="hidden" name="userId" value="${userId}"/>
			<input type="hidden" name="bbsCommentSeq" value="0">
			<div>
				<div>
					<div style="padding-left: 10px;">
							<textarea name="commentContents" style="width:320px; overflow:hidden; resize:none;" 
						onKeyup="resize(this)" placeholder="댓글을 입력하세요."></textarea>
					</div>
				</div>
			</div>
			<div>
				<button class="btn"  type="submit" style="margin:5px 5px 5px 10px; width:335px; background-color:#366BB6; color:#fff; border:1px solid #366BB6; height:40px;">
					<span>등록</span>
				</button>
			</div>
		</form>
	</footer>
	
