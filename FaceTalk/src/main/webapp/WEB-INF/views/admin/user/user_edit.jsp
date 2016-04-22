<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(1,1);
	});
	

	function subForm(frm) {
		
		var use = frm.use.value;
		
		if (frm.use.value == "") {
			alert("사용 여부를 선택해주세요.");
			return true;
		}

		var param = {
			use : $('input:radio[name="use"]:checked').val(),
			userId : frm.userId.value,
			point : frm.point.value,
			userName :frm.userName.value,
			gender : $('input:radio[name="gender"]:checked').val(),
			phoneNumber : frm.phoneNumber.value,
			userAge : frm.userAge.value,
			comment : frm.comment.value,
			userLevel : frm.userLevel.value,
			area : frm.area.value,
			money : frm.fmoney.value
		}; 
		
		$.ajax({
			type:"POST",
			url:"/admin/user/user_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/admin/user/user_view.go?userId="+frm.userId.value;
				}
			}
		}); 

		return false;
	}
	
</script>

</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 회원관리 > 일반회원 수정
			</header>
		
			<div class="contents-block">
			
				<h1>일반회원 수정</h1>
				
		
				<form method="post" name="firmForm" onsubmit="return subForm(this); return false;">
				<input type="hidden" name="userId" value="${user.userId}">
					<table class="register">
					<colgroup>
						<col width="70">
						<col width="*">
						<col width="*">
					</colgroup>
					<tr>
						<td rowspan="6">
						  <c:choose>
						     <c:when test="${empty user.photo }">
								<div class="photo-detail" style="background-image:url('/images/img_pro_defult.png')"></div>
							 </c:when>
							 <c:otherwise>
							 	<div class="photo-detail" style="background-image:url('${user.photo}')"></div>
							 </c:otherwise>
						 </c:choose>
						</td>
						<th>아이디</th><td>${user.userId}</td>
						<th>상태 변경</th>
						<td>
							<label style="margin-right:10px; position:center;"><input type="radio" name="use" value="1" ${user.status == 1 ? 'checked=\"checked\"' : ''}>사용 가능</label>
							<label style="margin-right:10px; "><input type="radio" name="use" value="3" ${user.status == 3 ? 'checked=\"checked\"' : ''}>사용중지(로그인 차단)</label>
							<label><input type="radio" name="use" value="4" ${user.status == 4 ? 'checked=\"checked\"' : ''}>강제탈퇴</label>
						</td>
					</tr>
					<tr>
						<th>이름</th><td><input type="text" class="itext" name="userName" value="${user.userName}"></td>
						<th>등록일자</th><td>${fn:substring(user.regDate,0,16)}</td>
					</tr>
					<tr>
						<th>성별</th>
						<td>
							<input type="radio" name="gender" value="1" ${user.gender == 1? 'checked=\"checked\"' : ''}>남자
							<input type="radio" name="gender" value="2" ${user.gender == 2? 'checked=\"checked\"' : ''}>여자
						</td>
						<th>최근 접속</th><td>${fn:substring(user.lastLogindate,0,16)}</td>
					</tr>
					<tr>
						<th>나이</th><td><input type="text" class="itext" name="userAge" value="${user.userAge}"></td>
						<th>소개글</th><td><input type="text" class="itext" name="comment" style="width:500px;" value="${user.comment }"></td>
					</tr>
					<tr>
						<th>휴대폰</th><td><input type="text" class="itext" name="phoneNumber" value="${user.phoneNumber}"></td>
						<th>레벨</th><td><input type="text" class="itext" name="userLevel" value="${user.userLevel}">Level</td>
					</tr>
					<tr>
						<th>지역</th>
						<td>
							<select name="area">
								<c:forEach items="${location}" var ="it">
									<c:if test="${it.seq!=0 }">
										<option value="${it.areaSido}" ${user.area ==it.areaSido? 'selected=\"selected\"' : ''}>${it.areaSido}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						<th>포인트/CASH</th>
						<td>
							포인트 : <input type="text" class="itext" name="point" value="${user.point}">
							CASH : <input type="text" class="itext" name="fmoney" value="${user.income}">
						</td>
					</tr>
					</table>
					
					<div  class="btn-tools" >	
						<button type="submit" class="btn-blue" >수정</button>
						<button type="button" class="btn" onclick="document.location.href='/admin/user/user_view.go?userId=${user.userId}';">취소</button>
					</div>
				</form>
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>