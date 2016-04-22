<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header5.jsp"  %>

<link rel="stylesheet" type="text/css" href="/css/bb-admin-mobile.css" />
	
<script type="text/javascript">
$(document).ready(function() {
});
</script>

</head>

<body style="background-color:#${popupAd.backgroundColor};">

<img src="/files/popup/${popupAd.contentsHtml}" style="width:100%;">

</body>

</html>