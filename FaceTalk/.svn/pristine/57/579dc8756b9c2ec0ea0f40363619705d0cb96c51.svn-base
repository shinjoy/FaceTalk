<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>		
		<div class="tbl-list" style="width:100%;">
			<ul>
				<c:choose>	
					<c:when test="${list.size() > 0}">
						<c:forEach var="it" items="${list}">
							<li class="imglist">
								<div class="photo-show" style="background-image:url('${it.fileName == '' ? '/images/user_default.png' : it.fileName}')" onclick="pop.zoom('${it.fileName}');"></div>
								<div class="photo-show-info">
									<b>${it.userName} ( ${it.userAge} )</b>
									<br>
									${it.genderText} | ${it.area}
									<br>
									${fn:substring(it.regDate,0,16)}
								</div>
							</li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<li style="height:100px; text-align:center; padding-top:80px; "> 조회된 데이터가 없습니다.</li>
					</c:otherwise>												
				</c:choose>
				
			</ul>
			<div style="clear:both;"></div>
		</div>

		${paging}
		
		
						