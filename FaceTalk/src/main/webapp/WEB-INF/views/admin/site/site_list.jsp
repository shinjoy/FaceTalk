<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
	<table class="list">
	<colgroup>
		<col width="10%">
		<col width="*">
		<col width="10%">
		<col width="10%">
		<col width="*%">	
		<col width="10%">			
	</colgroup>
	<thead>
		<tr>
			<th>번호</th>
			<th>사이트명</th>
			<th>회원수</th>
			<th>관리자수</th>
			<th>도메인</th>
			<th>등록일</th>
		</tr>
	</thead>
	<tbody class="rl">
	<c:choose>
		<c:when test="${list.size() > 0}">
			<c:forEach var="it" items="${list}">
				<tr>
					<td><a href="/admin/site/site_edit.go?siteSeq=${it.siteSeq}">${it.siteSeq}</a></td>
					<td style="text-align:left; padding-left:5px;"><a href="/admin/site/site_edit.go?siteSeq=${it.siteSeq}">${it.siteName}</a></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td style="text-align:left; padding-left:5px;">${it.siteDomain}</td>
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

	
