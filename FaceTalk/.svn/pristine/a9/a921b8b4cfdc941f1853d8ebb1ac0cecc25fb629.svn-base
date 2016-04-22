<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
  
   
<style>
.photo-talk3 {
	display: inline-block;
	width: 100px;
	height: 100px;
	background-size: cover;
	background-repeat: no-repeat;
	background-position: center;
	border: 1px solid #ccc;
	background-color: #fff;
}
.photo-no {
	display: inline-block;
	width: 100px;
	height: 100px;
	background-size: cover;
	background-repeat: no-repeat;
	background-position: center;
	
}


</style>
<script>


 function blindFormDeal(frm) {
		
		var param = {
			bbsSeq	: frm.bbsSeq.value,
			bbsCommentSeq	: frm.bbsCommentSeq.value,
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
	function deleteNotice(bbsSeq,userId) {
		if(confirm("토크를 삭제하시겠습니까?")) {
			var param = {
					bbsSeq	:	bbsSeq,
					talkId  :   userId,
					userId  :   userId
			};
			
			$.ajax({
				type:"POST",

				url:"/m/bbs_delete_do.go",		
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

	<c:choose>
		<c:when test="${list.size() > 0}">
			<c:forEach var="it" items="${list}">
			
					<table class="list">
						<colgroup>
								<col width="3%">
								<col width="5%">
								<col width="5%">
								<col width="20%">
								<col width="10%">
								<col width="20%">
								<col width="10%">
						</colgroup>
					 	<tr>
								<td>${it.bbsFncSeq}</td>
								<td>${it.siteName}</td>
								<td class="text-left">${it.userName}(${it.userId})</td>
								<td style="padding-left: 20px;">
								<c:choose>
								<c:when test="${it.photo!=''}">
									<div class="photo-talk3"
										style="background-image:url('${it.photo}')"
										onclick="pop.zoom('${it.photo}');"></div>
								</c:when>
								<c:otherwise>
									<div class="photo-no"></div>
								
								</c:otherwise>
								</c:choose>
		
								</td>
								<td class="text-left">${it.fncReasontxt}</td>
								<td class="text-left">${it.contents}</td>
								<td>${fn:substring(it.regDate,0,16)}</td>
						 </tr>	
					</table>
				
				<c:choose>
				<c:when  test="${it.bbs !=null or it.bbsComment != null }">
					<c:choose>
						<c:when test="${it.type==1 }">
							<div class="talk-box" style="margin:auto; border:1px solid gold;">
								<table class="list">
									<tr>
									  	<td>${it.bbs.userName }(${it.bbs.userId })
									  	<br>${fn:substring(it.bbs.regDate,0,16)}</td>
									  	<td>
									  	<c:choose>
											<c:when test ="${it.bbs.blindCount>0}">
												<form method="post" name="reportForm" onsubmit="return false;" style="float:left; margin-right:10px;">
													<input type="hidden" name="bbsCommentSeq" value="0">
													<input type="hidden" name="bbsSeq" value="${it.bbs.bbsSeq}">
													<input type="hidden" name="blindCount" value="0">
													<button type="button" class="btn" onclick="blindFormDeal(this.form);">블라인드 해제</button>
												</form>
											</c:when>
											<c:otherwise>
												<form method="post" name="blindForm" onsubmit="return false;" style="float:left; margin-right:-20px;">
													<input type="hidden" name="bbsSeq" value="${it.bbs.bbsSeq}">
													<input type="hidden" name="bbsCommentSeq" value="0">
													<input type="hidden" name="blindCount" value="1">
													<button type="button" class="btn" onclick="blindFormDeal(this.form);">블라인드 처리</button>
												</form>
											</c:otherwise>
										</c:choose>
									  	</td>
									 </tr>
							
									 <tr><td colspan="2">${it.bbs.bbsContents}</td></tr>
								
									  	<tr>
									  	<td colspan="2">
									  		<c:if test="${ it.bbs.files!=''}">
											  	<div class="photo-talk3" style="background-image:url('${it.bbs.files}')"
													onclick="pop.zoom('${it.bbs.files}');"></div>
											</c:if>	
										</td>
									</tr>
							   </table>
							 </div>
						</c:when>
						<c:otherwise>
							<div class="talk-box" style="margin:auto; border:1px solid gold;">
								<table class="list">
								<tr>
									<td>${it.bbsComment.user_name }(${it.bbsComment.userId })
									<br>${fn:substring(it.bbsComment.regDate,0,16)}</td>
									<td>
										<c:choose>
											<c:when test ="${it.bbsComment.dislikeCount>0}">
												<form method="post" name="reportForm" onsubmit="return false;" style="float:left; margin-right:10px;">
													<input type="hidden" name="bbsSeq" value="0">
													<input type="hidden" name="bbsCommentSeq" value="${it.bbsComment.bbsCommentSeq}">
													<input type="hidden" name="blindCount" value="0">
													<button type="button" class="btn" onclick="blindFormDeal(this.form);">블라인드 해제</button>
												</form>
											</c:when>
											<c:otherwise>
												<form method="post" name="blindForm" onsubmit="return false;" style="float:left; margin-right:-20px;">
													<input type="hidden" name="bbsSeq" value="0">
													<input type="hidden" name="bbsCommentSeq" value="${it.bbsComment.bbsCommentSeq}">
													<input type="hidden" name="blindCount" value="1">
													<button type="button" class="btn" onclick="blindFormDeal(this.form);">블라인드 처리</button>
												</form>
											</c:otherwise>
										</c:choose>	
									  </td>
								</tr>
								<tr><td colspan="2">${it.bbsComment.bbsContents}</td></tr>
								<tr>
									<td colspan="2">
										<c:if test="${ it.bbsComment.files!=''}">
											<div class="photo-talk3" style="background-image:url('${it.bbsComment.files}')"
												onclick="pop.zoom('${it.bbsComment.files}');"></div>
										</c:if>	
									</td>
									
								</tr>
								</table>
							</div>	
					</c:otherwise>
				</c:choose>
				</c:when>
				<c:otherwise>
						삭제된 글 입니다.
				</c:otherwise>
			  </c:choose>	
			</c:forEach>
		</c:when>
		<c:otherwise>
			<td colspan="10" style="height: 200px;">조회된 데이터가 없습니다.</td>
		</c:otherwise>

	</c:choose>
	

</div>
${paging}


