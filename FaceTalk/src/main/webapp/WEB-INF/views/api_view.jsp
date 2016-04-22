<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> -->
<!--[if IE 6]><html lang="ko" class="no-js old ie6"><![endif]-->
<!--[if IE 7]><html lang="ko" class="no-js old ie7"><![endif]-->
<!--[if IE 8]><html lang="ko" class="no-js old ie8"><![endif]-->
<!--[if IE 9]><html lang="ko" class="no-js ie9"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html lang="ko" class="no-js modern"><!--<![endif]-->

 <% request.setCharacterEncoding("utf-8"); %>
<%
 response.setHeader("Cache-Control","no-cache"); 
 response.setHeader("Pragma","no-cache"); 
 response.setDateHeader("Expires",0); 
%> 

<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta http-equiv="Content-Script-Type" content="text/javascript" />
	<meta http-equiv="Content-Style-Type" content="text/css" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>Face Talk</title>
	
	<link rel="stylesheet" type="text/css" href="/css/bb-1.0.2.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-form-1.0.2.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-popup-1.0.1.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-popup-2.0.1.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-tab-1.0.1.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-table-1.0.2.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-admin-1.0.3.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-calendar-1.0.1.css" />

	<script type="text/javascript" src="/lib/jquery/jquery-2.1.1.js"></script>
	<script type="text/javascript" src="/lib/jquery/jquery.form.js"></script>


<style>
	body { padding:20px; background-color:#fff; }
	h1 {font-weight:bold;font-size:14px;}
	h2 {font-weight:bold;font-size:14px; margin-bottom:5px; }
	ol.my-form li {
		list-style:decimal;
		font-weight:bold;
		font-size:16px;
		margin-left:30px;
	}
	.div-title { border-bottom:1px solid #000; font-size:30px; font-weight:bold; margin-top:20px; color : red ;}
	.api { min-width:1100px; clear:both; margin-top:20px; }
	.api dt { width:200px; }
	.api .in { float:left; }
	.api .out { float:left; width:600px; }
	.api .in dt { clear:left; float:left; font-size:12px; }
	.api .in dd { float:left; font-size:12px; font-weight:normal; }
	.result-view { border:1px solid #aaa; padding:10px; clear:both; word-break:break-all; font-size:12px; font-weight:normal; line-height:20px; }
	.real { color:#f40; min-height:26px; }
	input { width:264px !important; }
</style>

<script>

	$(document).ready(function() {
		/* 폼 ajax전송 : http://malsup.com/jquery/form/#ajaxForm */
		var options = {
			//target :		'#user-join-result',
			beforeSubmit :	formCheck,
			success :		formSuccess
		};
		$('#login').ajaxForm(options);
		$('#find_id').ajaxForm(options);
		$('#find_pw').ajaxForm(options);
		$('#profile_photo_add').ajaxForm(options);
		$('#profile_photo_delete').ajaxForm(options);
		$('#dup_check_id').ajaxForm(options);
		$('#dup_check_nick').ajaxForm(options);
		$('#dup_check_phone').ajaxForm(options);
		$('#join').ajaxForm(options);
		$('#photo_upload').ajaxForm(options);
		$('#user_bbs_list').ajaxForm(options);
		$('#user_album_list').ajaxForm(options);
		$('#user_guestbook_list').ajaxForm(options);
		$('#user_guestbook_edit').ajaxForm(options);
		$('#user_guestbook_delete').ajaxForm(options);
		$('#friend_list').ajaxForm(options);
		$('#user_list').ajaxForm(options);
		$('#request_friend').ajaxForm(options);
		$('#request_chat').ajaxForm(options);
		$('#chat_start').ajaxForm(options);
		$('#request_friend_answer').ajaxForm(options);
		$('#request_chat_answer').ajaxForm(options);
		$('#bbs_list').ajaxForm(options);
		$('#bbs_view').ajaxForm(options);
		$('#bbs_list_my').ajaxForm(options);
		$('#bbs_comment_list').ajaxForm(options);
		$('#bbs_edit_do').ajaxForm(options);
		$('#bbs_delete_do').ajaxForm(options);
		$('#bbs_comment_edit').ajaxForm(options);
		$('#bbs_recommend').ajaxForm(options);
		$('#bbs_report').ajaxForm(options);
		$('#bbs_share').ajaxForm(options);
		$('#bbs_share_delete').ajaxForm(options);
		$('#user_profile').ajaxForm(options);
		$('#my_bbs_flist').ajaxForm(options);
		$('#sido').ajaxForm(options);
		$('#category').ajaxForm(options);
		$('#search-key').ajaxForm(options);
		$('#change_pw').ajaxForm(options);
		$('#search_mem').ajaxForm(options);
		$('#user_profile_edit').ajaxForm(options);
		$('#user_fdelete').ajaxForm(options);
		$('#user_block').ajaxForm(options);
		$('#user_rblock').ajaxForm(options);
		$('#album_list').ajaxForm(options);
		$('#bbs_comment_reply_list').ajaxForm(options);
		$('#drop_user').ajaxForm(options); 
		$('#faceTalk_send_sms').ajaxForm(options);
		$('#giftpoint').ajaxForm(options);
		$('#changepoint').ajaxForm(options);
		$('#bbs_Comment_delete_do').ajaxForm(options);
		$('#bbs_Comment_reply_delete_do').ajaxForm(options);
		$('#distance').ajaxForm(options);
		$('#logout').ajaxForm(options);
		$('#level_info').ajaxForm(options);
		$('#charge_list').ajaxForm(options);
		$('#readygiftpoint').ajaxForm(options);
		$('#charge_into').ajaxForm(options);
		$('#message_list').ajaxForm(options);
		$('#message_visited').ajaxForm(options);
		$('#message_visited_all').ajaxForm(options);
		$('#drop_user').ajaxForm(options);
		$('#popup_notice').ajaxForm(options);
		$('#version').ajaxForm(options);
		$('#message_noread').ajaxForm(options);
		$('#my_paylist').ajaxForm(options);
		$('#changeList').ajaxForm(options); 
		$('#free_point').ajaxForm(options); 
		$('#push_test').ajaxForm(options); 
		$('#my_changeList').ajaxForm(options); 
		$('#my_chat_list').ajaxForm(options);
		$('#buy_item').ajaxForm(options);
		$('#buy_item_list').ajaxForm(options);
		$('#buy_myitem_list').ajaxForm(options);
		$('#buy_chatitem_list').ajaxForm(options);
		$('#chat_list').ajaxForm(options);
		$('#chat_add').ajaxForm(options);
		$('#block_user').ajaxForm(options);
		$('#message_delete').ajaxForm(options);
		$('#bbs_comment_view').ajaxForm(options);
		$('#point_value').ajaxForm(options);
		$('#user_talk_list').ajaxForm(options);
		$('#chat_join').ajaxForm(options);
		$('#chat_out').ajaxForm(options); 
		$('#chat_memberList').ajaxForm(options); 
		$('#chat_fnc').ajaxForm(options); 
		$('#chat_push').ajaxForm(options); 
		$('#chat_room_info').ajaxForm(options);
		$('#chat_room_mega').ajaxForm(options);
		$('#first_bbs').ajaxForm(options);
		$('#message_chk').ajaxForm(options);
		$('#inApp_insert').ajaxForm(options);
		$('#qna_view').ajaxForm(options);
		$('#qna_edit_do').ajaxForm(options);
		$('#qna_delete').ajaxForm(options);
		
	});
	
	function formCheck(formData, jqForm, options) {
		$("#"+resultDiv+"-result").html("");
		return true; 
	}
	function formSuccess(responseText, statusText, xhr, $form) {
		//alert('status: ' + statusText + '\n\nresponseText: \n' + responseText );
		var json = JSON.parse(responseText);
		try {
			$("#"+resultDiv+"-result").html(responseText);
		} catch (e) {
            alert(json.message); 
		}
	}

	var resultDiv;
	function formSubmit(div) {
		resultDiv = div;
	}

</script>

</head>
<body>

<h1 class="ad_title">FaceTalk Server API</h1>

<div class="div-title">
	회원관리
</div>

<ol class="my-form">
	
	
	<li class="api">
		<c:set var="url" value="login"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>모바일 로그인</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>password</dt><dd><input type="text" name="password" placeholder="password" class="bb"></input></dd>
				<dt>pushKey</dt><dd><input type="text" name="pushKey" placeholder="pushKey" class="bb"></input></dd>
				<dt>latitude</dt><dd><input type="text" name="latitude" placeholder="latitude" class="bb"></input></dd>
				<dt>longitude</dt><dd><input type="text" name="longitude" placeholder="longitude" class="bb"></input></dd>
				<dt>deviceType</dt><dd><input type="text" name="deviceType" placeholder="0:모바일 1:PC" class="bb"></input></dd>
				<dt>deviceId</dt><dd><input type="text" name="deviceId" placeholder="deviceId" class="bb"></input></dd>
				<dt>osType</dt><dd><input type="text" name="osType" placeholder="osType" class="bb"></input></dd>
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"로그인이 성공되었습니다.","result":true,"area":"인천","userName":"최승규","user":{"address":"","appVersion":"","area":"인천","birthYear":1973,"comment":"","count":0,"deviceId":"fq3rwr","deviceName":"","email":"","gender":1,"genderText":"남자","income":0,"intro":"","lastLogindate":"1900-01-01 00:00:00.0","latitude":37.2549,"levelPoint":0,"loginFacebook":0,"loginKakao":0,"longitude":126.0,"nickName":"","osType":"OS","osVersion":"4.1.1","password":"i7DPbrmxfQ99IrRW8SElfcElTh8BZlNwR2OD6ndt9BQ\u003d","phoneNumber":"01045951524","photo":"/files/profile/201509/aa47dde63331b4015e629b7788ff6e9a.jpg","photoRegDate":"","point":0,"pushkey":"q234q3afe","regDate":"2015-09-17 22:47:54.53","status":1,"usePushservice":1,"userAge":42,"userId":"bestist@naver.com","userLevel":0,"userName":"최승규","userType":4,"userTypeText":""},"photo":"/files/profile/201509/aa47dde63331b4015e629b7788ff6e9a.jpg","userType":4}<br>
					{"message":"해당 ID가 존재하지 않거나 ID가 일치하지 않습니다.","result":false,"userType":0}<br>
					{"message":"패스워드가 일치하지 않습니다.","result":false,"userType":0}<br>
					{"message":"탈퇴한 사용자입니다.","result":false}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="find_id"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>아이디 찾기</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>phoneNumber</dt><dd><input type="text" name="phoneNumber" placeholder="phoneNumber" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result":true,"userId":"admin"}<br>
					{"message":"존재하지 않는 ID 입니다.","result":false}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	
	<li class="api">
		<c:set var="url" value="find_pw"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>비밀번호 찾기</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>phone</dt>	<dd><input type="text" name="phone" placeholder="phone" class="bb"></input></dd>
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result":true,"password":"admin"}<br>
					{"message":"존재하지 않는 ID 입니다.","result":false}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	
<%-- 
		<c:set var="url" value="profile_photo_add"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go"  enctype="multipart/form-data">
			<h1>프로필 이미지 등록</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>photo</dt>	<dd><input type="file" name="photo" placeholder="photo" class="bb"></input></dd>
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"사진이 등록되었습니다.","result":true,"userId":"test"}<br>
					{"message":"존재하지 않는 ID 입니다.","result":false}<br>
					{"message":"사진 등록에 실패하였습니다.","result":false}  
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li> --%>
	
	<li class="api">
		<c:set var="url" value="profile_photo_delete"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>프로필 이미지 삭제</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"프로필 이미지가 삭제되었습니다.","result":true}<br>
					{"message":"존재하지 않는 ID 입니다.","result":false}<br>
					{"message":"파일삭제실패.","result":false}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="dup_check_id"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>아이디 중복 체크</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"사용가능한 아이디 입니다.","result":true}<br>
					{"message":"사용중인 아이디 입니다.","result":false}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="dup_check_nick"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>닉네임 중복 체크</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userName</dt><dd><input type="text" name="userName" placeholder="userName" class="bb"></input></dd>
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"사용가능한 닉네임 입니다.","result":true}<br>
					{"message":"사용중인 닉네임 입니다.","result":false}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="dup_check_phone"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>전화번호 중복 체크</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>phoneNumber</dt><dd><input type="text" name="phoneNumber" placeholder="phoneNumber" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"등록가능한 전화번호 입니다.","result":true}<br>
					{"message":"이미 등록된 전화번호 입니다.","result":false}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
<%-- 		<li class="api">
		<c:set var="url" value="n"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>비밀번호 재설정</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>phoneNumber</dt><dd><input type="text" name="phoneNumber" placeholder="phoneNumber" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"등록가능한 전화번호 입니다.","result":true}<br>
					{"message":"이미 등록된 전화번호 입니다.","result":false}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li> --%>
	
	<li class="api">
		<c:set var="url" value="join"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>회원가입</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>		<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>userName</dt>	<dd><input type="text" name="userName" placeholder="userName" class="bb"></input></dd>
				<dt>password</dt>	<dd><input type="password" name="password" placeholder="password" class="bb"></input></dd>
				<dt>phoneNumber</dt>	<dd><input type="text" name="phoneNumber" placeholder="phoneNumber" class="bb"></input></dd>
				<dt>userAge</dt>	<dd><input type="text" name="userAge" placeholder="userAge" class="bb"></input></dd>
				<dt>gender</dt>		<dd><input type="text" name="gender" placeholder="gender" class="bb"></input></dd>
				<dt>area</dt>		<dd><input type="text" name="area" placeholder="area" class="bb"></input></dd>
				<dt>photo</dt>		<dd><input type="text" name="photo" placeholder="photo" class="bb"></input></dd>
				<dt>latitude</dt>	<dd><input type="text" name="latitude" placeholder="latitude" class="bb"></input></dd>
				<dt>longitude</dt>	<dd><input type="text" name="longitude" placeholder="longitude" class="bb"></input></dd>
				<dt>osType</dt>		<dd><input type="text" name="osType" placeholder="osType" class="bb"></input></dd>
				<dt>osVersion</dt>	<dd><input type="text" name="osVersion" placeholder="osVersion" class="bb"></input></dd>
				<dt>deviceId</dt>	<dd><input type="text" name="deviceId" placeholder="deviceId" class="bb"></input></dd>
				<dt>pushKey</dt>	<dd><input type="text" name="pushKey" placeholder="pushKey" class="bb"></input></dd>
				<dt>&nbsp;</dt>		<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"회원가입되었습니다.","result":true}<br>
					{"message":"e.getMessage().","result":false}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="user_profile"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>회원 프로필</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>dId</dt><dd><input type="text" name="dId" placeholder="타인:타인아이디 본인: 본인아이디" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				  
					 {타인 프로필 볼때}("status", status) 친구인지 아닌지 상태값 친구아님:0,친구:1<br>
					 {동시}("user", user); 뿌려줄 정보<br>
					 {타인 프로필 볼때}map.put("message", "."); 본인 사진이 있으면
					 {타인 프로필 볼때}map.put("message", "본인 프로필 사진을 입력해야 상대방의 프로필 사진을 볼 수있습니다."); 없으면
					{"result":true,"list":[{"area":"서울","bbsSeq":0,"birthYear":1985,"photo":"/files/profile/201509/fi0ad20150910114128885.jpg","gender":1,"genderText":"남자","regDate":"","type":"profile","userAge":30,"userId":"test1@test.com","userName":"테스트1"}]}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		
		<form method="post" id="change_pw" name="change_pw" action="/m/change_pw.go">
			<h1>비밀번호변경 </h1>
			<h2 class="page-title">/m/change_pw.go</h2>
			<dl class="in">
				
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>password</dt><dd><input type="password" name="password" placeholder="password" class="bb"></input></dd>
				<dt>npassword</dt><dd><input type="password" name="npassword" placeholder="npassword" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('change_pw');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				변경완료 or 실패<br>
					
					
				</div>
				<div id="change_pw-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	

	
	<li class="api">
		<c:set var="url" value="user_profile_edit"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>프로필 사진 수정 </h1>
			<h1>(수정안한 항목도 값을 넣어줘서 보내야함) </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			
			    <dt>area</dt><dd><input type="text" name="area" placeholder="area" class="bb"></input></dd>
			    <dt>comment</dt><dd><input type="text" name="comment" placeholder="한줄소개" class="bb"></input></dd>
				<dt>userName</dt><dd><input type="text" name="userName" placeholder="userName" class="bb"></input></dd>
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="로그인한 유저아이디" class="bb"></input></dd>
				<dt>photo</dt><dd><input type="text" name="photo" placeholder="전파일명" class="bb"></input></dd>
				<dt>newphoto</dt><dd><input type="text" name="newphoto" placeholder="새로운 파일명" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					{"result":true,"message":"삭제되었습니다"}<br>
					{"message":"삭제권한이 없습니다","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="drop_user"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>계정탈퇴 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			   
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="로그인한 유저아이디" class="bb"></input></dd>
				<dt>dropReason</dt><dd><input type="text" name="dropReason" placeholder="탈퇴사유" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					{"result":true,"message":"탈퇴되었습니다"}<br>
					{"message":"실패하였습니다","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
<div class="div-title">
	이미지등록
</div>	
	<li class="api">
		<c:set var="url" value="photo_upload"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go" enctype="multipart/form-data">
			<h1>이미지등록</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>photo</dt>		<dd><input type="file" name="photo" placeholder="photo" class="bb"></input></dd>
				<dt>path</dt>		<dd><input type="text" name="path" placeholder="path" class="bb" value="profile"></input></dd>
				<dt>userId</dt>		<dd><input type="text" name="userId" placeholder="userId" class="bb" ></input></dd>
				<dt>&nbsp;</dt>		<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"사진이 등록되었습니다.","result":true,"path":"/files/profile/201509/","photo":"aa47dde63331b4015e629b7788ff6e9a.jpg"} <br>
					{"message":"e.getMessage().","result":false}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
<div class="div-title">
	토크
</div>	
	<li class="api">
		<c:set var="url" value="user_bbs_list"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>회원 토크 목록</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>		<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>&nbsp;</dt>		<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result":true,"list":[{"area":"서울","bbsCategory":"1","bbsContents":"1","bbsContentsText":"1","bbsHeader":"1","bbsSeq":3,"bbsTitle":"","birthYear":1985,"blindCount":1,"commentCount":0,"dislikeCount":0,"extraContents":"","fileCount":0,"files":"","gender":1,"genderText":"남자","likeCount":0,"linkUrl":"","nickName":"","regDate":"2015-09-14 13:15:01.69","reportCount":0,"userAge":30,"userId":"test1@test.com","userName":"테스트1","viewCount":0}]}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="bbs_list"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>토크 목록</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>keyword</dt><dd><input type="text" name="keyword" placeholder="keyword" class="bb"></input></dd>
				<dt>category</dt><dd><input type="text" name="category" placeholder="카테고리 일련번호" class="bb"></input></dd>
				<dt>gender</dt><dd><input type="text" name="gender" placeholder="1:남자 2:여자 0:전체" class="bb"></input></dd>
				<dt>area</dt><dd><input type="text" name="area" placeholder="area" class="bb"></input></dd>
				<dt>kind</dt><dd><input type="text" name="kind" placeholder="kind" class="bb"></input></dd>
				<dt>sort</dt><dd><input type="text" name="sort" placeholder="1:근거리 2:먼거리" class="bb"></input></dd>
				<dt>page</dt><dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
				<dt>latitude</dt><dd><input type="text" name="latitude" placeholder="latitude" class="bb"></input></dd>
				<dt>longitude</dt><dd><input type="text" name="longitude" placeholder="longitude" class="bb"></input></dd>
				<dt>site</dt><dd><input type="text" name="site" placeholder="site" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"조건없을시 전체 sort 근거리 등록일자역순."}<br>
					
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="bbs_view"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>토크 보기</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>bbsSeq</dt><dd><input type="text" name="bbsSeq" placeholder="bbsSeq" class="bb"></input></dd>
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"조건없을시 전체 sort 근거리 등록일자역순."}<br>
					
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="bbs_comment_view"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>토크 보기</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>bbsSeq</dt><dd><input type="text" name="bbsSeq" placeholder="bbsSeq" class="bb"></input></dd>
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"조건없을시 전체 sort 근거리 등록일자역순."}<br>
					
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="bbs_list_my"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>내토크 목록 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>keyword</dt><dd><input type="text" name="keyword" placeholder="keyword" class="bb"></input></dd>
				<dt>page</dt><dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"조건업을시 전체 토크리스트 출력"}<br>
					
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="user_talk_list"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>회원 토크 목록</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>		<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>myId</dt>		<dd><input type="text" name="myId" placeholder="myId" class="bb"></input></dd>
				<dt>&nbsp;</dt>		<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result":true,"list":[{"area":"서울","bbsCategory":"1","bbsContents":"1","bbsContentsText":"1","bbsHeader":"1","bbsSeq":3,"bbsTitle":"","birthYear":1985,"blindCount":1,"commentCount":0,"dislikeCount":0,"extraContents":"","fileCount":0,"files":"","gender":1,"genderText":"남자","likeCount":0,"linkUrl":"","nickName":"","regDate":"2015-09-14 13:15:01.69","reportCount":0,"userAge":30,"userId":"test1@test.com","userName":"테스트1","viewCount":0}]}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
<!-- 	<li class="api">
		
		<form method="post" id="my_bbs_flist" name="my_bbs_flistForm" action="/m/bbs_list_ff.go">
			<h1>친구 토크 목록 </h1>
			<h2 class="page-title">/m/bbs_list_ff.go</h2>
			<dl class="in">
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>keyword</dt><dd><input type="text" name="keyword" placeholder="keyword" class="bb"></input></dd>
				<dt>page</dt><dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('my_bbs_flist');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					map.put("bbsList", bbsList);
					map.put("currentPage", page);
					map.put("itemCount", bbsCount);
					map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
					map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);
					<br>
					
					
				</div>
				<div id="my_bbs_flist-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li> -->
	
	<li class="api">
		<c:set var="url" value="bbs_comment_list"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>토크 댓글 목록</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>bbsSeq</dt><dd><input type="text" name="bbsSeq" placeholder="bbsSeq" class="bb"></input></dd>
				<dt>page</dt><dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="bbs_comment_reply_list"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>토크 댓글에 댓글</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>bbsSeq</dt><dd><input type="text" name="bbsSeq" placeholder="bbsSeq" class="bb"></input></dd>
				<dt>commentSeq</dt><dd><input type="text" name="commentSeq" placeholder="commentSeq" class="bb"></input></dd>
				<dt>page</dt><dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
		
	<li class="api">
		<c:set var="url" value="bbs_edit_do"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>토크 등록/수정 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			    <dt>bbsSeq</dt><dd><input type="text" name="bbsSeq" placeholder="bbsSeq" class="bb"></input></dd>
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>contents</dt><dd><input type="text" name="contents" placeholder="contents" class="bb"></input></dd>
				<dt>photo(array)</dt><dd><input type="text" name="photo" placeholder="photo" class="bb"></input></dd>
				<dt>category</dt><dd><input type="text" name="category" placeholder="category" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					{"message":"게시물이 등록되었습니다.","result":true}<br>
					{"message":"게시물이 수정되었습니다.","result":true}<br>
					{"message":"오류메세지","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>	
	
	<li class="api">
		<c:set var="url" value="bbs_delete_do"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>토크 삭제 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			    <dt>bbsSeq</dt><dd><input type="text" name="bbsSeq" placeholder="bbsSeq" class="bb"></input></dd>
				<dt>talkId</dt><dd><input type="text" name="talkId" placeholder="글의 작성자" class="bb"></input></dd>
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="로그인한 유저아이디" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					{"result":true,"message":"삭제되었습니다"}<br>
					{"message":"삭제권한이 없습니다","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>	
	<li class="api">
		<c:set var="url" value="bbs_Comment_delete_do"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>토크 댓글 삭제 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			    <dt>bbsSeq</dt><dd><input type="text" name="bbsSeq" placeholder="bbsSeq" class="bb"></input></dd>
				<dt>bbsCommentSeq</dt><dd><input type="text" name="bbsCommentSeq" placeholder="bbsCommentSeq" class="bb"></input></dd>
				<dt>pbbsCommentSeq</dt><dd><input type="text" name="pbbsCommentSeq" placeholder="pbbsCommentSeq" class="bb"></input></dd>
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="로그인한 유저아이디" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					{"result":true,"message":"삭제되었습니다"}<br>
					{"message":"삭제권한이 없습니다","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>	
	<%-- <li class="api">
		<c:set var="url" value="bbs_Comment_reply_delete_do"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>토크 댓글의 댓글 삭제 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			    <dt>bbsSeq</dt><dd><input type="text" name="bbsSeq" placeholder="bbsSeq" class="bb"></input></dd>
				<dt>bbsCommentSeq</dt><dd><input type="text" name="bbsCommentSeq" placeholder="bbsCommentSeq" class="bb"></input></dd>
				<dt>pbbsCommentSeq</dt><dd><input type="text" name="pbbsCommentSeq" placeholder="부모글" class="bb"></input></dd>
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="로그인한 유저아이디" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					{"result":true,"message":"삭제되었습니다"}<br>
					{"message":"삭제권한이 없습니다","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>	 --%>
	
	<li class="api">
		<c:set var="url" value="bbs_comment_edit_do"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>토크 댓글등록 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>bbsCommentSeq</dt>		<dd><input type="text" name="bbsCommentSeq" placeholder="-1:등록, 이상:수정" class="bb"></input></dd>
			    <dt>bbsSeq</dt>		<dd><input type="text" name="bbsSeq" placeholder="bbsSeq" class="bb"></input></dd>
				<dt>userId</dt>		<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>bbsContents</dt>	<dd><input type="text" name="bbsContents" placeholder="bbsContents" class="bb"></input></dd>
				<dt>photo</dt>		<dd><input type="text" name="photo" placeholder="photo" class="bb"></input></dd>
				<dt>&nbsp;</dt>		<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					{"result":true,"message":"등록되었습니다"}<br>
					{"message":"오류메세지","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>	
	
	<li class="api">
		<c:set var="url" value="bbs_like"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>토크 추천 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			    <dt>talkSeq</dt><dd><input type="text" name="talkSeq" placeholder="talkSeq" class="bb"></input></dd>
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					{"result":true,"message":"추천하였습니다"}<br>
					{"message":"이미 추천하였습니다","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>	
	
	<li class="api">
		<c:set var="url" value="bbs_report"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>토크 신고</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>talkSeq</dt><dd><input type="text" name="talkSeq" placeholder="본글의 seq 댓글이면 -1" class="bb"></input></dd>
				<dt>bbsSeqComment</dt><dd><input type="text" name="bbsSeqComment" placeholder="댓글이면 해당댓글 seq 본글이면 -1" class="bb"></input></dd>
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>blockId</dt><dd><input type="text" name="blockId" placeholder="blockId" class="bb"></input></dd>
			   	<dt>fncReason</dt><dd><input type="text" name="fncReason" placeholder="1,2,3,4(신고사유)" class="bb"></input></dd>
				<dt>contents</dt><dd><input type="text" name="contents" placeholder="contents" class="bb"></input></dd>
				<dt>photo</dt><dd><input type="text" name="photo" placeholder="photo" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					{"result":true,"message":"토크를 신고하였습니다"}<br>
					{"message":"오류메세지","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="bbs_share"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>토크 공유하기</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>bbsSeq</dt><dd><input type="text" name="bbsSeq" placeholder="공유하려는 글 seq" class="bb"></input></dd>
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="퍼가는 사람 id" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"공유하였습니다.","result":true}<br>
					{"message":"오류메세지","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="bbs_share_delete"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>토크 공유하기 취소</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>bbsSeq</dt><dd><input type="text" name="bbsSeq" placeholder="bbsSeq" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"공유를 취소하였습니다.","result":true}<br>
					{"message":"오류메세지","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
<div class="div-title">
	앨범
</div>	
	

	
	<li class="api">
		<c:set var="url" value="user_album_list"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>회원 앨범 목록</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result":true,"list":[{"area":"서울","bbsSeq":0,"birthYear":1985,"fileName":"/files/profile/201509/fi0ad20150910114128885.jpg","gender":1,"genderText":"남자","regDate":"","type":"profile","userAge":30,"userId":"test1@test.com","userName":"테스트1"}]}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="album_list"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>앨범 더보기 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			   <dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
			   <dt>page</dt><dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
			   <dt>age</dt><dd><input type="text" name="age" placeholder="10:10대 " class="bb"></input></dd>
			   <dt>gender</dt><dd><input type="text" name="gender" placeholder="1:남자 2:여자 0:전체" class="bb"></input></dd>
			   <dt>keyword</dt><dd><input type="text" name="keyword" placeholder="검색어" class="bb"></input></dd> 
			   <dt>sort</dt><dd><input type="text" name="sort" placeholder="1근거리 2 먼거리" class="bb"></input></dd>
			   <dt>area</dt><dd><input type="text" name="area" placeholder="area" class="bb"></input></dd>
			   <dt>latitude</dt><dd><input type="text" name="latitude" placeholder="latitude" class="bb"></input></dd>
			   <dt>longitude</dt><dd><input type="text" name="longitude" placeholder="longitude" class="bb"></input></dd>
			
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					("uphoto", uphoto);//프로필 사진 등록자인지 아닌지 판별값.(미등록자:0,등록자:1)
					("bbsList", bbsList);
					("currentPage", page);
					("itemCount", bbsCount);
					("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
					("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
<div class="div-title">
	방명록
</div>	
	<li class="api">
		<c:set var="url" value="user_guestbook_list"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>회원 방명록 목록</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result":true,"list":[{"bookSeq":1,"contents":"처음 쓰는 방명록","guestId":"test1@test.com","regDate":"2015-09-18 00:18:49.853","userId":"bestist@naver.com"}]}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="user_guestbook_edit"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>회원 방명록 등록</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>bookSeq</dt><dd><input type="text" name="bookSeq" placeholder="등록은 0" class="bb"></input></dd>
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>guestId</dt><dd><input type="text" name="guestId" placeholder="guestId" class="bb"></input></dd>
				<dt>contents</dt><dd><input type="text" name="contents" placeholder="내용" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"방명록이 등록되었습니다.","result":true}<br>
					{"message":"방명록이 수정되었습니다.","result":true}<br>
					{"message":"본인만 수정할 수 있습니다.","result":false}<br>
					{"message":"오류메세지.","result":false}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="user_guestbook_delete"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>회원 방명록 삭제</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>bookSeq</dt><dd><input type="text" name="bookSeq" placeholder="bookSeq" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"방명록이 삭제되었습니다.","result":true}<br>
					{"message":"오류메세지.","result":false}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
     <!-- 시작 -->
<div class="div-title">
	멤버
</div>
		<li class="api">
		<c:set var="url" value="friend_list"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>내친구 목록</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>keyword</dt><dd><input type="text" name="keyword" placeholder="keyword" class="bb"></input></dd>
				<dt>page</dt><dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result":true,"message":"친구 list 출력" , "count": count}<br>
					{"message":"존재하지않는 ID 입니다","result":false}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>

	
	<li class="api">
		<c:set var="url" value="user_list"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>전체회원</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>page</dt>			<dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
				<dt>category</dt>		<dd><input type="text" name="category" placeholder="category" class="bb"></input></dd>
				<dt>gender</dt>			<dd><input type="text" name="gender" placeholder="gender" class="bb"></input></dd>
				<dt>area</dt>			<dd><input type="text" name="area" placeholder="area" class="bb"></input></dd>
				<dt>sort</dt>			<dd><input type="text" name="sort" placeholder="sort" class="bb"></input></dd>
				<dt>keyword</dt>		<dd><input type="text" name="keyword" placeholder="keyword" class="bb"></input></dd>
				<dt>latitude</dt>		<dd><input type="text" name="latitude" placeholder="latitude" class="bb"></input></dd>
				<dt>longitude</dt>		<dd><input type="text" name="longitude" placeholder="longitude" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result":true,"count":6,"list":[{"address":"","appVersion":"","area":"서울","birthYear":1985,"deviceId":"ffffffff-be9b-4376-ffff-ffffc51a0995","deviceName":"","email":"","gender":1,"genderText":"남자","income":0,"intro":"","lastLogindate":"1900-01-01 00:00:00.0","latitude":37.0,"levelPoint":0,"loginFacebook":"0","loginKakao":"0","longitude":126.0,"nickName":"","osType":"ANDROID","osVersion":"21","password":"luvsNg0HCo2M8mcjTpUMEg\u003d\u003d","phoneNumber":"01033333333","photo":"","photoRegDate":"","point":0,"pushkey":"","regDate":"1900-01-01 00:00:00.0","status":1,"usePushservice":"0","userAge":30,"userId":"test4@test.com","userLevel":0,"userName":"","userType":"4","userTypeText":"최고관리자"}]}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		
		<form method="post" id="search_mem" name="search_mem" action="/m/search_mem.go">
			<h1>멤버검색 </h1>
			<h2 class="page-title">/m/search_mem.go</h2>
			<dl class="in">
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>kind</dt><dd><input type="text" name="kind" placeholder="kind" class="bb"></input></dd>
				<dt>page</dt><dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
				<dt>category</dt><dd><input type="text" name="category" placeholder="0전체 1인기도,2레벨" class="bb"></input></dd>
				<dt>age</dt><dd><input type="text" name="age" placeholder="10:10대 " class="bb"></input></dd>
				<dt>gender</dt><dd><input type="text" name="gender" placeholder="1:남자 2:여자 0:전체" class="bb"></input></dd>
				<dt>keyword</dt><dd><input type="text" name="keyword" placeholder="검색어" class="bb"></input></dd> 
				<dt>sort</dt><dd><input type="text" name="sort" placeholder="1근거리 2 먼거리" class="bb"></input></dd>
				<dt>area</dt><dd><input type="text" name="area" placeholder="area" class="bb"></input></dd>
				<dt>latitude</dt><dd><input type="text" name="latitude" placeholder="latitude" class="bb"></input></dd>
				<dt>longitude</dt><dd><input type="text" name="longitude" placeholder="longitude" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('search_mem');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				변경완료 or 실패<br>
					
					
				</div>
				<div id="search_mem-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
<div class="div-title">
	친구
</div>	
	<li class="api">
		<c:set var="url" value="request_friend"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>친구신청</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>friendId</dt><dd><input type="text" name="friendId" placeholder="friendId" class="bb"></input></dd>
				<dt>message</dt><dd><input type="text" name="message" placeholder="message" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result":true,"message":"친구 요청 되었습니다" , "seq": seq}<br>
					{"message":"존재하지 않는 회원 입니다.","result":false}<br>
					{"message":"존재하지 않는 친구 입니다.","result":false}<br>
					{"message":"이미 친구 요청을 하였으며 응답을 기다리는 중입니다.","result":false}<br>
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="request_friend_answer"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>친구 신청 수락</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>seq</dt><dd><input type="text" name="seq" placeholder="seq" class="bb"></input></dd>
				<dt>answer</dt><dd><input type="text" name="answer" placeholder="1:수락, 0또는2:거절" class="bb"></input></dd>
				
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"친구 신청이 수락 되었습니다.","result":true}<br>
					{"message":"친구 신청을 거절했습니다.","result":false}<br>
					{"message":"이미 친구 수락을 했습니다.","result":false}
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
		<li class="api">
		<c:set var="url" value="user_fdelete"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>친구삭제 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			   
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="로그인한 유저아이디" class="bb"></input></dd>
				<dt>friendId</dt><dd><input type="text" name="friendId" placeholder="friendId" class="bb"></input></dd>
			
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					{"result":true,"message":"삭제되었습니다"}<br>
					{"message":"실패하였습니다","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
<div class="div-title">
	차단
</div>	
	<li class="api">
		<c:set var="url" value="block_user"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>차단하기 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			   
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="로그인한 유저아이디" class="bb"></input></dd>
				<dt>blockId</dt><dd><input type="text" name="blockId" placeholder="차단할 아이디" class="bb"></input></dd>
				<dt>page</dt><dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
			
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					{"result":true,"message":"삭제되었습니다"}<br>
					{"message":"실패하였습니다","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="user_block"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>차단유저리스트 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			   
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="로그인한 유저아이디" class="bb"></input></dd>
				<dt>page</dt><dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
			
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					{"result":true,"message":"삭제되었습니다"}<br>
					{"message":"실패하였습니다","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
		
	<li class="api">
		<c:set var="url" value="user_rblock"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>차단해제 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			   
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="로그인한 유저아이디" class="bb"></input></dd>
				<dt>bId</dt><dd><input type="text" name="bId" placeholder="차단해제 유저아이디" class="bb"></input></dd>
				
			
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					{"result":true,"message":"삭제되었습니다"}<br>
					{"message":"실패하였습니다","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	
	
<div class="div-title">
	대화
</div>	
	<li class="api">
		<c:set var="url" value="request_chat"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>대화요청</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>friendId</dt><dd><input type="text" name="friendId" placeholder="friendId" class="bb"></input></dd>
				<dt>message</dt><dd><input type="text" name="message" placeholder="message" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result":true,"message":"대화 요청되었습니다" , "seq": seq}<br>
					{"message":"존재하지 않는 회원 입니다.","result":false}<br>
					{"message":"존재하지 않는 친구 입니다.","result":false}<br>
					{"message":"이미등록된 친구입니다","result":false}<br>
					{"message":"이미 요청을 거절한 친구입니다","result":false}
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
		<li class="api">
		<c:set var="url" value="chat_start"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>대화요청(친구)</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>friendId</dt><dd><input type="text" name="friendId" placeholder="friendId" class="bb"></input></dd>
				
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result":true,"message":"대화요청되었습니다" , "seq": seq}<br>
					{"message":"존재하지않는 ID 입니다","result":false}<br>
					{"message":"친구사이가 아닙니다.\n먼저 친구신청하거나 대화요청을 해주세요.","result":false}<br>
					
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	

	<li class="api">
		<c:set var="url" value="request_chat_answer"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>대화 요청 수락</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>seq</dt><dd><input type="text" name="seq" placeholder="seq" class="bb"></input></dd>
				<dt>answer</dt><dd><input type="text" name="answer" placeholder="1:수락 0또는2:거절" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result":true,"message":"대화신청을 거절했습니다."}<br>
					{"result":true,"message":"친구 등록 되었습니다."}
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
<div class="div-title">
	리스트
</div>	
	
	<li class="api">
		<c:set var="url" value="sido"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>시도 목록</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					{"result":true,"list":[{"areaSido":"서울","seq":1,"sidoName":"서울특별시","sortId":1},{"areaSido":"인천","seq":2,"sidoName":"인천광역시","sortId":2},{"areaSido":"경기","seq":3,"sidoName":"경기도","sortId":3},{"areaSido":"강원","seq":4,"sidoName":"강원도","sortId":4},{"areaSido":"대구","seq":5,"sidoName":"대구광역시","sortId":5},{"areaSido":"부산","seq":6,"sidoName":"부산광역시","sortId":6},{"areaSido":"울산","seq":7,"sidoName":"울산광역시","sortId":7},{"areaSido":"경남","seq":8,"sidoName":"경상남도","sortId":8},{"areaSido":"경북","seq":9,"sidoName":"경상북도","sortId":9},{"areaSido":"전남","seq":10,"sidoName":"전라남도","sortId":10},{"areaSido":"전북","seq":11,"sidoName":"전라북도","sortId":11},{"areaSido":"광주","seq":12,"sidoName":"광주광역시","sortId":12},{"areaSido":"충남","seq":13,"sidoName":"충청남도","sortId":13},{"areaSido":"충북","seq":14,"sidoName":"충청북도","sortId":14},{"areaSido":"대전","seq":15,"sidoName":"대전광역시","sortId":15},{"areaSido":"제주","seq":16,"sidoName":"제주도특별자치도","sortId":16},{"areaSido":"세종","seq":17,"sidoName":"세종특별자치시","sortId":17}]}<br>
					{"message":"오류메세지","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
		
	
	
	<li class="api">
		<c:set var="url" value="category"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>카테고리(주제) 목록</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result":true,"list":[{"categoryName":"만남","categorySeq":1,"categorySort":1},{"categoryName":"유머","categorySeq":2,"categorySort":2},{"categoryName":"일상","categorySeq":3,"categorySort":3}]}
					{"message":"오류메세지","result":false}<br>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<div class="api">
		<form method="post" id="distance" name="distance" action="/m/distance.go">
			<li>거리목록</li>
			<h1 class="page-title">/m/distance.go</h1>
			<dl class="in">
			
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('distance');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="distance-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
		<div class="api">
		<form method="post" id="point_value" name="point_value" action="/m/point_value.go">
			<li>포인트 값 </li>
			<h1 class="page-title">/m/point_value.go</h1>
			<dl class="in">
			<dt>codeName</dt><dd><input type="text" name="codeName" placeholder="닉네임변경 : NAME . 프사등록 : PHOTO " class="bb"></input></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('point_value');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="point_value-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>

	<!-- <li class="api">
		
		<form method="post" id="search-key" name="search-key" action="/m/search_key.go">
			<h1>검색 </h1>
			<h2 class="page-title">/m/search_key.go</h2>
			<dl class="in">
				
				<dt>keyword</dt><dd><input type="text" name="keyword" placeholder="keyword" class="bb"></input></dd>
				<dt>page</dt><dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
				<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('search-key');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				("bbsList");
				("currentPage");
				("itemCount");
				("ITEM_COUNT_PER_PAGE");
				("PAGE_COUNT_PER_PAGING");<br>
					
					
				</div>
				<div id="search-key-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li> -->



<div class="div-title">
	알림
</div>	

	
	<li class="api">
		<c:set var="url" value="message_list"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>알림내역 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			   <dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
			   <dt>page</dt><dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
			   <dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>

	<li class="api">
		<c:set var="url" value="message_visited"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>알림내역 읽음 처리</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			   <dt>messageSeq</dt><dd><input type="text" name="messageSeq" placeholder="messageSeq" class="bb"></input></dd>
			   <dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>

	<li class="api">
		<c:set var="url" value="message_visited_all"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>알림내역 전체 읽음 처리</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			   <dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
			   <dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="message_noread"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>안읽은 알림내역 갯수 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			   <dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
			  
			   <dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="message_delete"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>알림내역삭제 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			   <dt>userId</dt><dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
			   <dt>messageSeq</dt><dd><input type="text" name="messageSeq" placeholder="messageSeq" class="bb"></input></dd>
			   <dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
<div class="div-title">
	팝업
</div>		
	<li class="api">
		<c:set var="url" value="popup_notice"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>팝업 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			   <dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
		
	<li class="api">
		<c:set var="url" value="adshow"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>광고 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			   <dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
<div class="div-title">
	SMS
</div>	
	<div class="api">
		<form method="post" id="faceTalk_send_sms" name="faceTalk_send_sms" action="/m/faceTalk_send_sms.go">
			<li>페이스톡 SMS 발송</li>
			<h1 class="page-title">/m/faceTalk_send_sms.go</h1>
			<dl class="in">
			<dt>site</dt>	<dd><input type="text" name="site" placeholder="site" class="bb"></dd>
			<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			<dt>msgType</dt>	<dd><input type="text" name="msgType" placeholder="msgType" class="bb"></dd>
				<dt>message</dt>	<dd><input type="text" name="message" placeholder="message" class="bb"></dd>
				<dt>to</dt>	 <dd><input type="text" name="to"  placeholder="수신전화번호" class="bb"></dd>
				<dt>from</dt>	 <dd><input type="text" name="from" value="1544-5808" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('faceTalk_send_sms');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"인증번호가 발송되었습니다.","result":true}
				</div>
				<div id="faceTalk_send_sms-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	
<div class="div-title">
	선물하기
</div>	
	<div class="api">
		<form method="post" id="readygiftpoint" name="readygiftpoint" action="/m/readygiftpoint.go">
			<li>포인트 선물하기전 내포인트 </li>
			<h1 class="page-title">/m/readygiftpoint.go</h1>
			<dl class="in">
			
				<dt>userId</dt>	 <dd><input type="text" name="userId"  placeholder="userId" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('readygiftpoint');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"userpoint":,"result":true}
				</div>
				<div id="readygiftpoint-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="giftpoint" name="giftpoint" action="/m/giftpoint.go">
			<li>포인트 선물하기</li>
			<h1 class="page-title">/m/giftpoint.go</h1>
			<dl class="in">
			
				<dt>userId</dt>	 <dd><input type="text" name="userId"  placeholder="userId" class="bb"></dd>
				<dt>friendId</dt>	 <dd><input type="text" name="friendId" value="friendId" class="bb"></dd>
				<dt>point</dt>	 <dd><input type="text" name="point" value="포인트" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('giftpoint');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"선물완료.","result":true}
				</div>
				<div id="giftpoint-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
<div class="div-title">
	환전
</div>
	<div class="api">
		<form method="post" id="changeList" name="changeList" action="/m/changeList.go">
			<li>환전리스트</li>
			<h1 class="page-title">/m/changeList.go</h1>
			<dl class="in">
			
				<dt>userId</dt>	 <dd><input type="text" name="userId"  placeholder="userId" class="bb"></dd>
				<dt>page</dt>	 <dd><input type="text" name="page" value="page" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('changeList');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="changeList-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="changepoint" name="changepoint" action="/m/changepoint.go">
			<li>포인트 환전신청</li>
			<h1 class="page-title">/m/changepoint.go</h1>
			<dl class="in">
			
				<dt>userId</dt>	 <dd><input type="text" name="userId"  placeholder="userId" class="bb"></dd>
				<dt>fmoney</dt>	 <dd><input type="text" name="fmoney" value="fmoney" class="bb"></dd>
				<dt>point</dt>	 <dd><input type="text" name="point" value="포인트" class="bb"></dd>
				<dt>money</dt>	 <dd><input type="text" name="money" value="money" class="bb"></dd>
				<dt>ownerName</dt>	 <dd><input type="text" name="ownerName"  placeholder="ownerName" class="bb"></dd>
				<dt>bank</dt>	 <dd><input type="text" name="bank"  placeholder="bank" class="bb"></dd>
				<dt>bankCount</dt>	 <dd><input type="text" name="bankCount"  placeholder="bankCount" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('changepoint');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"신청완료.","result":true}
				</div>
				<div id="changepoint-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="my_changeList" name="my_changeList" action="/m/my_changeList.go">
			<li>나의 환전리스트</li>
			<h1 class="page-title">/m/my_changeList.go</h1>
			<dl class="in">
			
				<dt>userId</dt>	 <dd><input type="text" name="userId"  placeholder="userId" class="bb"></dd>
				<dt>page</dt>	 <dd><input type="text" name="page" value="page" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('my_changeList');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="my_changeList-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
<div class="div-title">
	충전
</div>
	<div class="api">
		<form method="post" id="charge_list" name="charge_list" action="/m/charge_list.go">
			<li>충전리스트</li>
			<h1 class="page-title">/m/charge_list.go</h1>
			<dl class="in">
				
				<dt>userId</dt>	 <dd><input type="text" name="userId"  placeholder="userId" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('charge_list');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="charge_list-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="my_paylist" name="my_paylist" action="/m/my_paylist.go">
			<li>충전내역</li>
			<h1 class="page-title">/m/my_paylist.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId"  placeholder="userId" class="bb"></dd>
				<dt>page</dt>	 <dd><input type="text" name="page"  placeholder="page" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('my_paylist');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="my_paylist-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	
		<div class="api">
		<form method="post" id="order_kspay" name="order_kspay" action="/order_kspay.go">
			<li>충전결제전</li>
			<h1 class="page-title">/order_kspay.go</h1>
			<dl class="in">
				
				
				<dt>sndGoodname</dt>	 <dd><input type="text" name="sndGoodname"  placeholder="상품명" class="bb"></dd>
				<dt>sndAmount</dt>	 <dd><input type="text" name="sndAmount"  placeholder="결제금액" class="bb"></dd>
				<dt>sndOrdername</dt>	 <dd><input type="text" name="sndOrdername"  placeholder="주문자아이디" class="bb"></dd>
				<dt>sndEmail</dt>	 <dd><input type="text" name="sndEmail"  placeholder="결제후 이메일" class="bb"></dd>
				<dt>sndMobile</dt>	 <dd><input type="text" name="sndMobile"  placeholder="전화번호" class="bb"></dd>
				<dt>comment</dt>	 <dd><input type="text" name="comment"  placeholder="혜택" class="bb"></dd>
				<dt>userpoint</dt>	 <dd><input type="text" name="userpoint"  placeholder="유저포인트" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('order_kspay');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="order_kspay-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
<!-- 	<div class="api">
		<form method="post" id="charge_into" name="charge_into" action="/m/charge_into.go">
			<li>충전결제완료후</li>
			<h1 class="page-title">/m/charge_into.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId"  placeholder="userId" class="bb"></dd>
				<dt>paymentType</dt>	 <dd><input type="text" name="paymentType"  placeholder="1000000000카드 0000010000소액결제" class="bb"></dd>
				<dt>priceSum</dt>	 <dd><input type="text" name="priceSum"  placeholder="충전포인트" class="bb"></dd>
				<dt>totalFee</dt>	 <dd><input type="text" name="totalFee"  placeholder="결제금액" class="bb"></dd>
				<dt>cardName</dt>	 <dd><input type="text" name="cardName"  placeholder="카드이름" class="bb"></dd>
				<dt>cardNumber</dt>	 <dd><input type="text" name="cardNumber"  placeholder="카드번호" class="bb"></dd>
				<dt>mobile</dt>	 <dd><input type="text" name="mobile"  placeholder="핸드폰번호" class="bb"></dd>
				<dt>comment</dt>	 <dd><input type="text" name="comment"  placeholder="comment" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('charge_into');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="charge_into-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div> -->
	
<div class="div-title">
	무료충전
</div>
	<div class="api">
		<form method="post" id="free_point" name="free_point" action="/m/free_point.go">
			<li>무료충전</li>
			<h1 class="page-title">/m/free_point.go</h1>
			<dl class="in">
				<dt>partner</dt>	 <dd><input type="text" name="partner"  placeholder="partner" class="bb"></dd>
				<dt>cust_id</dt>	 <dd><input type="text" name="cust_id"  placeholder="cust_id" class="bb"></dd>
				<dt>ad_no</dt>	 <dd><input type="text" name="ad_no"  placeholder="ad_no" class="bb"></dd>
				<dt>point</dt>	 <dd><input type="text" name="point"  placeholder="point" class="bb"></dd>
				<dt>ad_title</dt>	 <dd><input type="text" name="ad_title"  placeholder="ad_title" class="bb"></dd>
				<dt>seq_id</dt>	 <dd><input type="text" name="seq_id"  placeholder="seq_id" class="bb"></dd>
				<dt>vaild_key</dt>	 <dd><input type="text" name="vaild_key"  placeholder="vaild_key" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('free_point');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="free_point-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
<div class="div-title">
	아이템
</div>
	<div class="api">
		<form method="post" id="buy_item_list" name="buy_item_list" action="/m/buy_item_list.go">
			<li>아이템리스트</li>
			<h1 class="page-title">/m/buy_item_list.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId"  placeholder="userId" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('buy_item_list');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="buy_item_list-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="buy_item" name="buy_item" action="/m/buy_item.go">
			<li>아이템구매</li>
			<h1 class="page-title">/m/buy_item.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId"  placeholder="userId" class="bb"></dd>
				<dt>seq</dt>	 <dd><input type="text" name="seq"  placeholder="seq" class="bb"></dd>
				
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('buy_item');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="buy_item-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="buy_myitem_list" name="buy_myitem_list" action="/m/buy_myitem_list.go">
			<li>내아이템구매리스트</li>
			<h1 class="page-title">/m/buy_myitem_list.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId"  placeholder="userId" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('buy_myitem_list');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="buy_myitem_list-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	


<div class="div-title">
	레벨
</div>	
	<div class="api">
		<form method="post" id="level_info" name="level_info" action="/m/level_info.go">
			<li>레벨안내</li>
			<h1 class="page-title">/m/level_info.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId"  placeholder="userId" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('level_info');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="level_info-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>

<div class="div-title">
	버전
</div>	
	
	<div class="api">
		<form method="post" id="version" name="version" action="/m/version.go">
			<li>버전확인</li>
			<h1 class="page-title">/m/version.go</h1>
			<dl class="in">
				<dt>osType</dt>	 <dd><input type="text" name="osType"  placeholder="osType" class="bb"></dd>
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('version');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="version-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
<div class="div-title">
	로그아웃
</div>		
	<div class="api">
		<form method="post" id="logout" name="logout" action="/m/logout.go">
			<li>로그아웃</li>
			<h1 class="page-title">/m/logout.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId"  placeholder="userId" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('logout');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="logout-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
<div class="div-title">
	인앱
</div>

	<div class="api">
		<form method="post" id=inApp_insert name="inApp_insert" action="/m/inApp_insert.go">
			<li>인앱결제</li>
			<h1 class="page-title">/m/inApp_insert.go</h1>
			<dl class="in">
				<dt>packageName</dt>	 <dd><input type="text" name="packageName"  placeholder="packageName" class="bb"></dd>
				<dt>productId</dt>	 <dd><input type="text" name="productId"  placeholder="productId" class="bb"></dd>
				<dt>purchaseToken</dt>	 <dd><input type="text" name="purchaseToken"  placeholder="purchaseToken" class="bb"></dd>
				<dt>accessToken</dt>	 <dd><input type="text" name="accessToken"  placeholder="accessToken" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('inApp_insert');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="inApp_insert-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>		
<div class="div-title">
	푸시키테스트
</div>		
	<div class="api">
		<form method="post" id="push_test" name="push_test" action="/m/push_test.go">
			<li>푸시키테스트</li>
			<h1 class="page-title">/m/push_test.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId"  placeholder="userId" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('push_test');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="push_test-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	
	
<div class="div-title">
	채팅
</div>		
	<div class="api">
		<form method="post" id="my_chat_list" name="my_chat_list" action="/m/my_chat_list.go">
			<li>채팅방목록(내꺼-인원수 받아올거)</li>
			<h1 class="page-title">/m/my_chat_list.go</h1>
			<dl class="in">
				<dt>chatRoomSeq</dt>	 <dd><input type="text" name="chatRoomSeq"  placeholder="chatRoomSeq" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('my_chat_list');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="my_chat_list-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>

		<form method="post" id="chat_list" name="chat_list" action="/m/chat_list.go">
			<li>채팅방 리스트</li>
			<h1 class="page-title">/m/chat_list.go</h1>
			<dl class="in">
				<dt>page</dt>	 <dd><input type="text" name="page" value="1" class="bb"></dd>
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>keyword</dt>	 <dd><input type="text" name="keyword"  placeholder="keyword" class="bb"></dd>
				<dt>age</dt>	 <dd><input type="text" name="age"  placeholder="0:무관, 10대:10, 20대:20, ..." class="bb"></dd>
				<dt>gender</dt>	 <dd><input type="text" name="gender"  placeholder="0:무관, 1:남, 2:여" class="bb"></dd>
				<dt>area</dt>	 <dd><input type="text" name="area"  placeholder="지역" class="bb"></dd>
				<dt>distance</dt>	 <dd><input type="text" name="distance"  placeholder="거리 0:무관" class="bb"></dd>
				<dt>latitude</dt>	 <dd><input type="text" name="latitude"  placeholder="위도" class="bb"></dd>
				<dt>longitude</dt>	 <dd><input type="text" name="longitude"  placeholder="경도" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chat_list');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="chat_list-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
		
		<form method="post" id="buy_chatitem_list" name="buy_chatitem_list" action="/m/buy_chatitem_list.go">
			<li>채팅방 생성 전 아이템</li>
			<h1 class="page-title">/m/buy_chatitem_list.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('buy_chatitem_list');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="buy_chatitem_list-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
		<form method="post" id="chat_add" name="chat_add" action="/m/chat_add.go">
			<li>채팅방 생성</li>
			<h1 class="page-title">/m/chat_add.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>title</dt>	 <dd><input type="text" name="title"  placeholder="title" class="bb"></dd>
				<dt>area</dt>	 <dd><input type="text" name="area"  placeholder="지역" class="bb"></dd>
				<dt>itemSeq</dt>	 <dd><input type="text" name="itemSeq"  placeholder="유저아이템 itemSeq" class="bb"></dd>
				<dt>memberLimit</dt>	 <dd><input type="text" name="memberLimit"  placeholder="2,4,10,20,50" class="bb"></dd>
				<dt>useMegaphone</dt>	 <dd><input type="text" name="useMegaphone"  placeholder="0:사용안함, 1:사용" class="bb"></dd>
				<dt>useFreeTicket</dt>	 <dd><input type="text" name="useFreeTicket"  placeholder="0:사용안함, 1:사용" class="bb"></dd>
				<dt>onlyOppositeSex</dt>	 <dd><input type="text" name="onlyOppositeSex"  placeholder="0:사용안함, 1:사용" class="bb"></dd>
				<dt>isOneone</dt>	 <dd><input type="text" name="isOneone"  placeholder="1:일대일, 0:해당없음" class="bb"></dd>
				<dt>isAnonym</dt>	 <dd><input type="text" name="isAnonym"  placeholder="1:익명채팅, 0:해당없음" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chat_add');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="chat_add-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
		<form method="post" id="chat_join" name="chat_join" action="/m/chat_join.go">
			<li>채팅방 입장</li>
			<h1 class="page-title">/m/chat_join.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>chatRoomSeq</dt>	 <dd><input type="text" name="chatRoomSeq"  placeholder="chatRoomSeq" class="bb"></dd>
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chat_join');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="chat_join-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
		<form method="post" id="chat_room_mega" name="chat_room_mega" action="/m/chat_room_mega.go">
			<li>채팅방 확성기 적용</li>
			<h1 class="page-title">/m/chat_room_mega.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId"  placeholder="userId" class="bb"></dd>
				<dt>chatRoomSeq</dt>	 <dd><input type="text" name="chatRoomSeq"  placeholder="chatRoomSeq" class="bb"></dd>
				<dt>itemSeq</dt>	 <dd><input type="text" name="itemSeq"  placeholder="itemSeq" class="bb"></dd>
				
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chat_room_mega');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="chat_room_mega-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
		<form method="post" id="chat_room_info" name="chat_room_info" action="/m/chat_room_info.go">
			<li>채팅방 정보</li>
			<h1 class="page-title">/m/chat_room_info.go</h1>
			<dl class="in">
				
				<dt>chatRoomSeq</dt>	 <dd><input type="text" name="chatRoomSeq"  placeholder="chatRoomSeq" class="bb"></dd>
				
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chat_room_info');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="chat_room_info-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
		<form method="post" id="chat_memberList" name="chat_memberList" action="/m/chat_memberList.go">
			<li>채팅방 참여 리스트</li>
			<h1 class="page-title">/m/chat_memberList.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId"  placeholder="userId" class="bb"></dd>
				<dt>chatRoomSeq</dt>	 <dd><input type="text" name="chatRoomSeq"  placeholder="chatRoomSeq" class="bb"></dd>
			<!-- 	<dt>page</dt>	 <dd><input type="text" name="page"  placeholder="page" class="bb"></dd> -->
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chat_memberList');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="chat_memberList-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
		<form method="post" id="chat_fnc" name="chat_fnc" action="/m/chat_fnc.go">
			<li>채팅방 신고하기</li>
			<h1 class="page-title">/m/chat_fnc.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>blockId</dt>	 <dd><input type="text" name="blockId"  placeholder="blockId" class="bb"></dd>
				<dt>fncReason</dt>	 <dd><input type="text" name="fncReason"  placeholder="fncReason" class="bb"></dd>
				<dt>fncFiles</dt>	 <dd><input type="text" name="fncFiles"  placeholder="fncFiles" class="bb"></dd>
				<dt>chatRoomSeq</dt>	 <dd><input type="text" name="chatRoomSeq"  placeholder="chatRoomSeq" class="bb"></dd>
				<dt>contents</dt>	 <dd><input type="text" name="contents"  placeholder="contents" class="bb"></dd>
			
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chat_fnc');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="chat_fnc-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
		<form method="post" id="chat_push" name="chat_push" action="/m/chat_push.go">
			<li>채팅방 푸시설정</li>
			<h1 class="page-title">/m/chat_push.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>status</dt>	 <dd><input type="text" name="status" placeholder="0:알림끔 3:알림" class="bb"></dd>
				
				<dt>chatRoomSeq</dt>	 <dd><input type="text" name="chatRoomSeq"  placeholder="chatRoomSeq" class="bb"></dd>
			
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chat_push');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="chat_push-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
		<form method="post" id="chat_out" name="chat_out" action="/m/chat_out.go">
			<li>채팅방 나오기</li>
			<h1 class="page-title">/m/chat_out.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>chatRoomSeq</dt>	 <dd><input type="text" name="chatRoomSeq"  placeholder="chatRoomSeq" class="bb"></dd>
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chat_out');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="chat_out-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
		
		<li class="api">
			<c:set var="url" value="banner_list"></c:set>
			<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
				<h1>배너광고 리스트</h1>
				<h2 class="page-title">/m/${url}.go</h2>
				<dl class="in">
					<dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
				</dl>
				<div class="out">
					<div class="result-view">
					</div>
					<div id="${url}-result" class="result-view real"></div>
				</div>
				<div class="clear"></div>
			</form>
		</li>
		
		<div class="api">
			<form method="post" id="bannerSeq" name="bannerForm" action="/m/banner_click.go">
				<li>해당 배너 링크이동</li>
				<h1 class="page-title">/m/banner_click.go</h1>
				<dl class="in">
					<dt>bannerSeq</dt>				<dd><input type="text" name="bannerSeq" placeholder="" class="bb"></dd>
					<dt>&nbsp;</dt>					<dd><button type="submit" onclick="formSubmit('bannerSeq');" class="bb round green" style="width:274px;">확인</button></dd>
				</dl>
				<div class="out">
					<div class="result-view">
	
					</div>
					<div id="banner-list-result" class="result-view real"></div>
				</div>
				<div class="clear"></div>
			</form>
		</div>
		
	<li class="api">
		<c:set var="url" value="guide_notice"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>이용안내</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
			<dt>site</dt>				<dd><input type="text" name="site" placeholder="" class="bb"></dd>
			   <dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="first_bbs"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>첫글판별</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			   <dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
		<li class="api">
		<c:set var="url" value="message_chk"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>알림체크</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>seq</dt>	 <dd><input type="text" name="seq" placeholder="seq" class="bb"></dd>
				<dt>type</dt>	 <dd><input type="text" name="type" placeholder="친구요청 :2 대화요청 :4" class="bb"></dd>
			
			   <dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>

	<li class="api">
		<c:set var="url" value="qna_view"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>1:1문의 읽기</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>qnaSeq</dt>	 <dd><input type="text" name="qnaSeq" placeholder="qnaSeq" class="bb"></dd>
				
			   <dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>		
	
	<li class="api">
		<c:set var="url" value="qna_edit_do"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>1:1문의 등록/수정</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>qnaSeq</dt>	 <dd><input type="text" name="qnaSeq" placeholder="qnaSeq" class="bb"></dd>
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>contents</dt>	 <dd><input type="text" name="contents" placeholder="contents" class="bb"></dd>
				<dt>title</dt>	 <dd><input type="text" name="title" placeholder="title" class="bb"></dd>
				
			   <dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>		

	<li class="api">
		<c:set var="url" value="qna_delete"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>1:1문의지우기</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>qnaSeq</dt>	 <dd><input type="text" name="qnaSeq" placeholder="qnaSeq" class="bb"></dd>
				
			   <dt>&nbsp;</dt><dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div>
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>		


	</div>

</ol>

</body>
</html>