<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header5.jsp"  %>


	<script type="text/javascript">

	$(document).ready(function() {
		$("#menu13").css("font-weight","bold");
		$("#menu13").css("color","#04f");
	});


	</script>

	<style>
	body { background-color:#eee; color:#555; }
	header { padding:10px; font-weight:bold; font-size:12px; }
	header img { height:16px; vertical-align:bottom; }
	.top-margin { height:36px; }
	article { padding: 5px 15px 30px; }
	table { border-collapse: collapse; margin: 0; padding: 0; border: 0; border-spacing: 0; width:100%; }
	table thead { font-weight: bold; margin-left: 10px; border-top: 1px solid #ddd; border-bottom: 1px solid #ddd; }
	table thead th { padding: 4px; border-left: 1px solid #ddd; background-color:#ffca00; font-weight:bold; }
	table thead th:first-child { border-left: 0; background-color:#666666; color:#fff; font-weight:bold; }
	table thead th.dark { background-color:#444444; }
	table tbody tr { background-color: #f1f1f1; }
	table tbody td { text-align: center; /* width: 100px; */ padding: 3px; border-bottom: 1px solid #ddd; background-color:#ffca00; }
	table tbody td:first-child { background-color:#fff; }
	table tbody tr.blank td { background-color:#eee; height:1px; font-size:2px; padding:0px; }
	ul { margin:20px 0; }
	li { list-style:circle; margin:0 0 10px 20px; }
	</style>

</head>
<body>

<header>
	<img src="/images/focustalk_black.png"> 의 레벨 상승에 따른 필요 경험치
</header>


<article>

<table>
<colgroup>
	<col width="50%">
	<col width="50%">
</colgroup>
<thead>
	<tr>
		<th>레벨</th>
		<th>필요경험치</th>
	</tr>
</thead>
<tbody>
	<tr class="blank"><td colspan="2"></td></tr>
	<tr>
		<td>-3</td>
		<td>-2001 ~ -3,000M</td>
	</tr>
	<tr>
		<td>-2</td>
		<td>-1001 ~ -2,000M</td>
	</tr>
	<tr>
		<td>-1</td>
		<td>0 ~ -1,000M</td>
	</tr>
	<tr>
		<td>0</td>
		<td>0M</td>
	</tr>
	<tr>
		<td>1 - 5</td>
		<td>1렙당 500M</td>
	</tr>
	<tr>
		<td>6 - 10</td>
		<td>1렙당 1,000M</td>
	</tr>
	<tr>
		<td>11 - 15</td>
		<td>1렙당 1,500M</td>
	</tr>
	<tr>
		<td>16 - 20</td>
		<td>1렙당 2,000M</td>
	</tr>
	<tr>
		<td>21 - 25</td>
		<td>1렙당 2,500M</td>
	</tr>
	<tr>
		<td>26 - 30</td>
		<td>1렙당 3,000M</td>
	</tr>
	<tr>
		<td>31 - 35</td>
		<td>1렙당 3,500M</td>
	</tr>
	<tr>
		<td>36 - 40</td>
		<td>1렙당 4,000M</td>
	</tr>
	<tr>
		<td>41 - 45</td>
		<td>1렙당 4,500M</td>
	</tr>
	<tr>
		<td>46 - 50</td>
		<td>1렙당 5,000M</td>
	</tr>
</tbody>
</table>

</article>

<header>
	<img src="/images/focustalk_black.png"> 의 레벨 상승에 따른 보상
</header>


<article>

<table>
<colgroup>
	<col width="50%">
	<col width="50%">
</colgroup>
<thead>
	<tr>
		<th colspan="2" class="dark">보상</th>
	</tr>
</thead>
<thead>
	<tr>
		<th>도달레벨</th>
		<th>지급 머니(M)</th>
	</tr>
</thead>
<tbody>
	<tr class="blank"><td colspan="2"></td></tr>
	<tr>
		<td>5 레벨도달</td>
		<td>500M</td>
	</tr>
	<tr>
		<td>10 레벨도달</td>
		<td>1,000M</td>
	</tr>
	<tr>
		<td>15 레벨도달</td>
		<td>1,500M</td>
	</tr>
	<tr>
		<td>20 레벨도달</td>
		<td>2,000M</td>
	</tr>
	<tr>
		<td>25 레벨도달</td>
		<td>2,500M</td>
	</tr>
	<tr>
		<td>30 레벨도달</td>
		<td>3,000M</td>
	</tr>
	<tr>
		<td>35 레벨도달</td>
		<td>3,500M</td>
	</tr>
	<tr>
		<td>40 레벨도달</td>
		<td>4,000M</td>
	</tr>
	<tr>
		<td>45 레벨도달</td>
		<td>4,500M</td>
	</tr>
	<tr>
		<td>50 레벨도달</td>
		<td>5,000M</td>
	</tr>
</tbody>
</table>

<ul>
	<li>레벨에 적용되는 F머니(M)는 어플내 활동으로 획득하는 F머니로만 적용되며 선물 받은 F머니는 경험치에 미 산입 됩니다.</li>
	<li>사용자들의 신고로 레벨이 다운될 수 있습니다.</li>
	<li>레벨 다운 후 재 레벨업 시에 동일한 보상이 추가 지급되지 않습니다.</li>
</ul>


</article>



<header>
	<img src="/images/focustalk_black.png"> 의 경험치 지침 방침
</header>


<article>

<table>
<colgroup>
	<col width="50%">
	<col width="50%">
</colgroup>
<thead>
	<tr>
		<th>레벨업</th>
		<th>지급 머니(M)</th>
	</tr>
</thead>
<tbody>
	<tr class="blank"><td colspan="2"></td></tr>
	<tr>
		<td>토크쓰기</td>
		<td>100M</td>
	</tr>
	<tr>
		<td colspan="2">1일 2회</td>
	</tr>
	<tr class="blank"><td colspan="2"></td></tr>
	<tr>
		<td>댓글쓰기</td>
		<td>20M</td>
	</tr>
	<tr>
		<td colspan="2">1일 10회</td>
	</tr>
	<tr class="blank"><td colspan="2"></td></tr>
	<tr>
		<td>출석체크</td>
		<td>100M</td>
	</tr>
	<tr>
		<td colspan="2">1일 1회<br>-7일 연속 시 7일 째 1,000P</td>
	</tr>
	<tr class="blank"><td colspan="2"></td></tr>
	<tr>
		<td>채팅수락(여)</td>
		<td>20M</td>
	</tr>
	<tr>
		<td colspan="2">건 별 1회. 1일 제한 없음<br>신청 수락 후 대화 입력 시 지급</td>
	</tr>
	<tr class="blank"><td colspan="2"></td></tr>
	<tr>
		<td>5 레벨도달</td>
		<td>500M</td>
	</tr>
	<tr>
		<td>10 레벨도달</td>
		<td>1,000M</td>
	</tr>
	<tr>
		<td>15 레벨도달</td>
		<td>1,500M</td>
	</tr>
	<tr>
		<td>20 레벨도달</td>
		<td>2,000M</td>
	</tr>
	<tr>
		<td>25 레벨도달</td>
		<td>2,500M</td>
	</tr>
	<tr>
		<td>30 레벨도달</td>
		<td>3,000M</td>
	</tr>
	<tr>
		<td>35 레벨도달</td>
		<td>3,500M</td>
	</tr>
	<tr>
		<td>40 레벨도달</td>
		<td>4,000M</td>
	</tr>
	<tr>
		<td>45 레벨도달</td>
		<td>4,500M</td>
	</tr>
	<tr>
		<td>50 레벨도달</td>
		<td>5,000M</td>
	</tr>
	<tr>
		<td colspan="2">5~50 레벨 도달 시 최초 1회 머니 지급 함<br>- 레벨 다운 후 재 도달 시 적용되지 않음</td>
	</tr>
	<tr class="blank"><td colspan="2"></td></tr>
	<tr>
		<td>타인의 신고</td>
		<td>-100M</td>
	</tr>
	<tr>
		<td colspan="2">매회 적용</td>
	</tr>
</tbody>
</table>


</article>

</body>
</html>