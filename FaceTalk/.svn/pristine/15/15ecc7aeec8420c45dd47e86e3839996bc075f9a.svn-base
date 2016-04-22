<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
	<table class="list">
	<colgroup>
		<col width="10%">
		<col width="10%">
		<col width="15%">
		<col width="*">
		<col width="20%">				
	</colgroup>
	<thead>
		<tr>
			<th>상태</th>
			<th>번호</th>
			<th>운영사이트</th>
			<th>제목</th>
			<th>등록일</th>
		</tr>
	</thead>
	<tbody class="rl">
	<c:choose>
		<c:when test="${list.size() > 0}">
			<c:forEach var="it" items="${list}">
			
			  <c:choose>
			  	<c:when test="${it.endDate.substring(0,10) >= todayData 
			  			and it.startDate.substring(0,10)<=todayData}">
			  		<tr style="background-color:#FFD700; color=#FF0000;">	
			  		<td>진행중</td>
			  	</c:when>
			  	<c:when test="${it.startDate.substring(0,10)>= todayData }">
			  		<tr>
			  		<td>진행예정</td>
			  	</c:when>
			  	<c:otherwise>
			  		<tr>
			  		<td>마감</td>
			  	</c:otherwise>
			  </c:choose>
				<%-- <tr ${it.endDate.substring(0,10) >= todayData and it.startDate.substring(0,10) <= todayData
				 ? 'style=\"background-color:#FFD700; color=#FF0000;\"' : ''} > --%>
					<td><a href="/admin/event/event_edit.go?seq=${it.noticeSeq}">${it.noticeSeq}</a></td>
					<td>${it.siteName}</td>
					<td style="text-align:left; padding-left:5px;">
						<a href="/admin/event/event_edit.go?seq=${it.noticeSeq}">${it.title}</a>
					</td>
					<td>${it.regDate.substring(0,16)}</td>

			</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="7" class="empty-data">조회된 데이터가 없습니다.</td>
			</tr>
		</c:otherwise>
	</c:choose>
	</tbody>
	</table>
		
	${paging}

	
