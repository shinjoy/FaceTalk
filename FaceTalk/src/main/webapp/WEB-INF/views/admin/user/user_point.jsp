<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	
 	$(document).ready(function() {
		aside.setActive(1,1);
	}); 
 
 	var selectorCnt = 1;

 	function ref(frm){
	 		
 			$('.total').empty();

	 		var sum=0;
	 		var sum2=0;
	 		if (selectorCnt==1) {
		 		if (frm.moneyset.value != "") {
		 			var arr = (frm.moneyset.value).split("/");
		 			sum+=parseInt(arr[0]);
		 			sum2+=parseInt(arr[1]);
		 		}
	 		} else {
		 		for (var i=0; i<frm.moneyset.length; i++) {
			 		if (frm.moneyset[i].value != "") {
			 			var arr = (frm.moneyset[i].value).split("/");
			 			sum+=parseInt(arr[0]);
			 			sum2+=parseInt(arr[1]);
			 		}
			 	}
	 		}
	 		
			if(frm.kind.value=="p"){//포인트전환이면
 			
	 			$('.total').append("총포인트 :" + sum + "   환전금액 : " + sum2);
		 		
			}else{ //fmoney
				
				var v = frm.vall.value;
			
				if(v=='p'){
					
					$('.total').append("총 F-MONEY : " + Number(sum).toLocaleString() + "     환전포인트 : " + Number(sum2).toLocaleString());
				}else{
					$('.total').append("총 F-MONEY : " + Number(sum).toLocaleString() + "     환전금액 : " + Number(sum2).toLocaleString());
				}
			}
			
 		
 	}
 	
 	function addch(){
 
 		var div = document.createElement('div');
 		div.innerHTML = document.getElementById('setSee').innerHTML;
 		document.getElementById('addSee').appendChild(div);
 		selectorCnt++;
 	
 	}

 	function remch(obj,frm){
		
 		document.getElementById('addSee').removeChild(obj.parentNode);
 		selectorCnt--;
 		if(selectorCnt!=1){
 			$('.total').empty();
 		}
 		ref(frm);
    }
	
	
 	function chcal(frm){
 		
 		if(frm.kind.value=="p"){//포인트전환이면
 			
	 		var upoint = frm.point.value;//유저 포인트
	 		
	 		var sum=0;
	 		
	 		for (var i=0; i<$(".moneyset").length; i++) {
		 		if ($(".moneyset").eq(i).val()!=""){
		 			sum+=parseInt($(".moneyset").eq(i).val());
		 		}
		 	}
	 		
	 		if(sum > upoint){
	 			alert("회원의 포인트가 적용 포인트보다 작습니다.");
	 			return true;
	 		}
	 		else if(sum == 0 ){
	 			alert("환급 금액을 선택해 주세요.");
	 			return true;
	 		
	 		}else{
	 			
	 			
	 			
	 			
	 			var param ={
	 					point : sum,
	 					upoint : upoint,
	 					userId : frm.userId.value,
	 					kind : frm.kind.value
	 			};
	 			$.ajax({
	 				type:"POST",
	 				url:"/admin/user/user_point_do.go",
	 				dataType:"json",
	 				data : param,
	 				success:function(json){
	 					var t="";
	 					if (json.result) {
	 						 alert("환급처리 되었습니다.");
	 						 document.location.href="/admin/user/user.go"
	 					}
	 				}
	 			});
	 			return false;
	 			
	 		}
 		
 		}else{
 			
 			var ufmoney = frm.fmoney.value; //유저에프머니
 			var sum=0;
 			var sum2=0;
 			
	 		for (var i=0; i<$(".moneyset").length; i++) {
		 		if ($(".moneyset").eq(i).val()!=""){
		 			var arr = $(".moneyset").eq(i).val().split("/");
		 			sum+=parseInt(arr[0]);
		 			sum2+=parseInt(arr[1]);
		 		}
		 	}
 			
 			if(ufmoney<sum){
 				alert("회원의 F-MONEY가 적용 F-MONEY보다 작습니다.");
	 			return true;
			}else if(sum == 0 ){
	 			alert("환급 금액을 선택해 주세요.");
	 			return true;
	 		
	 		}else{
	 			var param ={
	 					fmoney : sum,
	 					point : sum2,
	 					ufmoney : ufmoney,
	 					userId : frm.userId.value,
	 					kind : frm.kind.value,
	 					seckind : frm.vall.value
	 			};
	 			$.ajax({
	 				type:"POST",
	 				url:"/admin/user/user_point_do.go",
	 				dataType:"json",
	 				data : param,
	 				success:function(json){
	 					var t="";
	 					if (json.result) {
	 						 alert("환급처리 되었습니다.");
	 						 document.location.href="/admin/user/user.go"
	 					}
	 				}
	 			});
	 			return false;
	 		}
 			
 		}
 		
 	}
 	
 	function see(v){
 		$(".setSee").empty();
 		$(".addSee").empty();
 		$(".total").empty();
 		
 		$.ajax({
			type:"POST",
			url:"/admin/user/point_v.go",
			dataType:"json",
			success:function(json){
				var t="";
				if (json.result) {
					  t+="<select name=moneyset class=moneyset onchange=ref(this.form)> <option value=''>선택하세요</option>";
						
					 for(i=0;i<json.pointv.length;i++){
						 
						  
							if(v.value=='p'){//포인트로
								t+="<option value='"+json.pointv[i].money+"/"+json.pointv[i].point+"'>"+"F-MONEY:"+json.pointv[i].money;
								t+=" ▶ 환전포인트 :"+json.pointv[i].point+"</option>";
							 }
					 		if(v.value=='w'){//현금으로
					 			 t+="<option value='"+json.pointv[i].money+"/"+json.pointv[i].cash+"'>"+"F-MONEY:"+json.pointv[i].money;
					 			t+=" ▶ 환전금액 :"+json.pointv[i].cash+"</option>";
					 		} 
					}
			 		t+="</select><button type=button class=btn-red onclick=remch(this,this.form)>삭제</button>";
					$(".setSee").append(t);
				}
			}
		});
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

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 회원관리 > 환급처리
			</header>
		
			<div class="contents-block">
			
				<h1>환급 처리</h1>
				
				<form method="post" name="chForm" onsubmit="return chcal(this); return false;">
				<input type="hidden" name="kind" value="${kind}">
				<input type="hidden" name="userId" value="${userId }">

				<div class="contents-main">

					<div class="contents-box">
						
							<table class="register">
								<tr>
									<th><div class="icon">회원아이디</div></th>
									<td>${userId}</td> 
								</tr>
								 <c:choose>
									 <c:when test="${kind=='p' }">
									 	<input type="hidden" name="point" value="${point}">
										<tr>
											<th><div class="icon">회원포인트</div></th>
											<td><fmt:formatNumber>${point}</fmt:formatNumber> </td>
										 		
										</tr>
										<tr>
											<th><div class="icon">포인트 정책</div></th>
											<td>
											 <div id="setSee" >
												<select name="moneyset" class="moneyset" onchange="ref(this.form)">
													<option value="">선택하세요</option>
													<c:forEach items="${pointset}" var="it" >
														<option value="${it.point }/${it.cash}">포인트:${it.point } ▶ 환전금액 : ${it.cash}  </option>
													</c:forEach>
												</select> &nbsp;&nbsp;<button type="button" class="btn-red" onclick="remch(this,this.form)">삭제</button>
											</div>
											<div id="addSee" style="padding-top:4px;">
											</div>
											</td>
										</tr>
									 	<tr>
											<th><div class="icon"></div></th>
											<td>
												
											</td>
										</tr>
										<tr><td align="center" colspan="2"><button type="button" class="btn-blue" onclick="addch()">추가</button></td></tr>
									</c:when>
									<c:otherwise>
										<input type="hidden" name="fmoney" value="${fmoney}">
										<tr>
											<th><div class="icon">회원F-MONEY</div></th>
											<td><fmt:formatNumber>${fmoney}</fmt:formatNumber></td>
										 		
										</tr>
										<tr>
											<th><div class="icon">환전 유형</div></th>
											<td>
												<input type="radio" name="vall" value="p" onchange="see(this)" checked="checked"> 포인트
												<input type="radio" name="vall" value="w" onchange="see(this)"> 현금
											</td>
										</tr>
										<tr>
											<th><div class="icon">F-MONEY 정책</div></th>
											<td>
												<div id="setSee" class="setSee" > 
													 <select name="moneyset" class="moneyset" onchange="ref(this.form)"> 
													 	<option value="">선택하세요</option>
														   <c:forEach items="${pointset }" var="it">	
																<option value="${it.money}/${it.point}">F-MONEY:${it.money} ▶ 환전포인트 :${it.point}</option>
									 					   </c:forEach>
								 					  </select> &nbsp;&nbsp;<button type="button" class="btn-red" onclick="remch(this,this.form)">삭제</button>
								 				</div>
												<div id="addSee" class="addSee" style="padding-top:4px;">
												</div>
								 			</td>
							 			</tr>
										<tr><td align="center" colspan="2"><button type="button" class="btn-blue" onclick="addch()">추가</button></td></tr>
									</c:otherwise>
									
								</c:choose>
								<tr>
									<th><div class="icon"></div></th>
									<td><div class="total"></div></td>
								</tr>	
							</table>
					</div>

					<br><br>
					<div  class="btn-tools" >
						
							<button type="submit" class="btn-blue" >환급처리</button>
							<button type="button" class="btn" onclick="javascript:history.back(-1)">취소</button>
					</div>

				</div>
				</form>	
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
