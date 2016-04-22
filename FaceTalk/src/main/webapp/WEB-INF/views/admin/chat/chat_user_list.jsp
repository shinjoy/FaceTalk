<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


				<colgroup>
					<col width="8%">
					<col width="*">
					<col width="15%">
					<col width="15%">
					<col width="7%">
					<col width="7%">
					
					
				</colgroup>
			<table style="margin: 10px 10px 20px 10px;">
				
			
			<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					<c:forEach var="it" items="${list}">
						<c:choose>
							<c:when test="${it.owner==1}">
								<tr style="background-color:#ABF200; ">
							</c:when>
							<c:otherwise>
								<tr>
							</c:otherwise>						
						</c:choose>
						
							<td>
								<c:choose>
									  <c:when test="${empty it.photo}">
									  		<div class="photo-list" style="background-image:url('/images/user_default.png')"></div> 
									  </c:when>	
									  <c:otherwise>
									  		<div class="photo-list" style="background-image:url('${it.photo}')" onclick="pop.zoom('${it.photo}');"></div>
									  </c:otherwise>
								</c:choose>
							</td>
							<td class="text-left"><h2>${it.userName} (${it.userAge} /  ${it.genderText})
							<br> [${it.userId}]</h2>
							
							</td>
							
							
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
		
	
	
