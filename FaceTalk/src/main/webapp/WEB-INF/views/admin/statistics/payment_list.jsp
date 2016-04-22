<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		
	<table class="list">
		<colgroup>
			<col width="10%">
			<col width="*">
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="20%">				
		</colgroup>
	<tbody class="rl">
	<c:choose>
		<c:when test="${list.size() > 0}">
			<c:forEach var="it" items="${list}">
				<tr>
					<td>${it.orderSeq}</td>
					<td style="text-align:left; padding-left:5px;">${it.userId}</td>
					<td><fmt:formatNumber>${it.priceSum}</fmt:formatNumber>  </td>
					<td><fmt:formatNumber>${it.totalFee}</fmt:formatNumber>  </td>
					<td>${it.paytypeText}</td>
					<td>${it.orderDate.substring(0,16)}</td>

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

	
