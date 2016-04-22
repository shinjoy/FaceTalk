<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
		
		<table class="list">
		<colgroup>
			<col width="80%">
			<col width="*">
		</colgroup>
			<thead>
				<th>약관내용</th>
				<th>등록일자</th>
			</thead>
			
			<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					
					</c:when>
					<c:otherwise>
						<td colspan="10" style="height:200px;">조회된 데이터가 없습니다.</td>
					</c:otherwise>
					 	
			</c:choose>
			</tbody>
		</table>
			
		${paging}
		
	
	
