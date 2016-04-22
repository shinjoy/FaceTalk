<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(1,1);
	});
	
	function deleteUser(userId) {
		
		if(confirm("회원을 삭제합니다. 회원의 모든 활동내역이 제거됩니다. 삭제된 내용은 복구될 수 없습니다. 삭제를 진행하시겠습니까?")) {
			var param = {
					userId	:	userId
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/user/user_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/user/user.go";
					}
				}
			});
		}
		return false;
	}
 	function ch(frm) {
		
 		var check =$("input:radio[name=kind]").is(":checked") ;
 		if(check){
	 		frm.action="/admin/user/point.go";
			frm.submit();
 		}else{
 			alert("환급종류를 선택해주세요");
 		}
		
		return false;
	} 

</script>

<style>
</style>


		<form method="post" name="firmForm">
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
							<div class="photo-detail" style="background-image: url('/images/img_pro_defult.png')"></div>
						</c:when>
						<c:otherwise>
							<div class="photo-detail" style="background-image:url('${user.photo}')" onclick="pop.zoom('${user.photo}');"></div>
						</c:otherwise>
					</c:choose>
				</td>
				<th>아이디</th><td> ${user.userId }</td>
				<th>상태</th><td> ${user.statustxt }</td>
			</tr>
			<tr>
				<th>이름</th><td> ${user.userName}</td>
				<th>등록일자</th><td>${fn:substring(user.regDate,0,16)}</td>
			</tr>
			<tr>
				<th>성별</th><td> ${user.genderText}</td>
				<th>최근 접속</th><td>${fn:substring(user.lastLogindate,0,16)}</td>
			</tr>
			<tr>
				<th>나이</th><td> ${user.userAge}</td>
				<th>소개글</th><td> ${user.comment }</td>
			</tr>
			<tr>
				<th>휴대폰</th><td> ${user.phoneNumber}</td>
				<th>레벨</th><td> ${user.userLevel }</td>
			</tr>
			<tr>
				<th>지역</th><td> ${user.area}</td>
				<th>포인트 내역</th>
				<td>
					<b>포인트:</b> <fmt:formatNumber>${user.point}</fmt:formatNumber>
					<b>CASH :</b> <fmt:formatNumber>${user.income}</fmt:formatNumber>
<!-- 					<button type="button" class="btn" style="margin-left: 10px; margin-top: -5px;" onclick="ch(this.form)">환급처리</button> -->
				</td>
			</tr>
			</table>

		</form>

	<div class="btn-tools">
		<button type="button" class="btn-blue" onclick="document.location.href='/admin/user/user_edit.go?userId=${user.userId}';">수정</button>
		<button type="button" class="btn-red"onclick="deleteUser('${user.userId}');">삭제</button>
		<button type="button" class="btn"onclick="document.location.href='/admin/user/user.go';">뒤로가기</button>
	</div>

