<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/pc/head.jsp"  %>

	<script type="text/javascript">

		var userId = "";
		$(document).ready(function() {
			userId = util.getPageParam()["userId"];
			searchForm.userId.value = userId;
			chat.getList(searchForm);
		});

	</script>

	<style>

	</style>

</head>
<body>

	<form method="post" name="searchForm" onsubmit="return chat.getList(this); return false;">
	<input type="hidden" name="userId" value="">
		<header class="list-filter">
			<ul>
				<li>
					<select name="age" class="list-age no-border">
						<option value="0">연령</option>
						<option value="10" ${age=='10' ? 'selected=\"selected\"' : ''}>10대</option>
						<option value="20" ${age=='20' ? 'selected=\"selected\"' : ''}>20대</option>
						<option value="30" ${age=='30' ? 'selected=\"selected\"' : ''}>30대</option>
						<option value="40" ${age=='40' ? 'selected=\"selected\"' : ''}>40대</option>
						<option value="50" ${age=='50' ? 'selected=\"selected\"' : ''}>50대 이상</option>
					</select>
				</li>
				<li>
					<select name="gender" class="list-gender no-border">
						<option value="0">성별</option>
						<option value="1" ${gender=='1' ? 'selected=\"selected\"' : ''}>남자</option>
						<option value="2" ${gender=='2' ? 'selected=\"selected\"' : ''}>여자</option>
					</select>
				</li>
				<li>
					<select name="area" class="list-area no-border">
						<option value="">지역</option>
						<option value="서울" ${area=='서울' ? 'selected=\"selected\"' : ''}>서울</option>
						<option value="인천" ${area=='인천' ? 'selected=\"selected\"' : ''}>인천</option>
						<option value="경기" ${area=='경기' ? 'selected=\"selected\"' : ''}>경기</option>
						<option value="강원" ${area=='강원' ? 'selected=\"selected\"' : ''}>강원</option>
						<option value="대구" ${area=='대구' ? 'selected=\"selected\"' : ''}>대구</option>
						<option value="부산" ${area=='부산' ? 'selected=\"selected\"' : ''}>부산</option>
						<option value="울산" ${area=='울산' ? 'selected=\"selected\"' : ''}>울산</option>
						<option value="경남" ${area=='경남' ? 'selected=\"selected\"' : ''}>경남</option>
						<option value="경북" ${area=='경북' ? 'selected=\"selected\"' : ''}>경북</option>
						<option value="전남" ${area=='전남' ? 'selected=\"selected\"' : ''}>전남</option>
						<option value="전북" ${area=='전북' ? 'selected=\"selected\"' : ''}>전북</option>
						<option value="광주" ${area=='광주' ? 'selected=\"selected\"' : ''}>광주</option>
						<option value="충남" ${area=='충남' ? 'selected=\"selected\"' : ''}>충남</option>
						<option value="대전" ${area=='대전' ? 'selected=\"selected\"' : ''}>대전</option>
						<option value="제주" ${area=='제주' ? 'selected=\"selected\"' : ''}>제주</option>
					</select>
				</li>
				<li>
					<select name="distance" class="list-distances no-border">
						<option value="0" ${distance=='0' ? 'selected=\"selected\"' : ''}>근거리</option>
						<option value="20" ${distance=='20' ? 'selected=\"selected\"' : ''}>20m</option>
						<option value="300" ${distance=='300' ? 'selected=\"selected\"' : ''}>300m</option>
						<option value="500" ${distance=='500' ? 'selected=\"selected\"' : ''}>500m</option>
						<option value="600" ${distance=='600' ? 'selected=\"selected\"' : ''}>600m</option>
						<option value="800" ${distance=='800' ? 'selected=\"selected\"' : ''}>800m</option>
					</select>
				</li>
			</ul>
		</header>

		<header class="list-search">
			<ul>
				<li class="c1"><input type="text" name="keyword" class="itext" placeholder="검색어 입력"></li>
				<li class="c2"><button type="submit" class="btn">검색</button>
			</ul>
		</header>
	</form>

	<article class="list-chat">
		<ul class="list" id="chat-list">
			<li class="list-cell megaphone">
				<ul class="chat-list-cell">
					<li class="c1">
						<div class="thumb40" style="background-image:url('./images/icon_man.png')"></div>
					</li>
					<li class="c2">
						<h2>채팅방 이름</h2>
						<p>방종류 | 지역 | 연령 | 구분</p>
					</li>
					<li class="c3">
						<p>참가/제한</p>
						<p>대화</p>
					</li>
				</ul>
			</li>
			<li class="list-cell">
				<ul class="chat-list-cell">
					<li class="c1">
						<div class="thumb40" style="background-image:url('./images/icon_man.png')"></div>
					</li>
					<li class="c2">
						<h2>채팅방 이름</h2>
						<p>방종류 | 지역 | 연령 | 구분</p>
					</li>
					<li class="c3">
						<p>참가/제한</p>
						<p>대화</p>
					</li>
				</ul>
			</li>
			<li class="list-cell empty">검색된 채팅방이 없습니다.</li>
		</ul>
	</article>

</body>
</html>