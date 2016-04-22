<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

			<table class="list">
				<colgroup>
					<col width="10%">			
					<col width="10%">
					<col width="5%">
					<col width="5%">
					<col width="8%">
					<col width="8%">
					<col width="8%">
					<col width="10%">
					<col width="10%">
					<col width="5%">
				</colgroup>
			
			<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					<c:forEach var="it" items="${list}">
						<tr>
							<td class="text-left">${it.typeTxt}</td>
							<td class="text-left">${it.userId}</td>
							<td class="text-left">${it.userName}</td>
							<td><fmt:formatNumber>${it.inPoint}</fmt:formatNumber></td>
							<td><fmt:formatNumber>${it.inMoney}</fmt:formatNumber></td>
							<td><fmt:formatNumber>${it.outPoint}</fmt:formatNumber></td>
							<td><fmt:formatNumber>${it.outMoney}</fmt:formatNumber></td>
							<td><fmt:formatNumber>${it.remainPoint}</fmt:formatNumber></td>
							<td><fmt:formatNumber>${it.remainMoney}</fmt:formatNumber></td>
							<td>${fn:substring(it.regDate,0,16)}</td>
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
		
	
	
