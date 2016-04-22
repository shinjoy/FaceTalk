<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>



<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript" src="/lib/bestist/getLatLng.js"></script>


<script type="text/javascript">
  window.___gcfg = {lang: 'ko'};

  (function() {
    var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
    po.src = 'https://apis.google.com/js/plusone.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
  })();


  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-27144290-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();


	
 	$(document).ready(function() {
		aside.setActive(1,9);
		/* 폼 ajax전송 : http://malsup.com/jquery/form/#ajaxForm */
		var options = {
			//target :		'#user-join-result',
			beforeSubmit :	formCheck,
			success :		formSuccess
		};
		$('#firmForm').ajaxForm(options);

 	
 	});
 	
 	

 	function formCheck(formData, frm, options) {
 	 	if (firmForm.agentId.value == "") {
 			alert("채팅관리자를 입력해주세요.");
 			return false;
 		}else if (firmForm.userName.value == "") {
 			alert("이름을 입력해주세요.");
 			return false;
 		}else if (firmForm.gender[0].checked == false && firmForm.gender[1].checked == false) {
 		    alert("성별을 선택해주세요.");
 		    return false;
 		}else if(firmForm.birthYear.value == "") {
 			alert("생년를 입력해주세요.");
 			return false;
 		}else if((firmForm.birthYear.value).length < 4) {
 			alert("생년은 4자리 입니다. ex>1988");
 			return false;
 		}else if(firmForm.phoneNumber.value == "") {
 			alert("휴대폰번호를 입력해주세요.");
 			return false;
 		}else if(firmForm.userId.value == "") {
 			alert("아이디를 입력해주세요.");
 			return false;
 		}else if(validEmail(firmForm.userId.value) == false) {
 			 return false;
 		}else if(firmForm.password.value == "") {
 			alert("패스워드를 입력해주세요.");
 			return false;
 		}else if(firmForm.site.value == 0) {
 			alert("사이트를 선택해주세요.");
 			return false;
 		}else if(firmForm.areaSido.value == "") {
 			alert("지역을 선택해주세요.");
 			return false;
 		}else if(firmForm.latitude.value == 0) {
 			alert("위치를 선택해주세요.");
 			return false;
 		}else{ 
 			return true;
 		}
 	}
 		

 	function formSuccess(responseText, statusText, xhr, $form) {
 		//alert('status: ' + statusText + '\n\nresponseText: \n' + responseText );
 		var json = JSON.parse(responseText);
 		try {
 			if(json.result==false){
 	    		
 	    		alert(json.message);
 	    	}else{
 	    		var user = json.user;
 	    		opener.searchForm.chatId.value = user.userId;
 	    		opener.searchForm.chatName.value = user.userName;
 	    		opener.changeChatId();
 	    		localStorage.setItem("chatId", user.userId);

				//window.location = "jscall://callAddUser?"+firmForm.agentId.value+","+firmForm.userName.value;
 	    		document.location.href = "/pc/chat_user.go?agentId="+firmForm.agentId.value;
 	    		alert(json.USER_ID);
				document.location.href = "/pc/chat_user.go?agentId="+json.USER_ID;
 	    	}
 		} catch (e) {
 	        alert(json.message); 
 		}
 	}


 	
 	
	/**
	 * 이메일만 입력가능합니다.
	 * @param val
	 * @returns {Boolean}
	 */
	function validEmail(email) {
		var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;   
		  
		if(regex.test(email) === false) {  
		    alert("잘못된 이메일 형식입니다.");  
		    return false;  
		} else {  
			return true;
		}  
	}  
	

	
	function deleteImg(userId,photo1) {
		if(confirm("이미지를 삭제하시겠습니까?")) {
			var param = {
					userId	:	userId,
					photo : photo1
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/user/imagine_file_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						//window.location = "jscall://callAddUser?"+firmForm.agentId.value+","+firmForm.userName.value;
						document.location.reload();
					}
				}
			});
		}
		return false;

	}
	
	function deleteUser(userId, userName) {

		if(confirm("가상 회원을 삭제합니다. 삭제된 내용은 복구될 수 없습니다. 삭제를 진행하시겠습니까?")) {
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
						window.location = "jscall://callAddUser?"+userId+","+userName;
						document.location.href = "/pc/chat_user.go?agentId="+firmForm.agentId.value;
					}
				}
			});
		}
		return false;
	}

</script>

<style>
table.register th {
   vertical-align: top;
   text-align: right;
   padding:10px;
   font-weight: bold;
}

table.register td {
	padding:10px;
    vertical-align: top;
}	
.tbl-list TD{
	border-bottom: 1px solid #ddd;
	padding: 5px;
}
</style>
</head>
<body>

<section class="main-cover main-row" style="width:800px;min-width:800px;">

	<section id="main">
		
		<section id="contents">
		
			<form method="post" name="firmForm"  id="firmForm" enctype="multipart/form-data" action="/pc/user/user_imagine_edit_do.go">
			<!-- <form method="post" name="firmForm"  enctype="multipart/form-data" action="/admin/user/user_imagine_edit_do.go" onsubmit="return submitForm(this); return false;"> -->
			<input type="hidden" name="userSeq" value="${userSeq}">
			<input type="hidden" name="editFrom" value="agent">

				<div class="contents-block">
				
					<h1>가상회원 상세보기</h1>
				
					<table class="register">
					<colgroup>
						<col width="150">
						<col width="*">
					</colgroup>
					<tr>
						<th><div class="icon">채팅관리자</div></th>
						<td>
							${user.agentId}
							<input type="hidden" name="agentId" value="${user.agentId}">
						</td> 
					</tr>
					<tr>
						<th><div class="icon">프로필 사진 등록</div></th>
						<td>
							<c:choose>
								  <c:when test="${user.photo == null || user.photo == ''}">
								  		<input type="file" name="photo">(폭 700px의 이미지를 업로드해주세요.)
								  </c:when>	
								  <c:otherwise>
								  		<div class="photo-list" style="background-image:url('${user.photo}')"></div>
										<button type="button" class="btn tiny" onclick="deleteImg('${user.userId}','${user.photo}');">삭제</button>	
								  </c:otherwise>
							</c:choose>
						</td>
					</tr>	
					<tr>
						<th><div class="icon">가상회원 이름</div></th>
						<td><input type="text" class="itext" name="userName" value="${user.userName}"></td> 
					</tr>
					<tr>
						<th>가상회원 성별</th>
						<td>										
							<label style="margin-right:10px;"><input type="radio" name="gender" value="1" ${user.gender == 1 ? 'checked=\"checked\"' : ''}>남자</label>
							<label style="margin-right:10px;"><input type="radio" name="gender" value="2" ${user.gender == 2 ? 'checked=\"checked\"' : ''}>여자</label>
						</td>
					</tr>
					<tr>
						<th>가상회원 생년</th>
						<td>										
							<input type="text" class="itext" name="birthYear"  value="${user.birthYear}"> (나이는 생년으로 입력해주세요. ex:1990)
						</td>
					</tr>
					<tr>
						<th>가상회원 휴대폰 번호</th>
						<td>										
							<input type="text" class="itext" name="phoneNumber"  value="${user.phoneNumber}">
						</td>
					</tr>
					<tr>
						<th><div class="icon">가상회원 ID</div></th>
					 	<c:choose>
							<c:when test="${userSeq == 0}"> 
								<td><input type="text" class="itext" name="userId"> 아이디는 이메일 형식으로만 가능합니다.</td>
							 </c:when>
						 	<c:otherwise>
						 		<input type="hidden" name="userId" value="${user.userId}">
								<td>${user.userId}</td>
							</c:otherwise> 
					 	</c:choose> 
					</tr>
					<tr>
						<th><div class="icon">가상회원 비밀번호</div></th>
						<c:choose>
							<c:when test="${userSeq == 0}">
								<td><input type="password" class="itext" name="password"></td>
							</c:when>
							<c:otherwise>
								<input type="hidden" class="itext" name="password"  value="${user.password}">
								<td>비밀번호는 변경하실 수 없습니다.</td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<th>가상회원 소개글</th>
						<td>										
							<input type="text" class="itext" name="comment"  style="width:500px;" value="${user.comment}">
						</td>
					</tr>
					<c:if test="${userSeq > 0}">
						<tr>
							<th><div class="icon">Point</div></th>
							<td><input type="text" class="itext" name="point" value="${user.point}"></td>
						</tr>
					</c:if>
					<c:if test="${userSeq > 0}">
						<tr>
							<th><div class="icon">F-MONEY</div></th>
							<td><input type="text" class="itext" name="fmoney" value="${user.income}"></td>
						</tr>
					</c:if>
					
						<tr>
							<th><div class="icon">레벨</div></th>
							<td><input type="text" class="itext" name="userLevel" value="${user.userLevel}">Level</td>
						</tr>
				
					
					<tr>
						<th><div class="icon">운영사이트</div></th>
						<td>
							<select name="site" style="width:200px;" class="select-search">
								<option value="0">=운영사이트=</option>
								<c:forEach items="${site}" var="it" >
									<option value="${it.siteSeq}" ${it.siteSeq == user.site ? 'selected=\"selected\"' : ''}>${it.siteName}</option>								
								</c:forEach>		
							</select>
						</td>
					</tr>
					<tr>
						<th><div class="icon">지역 설정</div></th>
						<td>
							<select name="areaSido" style="width:105px;" class="select-search">
								<option value="">=지역=</option>
								<c:forEach items="${location}" var="it" >
									<option value="${it.areaSido}" ${user.area ==it.areaSido? 'selected=\"selected\"' : ''}>${it.areaSido}</option>								
								</c:forEach>		
							</select>
						</td>
					</tr>
					<tr>
							<th><div class="icon"> 위치설정</div></th>
							<td>
								위도<input type="text" class="itext" name="latitude"  value="${user.latitude}" style="margin-left:10px;">
								경도<input type="text" class="itext" name="longitude"  value="${user.longitude}" style="margin-left:10px;">
								
								<div id='map' style="width:580px; height:400px; margin-top:10px;"><br></div>
								<div class="map-info">
									지도에서 원하는 위치를 클릭한 후 핀을 클릭하면 위도와 경도를 확인할 수 있습니다.
								</div>
							</td>
						</tr>
					</table>

				</div>

				<div  class="btn-tools" >
					<c:choose>
						<c:when test="${userSeq == 0}">	
							<button type="submit" class="btn-blue" >등록</button>
						</c:when>
						<c:otherwise>
							<button type="submit" class="btn-blue" >수정</button>
							<button type="button" class="btn-red" onclick="deleteUser('${user.userId}','${user.userName}');">삭제</button>
						</c:otherwise>
					</c:choose>
					<button type="button" class="btn" onclick="javascript:history.back(-1);">목록</button>
				</div>

			</form>	
			
		</section>
	</section>
</section>


<script>

function getLatLng(place) {
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({
		address: place,
		region: 'ko'
	}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			var bounds = new google.maps.LatLngBounds();
			for (var r in results) {
				if (results[r].geometry) {
					var latlng = results[r].geometry.location;
					firmForm.latitude.value = latlng.lat();
					firmForm.longitude.value = latlng.lng();
					setMap(); // bestist/getLatLng.js
					/*
					bounds.extend(latlng);
					
					var address = results[0].formatted_address.replace(/^대한민국, /, '');
					new google.maps.InfoWindow({
						content: address + "<br>(Lat, Lng) = " + latlng.toString()
					}).open(map, new google.maps.Marker({
						position: latlng,
						map: map
					}));
					*/
				}
			}
			//map.fitBounds(bounds);
		} else if (status == google.maps.GeocoderStatus.ERROR) {
			alert("서버 통신에러！");
		} else if (status == google.maps.GeocoderStatus.INVALID_REQUEST) {
			alert("리퀘스트에 문제발생！geocode()에 전달하는GeocoderRequest확인요！");
		} else if (status == google.maps.GeocoderStatus.OVER_QUERY_LIMIT) {
			alert("단시간에 쿼리 과다송신！");
		} else if (status == google.maps.GeocoderStatus.REQUEST_DENIED) {
			alert("이 페이지에서는 지오코더 이용불가! 왜?");
		} else if (status == google.maps.GeocoderStatus.UNKNOWN_ERROR) {
			alert("서버에 문제 발생한거 같아요.다시 한번 해보세요.");
		} else if (status == google.maps.GeocoderStatus.ZERO_RESULTS) {
			alert("위치를 찾을 수 없습니다.");
		} else {
			alert("알 수 없는 오류입니다.");
		}
	});
}
</script>

</body>
</html>