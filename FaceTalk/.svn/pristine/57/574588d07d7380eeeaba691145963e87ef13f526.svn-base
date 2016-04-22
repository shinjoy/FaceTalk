<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(2,1);
		
	});
	
	function searchList(searchForm,page) {

		
		var bbsSeq = searchForm.bbsSeqq.value;
		var userId = searchForm.userId.value;

		document.location.href = "/admin/talk/talk_view.go?userId="+userId+"&bbsSeq="+bbsSeq+"&page="+page;
		

		return false;
	}
	
	function submitForm(frm) {
			
		var param = {
			bbsSeq	: frm.bbsSeq.value,
			reportCount :frm.reportCount.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/talk/talk_edit_do.go",
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
	
	function blindFormDeal(frm) {
		
		var param = {
			bbsSeq	: frm.bbsSeq.value,
			blindCount :frm.blindCount.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/talk/talk_edit_do.go",
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
	
	function catemove(frm,bbsSeq) {
		
		var param = {
			bbsSeq	: bbsSeq,
			cate :frm.cate.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/talk/cate.go",
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
	
	function deleteNotice(bbsSeq,userId) {
		if(confirm("토크를 삭제하시겠습니까?")) {
			var param = {
					bbsSeq	:	bbsSeq,
					talkId  :   userId,
					userId  :   userId
			};
			
			$.ajax({
				type:"POST",
// 				url:"/admin/talk/talk_delete_do.go",
				url:"/m/bbs_delete_do.go",		
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/talk/talk.go";
					}
				}
			});
		}
		return false;

	}
	
	function deleteComment(bbsCommentSeq,bbsSeq,userId) {
		if(confirm("댓글을 삭제하시겠습니까?")) {
			var param = {
					bbsCommentSeq	:	bbsCommentSeq,
					bbsSeq : bbsSeq,
					userId : userId,
					pbbsCommentSeq : bbsCommentSeq
			};
			
			$.ajax({
				type:"POST",
// 				url:"/admin/talk/comment_delete_do.go",
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
	
</script>

<style>
.tbl-list TD{
	border-bottom: 1px solid #ddd;
	padding: 5px;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 토크관리 > 토크 상세보기
			</header>
		
			<div class="contents-block">
			
				<h1>토크 상세보기</h1>
				
				<div style="text-align:right; margin-bottom:10px;">
					<button type="button" class="btn"  onclick="document.location.href='/admin/talk/talk.go';" style="width:100px; background-color:#ae4;">목록</button>
				</div>
				<form method="post" name="searchForm" id="searchForm" onsubmit="return searchList(this,1); return false;">
				<input type="hidden" name="bbsSeqq" value="${bbs.bbsSeq}">
				<input type="hidden" name="userId" value="${USER_ID}">
				<table class="register">
				<tr>
					<td rowspan="3">
						<div class="photo-detail" style="background-image:url('${bbs.photo=='' ? '/images/img_pro_defult.png' : bbs.photo}')"></div>
					</td>
					<th>아이디</th><td>${bbs.userId}</td>
					<th>등록일자</th><td>${fn:substring(bbs.regDate,0,16)}</td>
					<th>블라인드처리</th>
					<td>
						<c:choose>
							<c:when test ="${bbs.blindCount>0}">
								<form method="post" name="reportForm" onsubmit="return false;" style="float:left; margin-right:10px;">
									<input type="hidden" name="bbsSeq" value="${bbs.bbsSeq}">
									<input type="hidden" name="bbsCommentSeq" value="0">
									<input type="hidden" name="blindCount" value="0">
									<button type="button" class="btn" onclick="blindFormDeal(this.form);">블라인드 해제</button>
								</form>
							</c:when>
							<c:otherwise>
								<form method="post" name="blindForm" onsubmit="return false;" style="float:left; margin-right:-20px;">
									<input type="hidden" name="bbsSeq" value="${bbs.bbsSeq}">
									<input type="hidden" name="bbsCommentSeq" value="0">
									<input type="hidden" name="blindCount" value="1">
									<button type="button" class="btn" onclick="blindFormDeal(this.form);">블라인드 처리</button>
								</form>
							</c:otherwise>
						</c:choose>	
					</td>
				</tr>
				<tr>
					<th>이름</th><td>${bbs.userName}</td>
					<th>게시글 상태</th><td>${bbs.blindCount == 1 ?  '<span color="#f40">블라인드 게시글</span>' : '게시중' }</td>
					<th>주제변경</th>
					<td>
						<form method="post" name="cateForm">
							<select name="cate">
								<c:forEach items="${cate}" var ="it">
									<c:if test="${it.categorySeq!=0 }">
										<option value="${it.categorySeq}"  ${bbs.bbsCategory ==it.categorySeq? 'selected=\"selected\"' : ''} >${it.categoryName }</option>
									</c:if>
								</c:forEach>
							</select>
							<button type="button" class="btn" onclick="catemove(this.form,${bbs.bbsSeq})" style="background-color:#fe5;">변경</button>
						</form>
					</td>
				</tr>
				<tr>
					<th>카테고리</th><td>${bbs.categoryName}</td>
					<th>호응</th>
					<td>
						<span style="good">♥좋아요:${bbs.likeCount}</span>
						<span style="good">♠댓글:${bbs.commentCount}</span>
						<span style="good">♣신고:${bbs.reportCount}</span>
						<span style="good">♣댓글의 댓글:${bbs.bbscomCount}</span>
					</td>
					<th>토크삭제</th>
					<td>
						<form method="post" name="talkForm">
						<input type="hidden" name="bbsSeq" value="${bbs.bbsSeq}">
							<button type="button" class="btn-red" onclick="deleteNotice(${bbs.bbsSeq},'${bbs.userId}');">삭제</button>
						</form>
					</td>
				</tr>
				</table>
				<div class="bbs-contents">
					<% pageContext.setAttribute("LF", "\n"); %>
					<h3>${fn:replace(bbs.bbsContents, LF,'<br>')}</h3>
				
					<br>
					
					<c:choose>
						<c:when test="${fileLists.size() > 0 }">
						
							 <c:forEach items="${fileLists}" var="it">
								  <div class="photo-talk2" style="background-image:url('${it}') " onclick="pop.zoom('${it}');" >
									
								  </div> 
							</c:forEach>
						</c:when>
					</c:choose>
					<c:if test="${bbs.kind==2 }">
					<div class="shareBox" style="border: 2px solid gold; width: 60%;">
						<table style="margin: 10px 10px 10px 10px;">
							<colgroup>
								<col width="60">
								<col width="*">
							</colgroup>
							<tr>
								<td><c:choose>
										<c:when test="${bbs.writerGender==1}">
											<div class="photo-detail"
												style="background-image:url('${bbs.writerPhoto == '' ? '/images/icon_man.png' : bbs.writerPhoto}')"></div>
										</c:when>
										<c:otherwise>
											<div class="photo-detail"
												style="background-image:url('${bbs.writerPhoto == '' ? '/images/icon_woman.png' : bbs.writerPhoto}')"></div>
										</c:otherwise>
									</c:choose></td>
								<td><b>${bbs.writerName} </b> 
								<br> ${bbs.writerArea}
									${bbs.writerAge} ${bbs.writergendertxt} <br> 
									<span class="good"><img src='/images/btn_good_dw.png'
										style="height: 12px;"> ${bbs.writerLikeCount}</span> 
									<span class="good"><img src='/images/btn_reply_dw.png'
										style="height: 12px;"> ${bbs.writerCommnetCount}</span></td>
							</tr>
						</table>

						<div style="padding: 10px; border-top: 1px solid #aaa;">
							${bbs.writerComment}
						</div>

						<div style="clear: both;"></div>

						<div style="padding: 10px;">

							<div class="img-thumb">
								<c:if test="${fileList2.size()>0}">
									<c:forEach items="${fileList2}" var="it2" varStatus="i">
										<c:if test="${i.index lt 3 }">
											<div class="photo-talk"
												style="background-image:url('${it2}') "></div>
										</c:if>
										<c:if test="${i.index == 3}">
											<h3>MORE..</h3>
										</c:if>
									</c:forEach>
								</c:if>
							</div>
						</div>
					</div>
				</c:if>
							
				</div>
				
				<h2 style="margin-top:20px;">댓글</h2>
				
				<div class="tab-list" >
				
					<ul class="reply-list">
					<c:choose>
						<c:when test="${list.size() > 0}">
						<form method="post" name="commentForm">
							<c:forEach var="it" items="${list}" varStatus="i">
								<input type="hidden" name="bbsCommentSeq" value="${it.bbsCommentSeq}">
								<input type="hidden" name="bbsSeq1" value="${bbs.bbsSeq}">
								<li>
									<dl style="padding-left:${(it.rLevel * 20) + 20}px;">
										<dt>
											<div class="photo-small" style="background-image:url('${it.photo=='' ? '/images/img_pro_defult.png' : it.photo}')"></div>
										</dt>
										<dd>
											${it.user_name} (${it.userId})<br>
											${fn:substring(dl.regDate,0,16)}<button style="margin-left:10px;" type="button" class="btn-red" onclick="deleteComment(${it.bbsCommentSeq},${bbs.bbsSeq},'${bbs.userId}');">삭제</button>
											<div style="margin:10px 0;">
												${it.bbsContents}
												<c:if test="${it.files!=''}"><img src="${it.files}" style="max-width:300px;"></c:if>											
											</div>
										</dd>
									</dl>
								</li>
							</c:forEach>
						</form>
						</c:when>
						<c:otherwise>
							<li style="height:100px; padding-top:80px; text-align: center;">등록된 댓글이 없습니다.</li>
						</c:otherwise>
					</c:choose>
					</ul>
					${paging}
				</div>	
				</form>	
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>