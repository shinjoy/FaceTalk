<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	$(function() {
		    $( "#sortable" ).sortable();
		    $( "#sortable" ).disableSelection();
	});
</script>
 <style>
  #sortable { list-style-type: none; margin: 0; padding: 0; width: 100%; }
  #sortable li { margin: 3px 3px 3px 0; padding: 1px; float: left; width: 300px; height: 200px; font-size: 3; text-align: center; }
</style>	
	<div id="sortable">
		<c:choose>	
		<c:when test="${list.size() > 0}">
			<c:forEach var="it" items="${list}">
		
				
				<li class="ui-state-default"  >
					<a href="/admin/guide/guide_edit.go?seq=${it.noticeSeq}"> <img src="${it.contentsHtml}" style="max-height:300px; max-width: 200px;" > <br>
					${it.siteName}<br>
					${it.startDate.substring(0,10)}~${it.endDate.substring(0,10)}</a>
					<input type="hidden" name="noticeSeq" class="sort" value="${it.noticeSeq}"/>
				</li>
					
					
					
			</c:forEach>
		</c:when>
			<c:otherwise>
					<ul style="text-align:center;"><li>조회된 데이터가 없습니다.</li></ul>
			</c:otherwise>
		</c:choose>
		<br>
		<div style="clear:both;"></div>
	</div>

	
