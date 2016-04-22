<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

			<table class="list">
			<colgroup>
				<col width="5%">
				<col width="5%">
				<col width="10%">
				<col width="5%">
				<col width="10%">
				<col width="10%">
				<col width="20%">
				<col width="5%">
				<col width="5%">
			</colgroup>

	<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					<c:forEach var="it" items="${list}">
						<tr>
						<td class="text-left">${it.expenseSeq}</td>
						<td class="text-left">${it.siteName}</td> 
							<td class="text-left">${it.userId}</td>
							<td class="text-left">${it.userName}</td>
							<td><fmt:formatNumber>${it.point}</fmt:formatNumber>  </td>
							<td><fmt:formatNumber>${it.payPoint}</fmt:formatNumber>  </td>
							<td>${it.comment}</td>
							<td>${fn:substring(it.requestDate,0,16)}</td>
							<td>${fn:substring(it.finishDate,0,16)}</td>
						</tr>
						
					</c:forEach>
					</c:when>
					<c:otherwise>
						<td colspan="10" style="height:200px;">조회된 데이터가 없습니다.</td>
					</c:otherwise>
					 	
			</c:choose>
			</tbody>
		</table>
			
		${paging}
		
	
	
