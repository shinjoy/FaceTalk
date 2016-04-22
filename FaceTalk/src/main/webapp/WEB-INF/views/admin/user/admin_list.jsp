<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
		
		<table class="list">
			<colgroup>
				<col width="15%">
				<col width="15%">
				<col width="20%">
				<col width="*">
				<col width="10%">
			</colgroup>

			<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					<c:forEach var="it" items="${list}">
						
						<tr>
							<td class="text-left">${it.siteName}</td>
							<td><a href="/admin/user/admin_edit.go?userId=${it.userId}&seq=1">${it.userTypeText}</a></td>
							<td class="text-left">${it.userName}</td>
							<td class="text-left"><a href="/admin/user/admin_edit.go?userId=${it.userId}&seq=1">${it.userId}</a></td>
							<td>${fn:substring(it.regDate,0,10)}</td>						
						</tr>
						
				</c:forEach>
				</c:when>
				<c:otherwise>
				<tr>
					<td colspan="5" style="height:200px;">조회된 데이터가 없습니다.</td>
				</tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
			
		${paging}
		
	
	
