<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/pc/head.jsp"  %>

	<script type="text/javascript">

		$(document).ready(function() {
			localStorage.setItem("chatId","");
			document.location.href = "/pc/chat_list.go?userId="+pageForm.userId.value+"&password="+pageForm.password.value;
		});

	</script>

</head>
<body>

<form name="pageForm">
	<input type="hidden" name="userId" value="${userId}">
	<input type="hidden" name="password" value="${password}">
</form>

</body>
</html>