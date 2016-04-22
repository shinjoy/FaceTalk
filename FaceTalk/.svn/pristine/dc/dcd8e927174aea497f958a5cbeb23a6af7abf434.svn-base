<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

	<link rel="stylesheet" type="text/css" href="/css/bb-admin-mobile.css" />
	
<script type="text/javascript">

function changeNoc(frm){
	
	var arr = new Array();
	var arrSeq = "" ; 
	
	$.each($(".arr"), function( index, obj ) {
		
        	arr.push($(obj).val());
	});
	
	arrSeq = arr.join(",");
	
	
	 var param = {
				arrSeq  : arrSeq,
	 			comment : frm.comment.value
	};
	 $.ajax({

			type : "POST",
					url : "/admin/point/changeNo.go",
					dataType : "json",
					data : param,
					success : function(json) {
						
						if (json.result) {
							window.close();
						}
					}
				});

			return false;
}


</script>

</head>

<body class="white-background">

	<form metohd="post" name="listForm" style="height:100%;" onsubmit="return changeNoc(this); return false;">
	
		<c:forEach items="${list}" var="it">
			<input type="hidden" name="arr" class="arr" value="${it}">
		</c:forEach>
		<div style="padding-left: 5px; margin-top: 10px;background-color: white;" >
			<span style="padding-left: 20px; margin-top: 30px;">불가 사유</span>
			<input type="text" name="comment">
			
			<div class="btn-tools" style="padding-left: 50px; padding-top:10px;">
				<button class="btn-green" type="submit">완료</button>
			</div>
			
		</div>

	</form>

</body>

</html>