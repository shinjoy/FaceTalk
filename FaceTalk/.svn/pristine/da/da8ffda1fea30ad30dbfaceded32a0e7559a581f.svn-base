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



</style>

<div id="fnc_table">
	

	<dl >
		<c:choose>
			<c:when test="${list.size() > 0}">
				<c:forEach var="it" items="${list}">
					<dt>
					 	<table class="list">
					 	<colgroup>
					 	
						 		<col width="5%">
								<col width="5%">
								<col width="5%">
								<col width="5%">
								<col width="15%">
								<col width="10%">
								<col width="10%">
								<col width="20%">
								<col width="10%">
						</colgroup>
										 	
						<tr>
							<td style="padding-left:15px;">  ${it.userFncSeq}</td>
							<td style="padding-left:15px;">  ${it.siteName}</td>
							<td style="padding-left:15px;">  ${it.userTypeText}</td>
								<td style="padding-left:35px;">
										<c:choose>
											  <c:when test="${empty it.photo}">
											  		<div class="photo-list" style="background-image:url('/images/user_default.png')"></div> 
											  </c:when>	
											  <c:otherwise>
											  		<div class="photo-list" style="background-image:url('${it.photo}')" onclick="pop.zoom('${it.photo}');"></div>
											  </c:otherwise>
										</c:choose>
									</td>
							
							<td class="text-left">
							<c:choose>
								<c:when test="${it.userType==4 }">
									<a href="/admin/user/user_view.go?userId=${it.blockId}">
								</c:when>
								<c:otherwise>
									<a href="/admin/user/user_imagine_edit.go?userId=${it.blockId}&userSeq=${it.userSeq}">
								</c:otherwise>
								
							</c:choose>
								${it.blockName}(${it.userAge})<br>(${it.blockId})</a></td>
							<td>${it.statustxt }</td>
							<td style="padding-left: 20px;">
							<c:if test="${it.fncFiles !='' }">	
								<div class="photo-talk3"
									style="background-image:url('${it.fncFiles}')"
									onclick="pop.zoom('${it.fncFiles}');"></div>
							</c:if>
							</td>
							<td class="text-left">${it.fncReasontxt}</td>
							<td class="text-left">${it.contents}</td>
							<td>${fn:substring(it.regDate,0,16)}</td>
						 </tr>	
						</table>
					</dt>
			
					
					</c:forEach>
				
		</c:when>
		<c:otherwise>
			<td colspan="10" style="height: 200px;">조회된 데이터가 없습니다.</td>
		</c:otherwise>

	</c:choose>
	</dl>
	
		
</div>
${paging}

