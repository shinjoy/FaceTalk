var chat = {
	getList : function(frm,page) {
		/*
		$.ajaxPrefilter('json', function(options, orig, jqXHR) {
			return 'jsonp';
		});
*/
		var param = {
			page : page,
			userId : frm.chatId.value,
			agentId : frm.userId.value,
			age : frm.age.value,
			admin : 2,
			gender : frm.gender.value,
			keyword : frm.keyword.value,
			area : frm.area.value,
            distance : frm.distance.value,
            latitude : 0,
            longitude : 0
		};

		$.ajax({
		    type:"GET",
		    url:"/m/chat_list.go",
			data:param,
		    dataType:"json",
			//crossDomain: true,
		    success:function(json) {
				var list = json.list;
				var str = "";
				if (list.length > 0) {
					for (var i=0; i<list.length; i++) {
						
						var roomType = ""; 
						if (list[i].chatRoomType == 1) {
							roomType = "1:N";
						} else if (list[i].chatRoomType == 2) {
							roomType = "1:1";
						} else if (list[i].chatRoomType == 3) {
							roomType = "익명";
						}
						if (list[i].useSpeaker==1) {
							str += '			<li class="list-cell megaphone">';
						} else {
							str += '			<li class="list-cell">';
						}
						str += '				<ul class="chat-list-cell">';
						str += '					<li class="c1">';
						if (list[i].photo=="" || list[i].photo == null || list[i].chatRoomType == 3) {
							if (list[i].gender == 1) {
								str += '			<div class="thumb40" style="background-image:url(\'/images/icon_man.png\')"></div>';
							} else {
								str += '			<div class="thumb40" style="background-image:url(\'/images/icon_woman.png\')"></div>';
							}
							
						} else {
							str += '				<div class="thumb40" style="background-image:url(\''+list[i].photo+'\')"></div>';					
						}	
						str += '					</li>';
						
						if (frm.chatId.value == '') {
							str += '					<li class="c2">';
							str += '						<h2 onclick="alert(\'가상계정을 선택해주세요.\');" style="cursor:pointer;">'+list[i].title+'</h2>';
							str += '						<p onclick="alert(\'가상계정을 선택해주세요.\');" style="cursor:pointer;">'+roomType+' | '+list[i].area+'| '+list[i].age+'</p>';
							str += '					</li>';
						} else if (list[i].otherAgentUser != "" /*&& list[i].otherAgentUser != frm.chatId.value*/) {
							str += '					<li class="c2">';
							str += '						<h2 onclick="alert(\''+list[i].otherAgentUser+'계정으로 이미 참여중인 방입니다.\');" style="cursor:pointer;">'+list[i].title+'</h2>';
							str += '						<p onclick="alert(\''+list[i].otherAgentUser+'계정으로 이미 참여중인 방입니다.\');" style="cursor:pointer;">'+roomType+' | '+list[i].area+'| '+list[i].age+'<br> <span style="color: #FF5E00;"> [참여ID ] '+list[i].otherAgentUser+'</span></p>';
							str += '					</li>';
						/*
						} else if (list[i].agentId == frm.userId.value) {
							str += '					<li class="c2">';
							str += '						<h2 onclick="alert(\'이미 참여중인 방입니다.\');" style="cursor:pointer;">'+list[i].title+'</h2>';
							str += '						<p onclick="alert(\'이미 참여중인 방입니다.\');" style="cursor:pointer;">'+roomType+' | '+list[i].area+'| '+list[i].age+'</p>';
							str += '					</li>';
						*/
						} else {
							str += '					<li class="c2">';
							str += '						<h2 onclick="chat.enterRoom('+list[i].chatRoomSeq+');" style="cursor:pointer;">'+list[i].title+'</h2>';
							str += '						<p onclick="chat.enterRoom('+list[i].chatRoomSeq+');" style="cursor:pointer;">'+roomType+' | '+list[i].area+'| '+list[i].age+'</p>';
							str += '					</li>';
						}
						str += '					<li class="c3">';
						str += '						<p>'+list[i].memberCount+'/'+list[i].memberLimit+'</p>';
						str += '						<p>'+list[i].memberCount+'</p>';
						str += '					</li>';
						str += '				</ul>';
						str += '			</li>';
					}
				} else {
					str = '<li class="list-cell empty">검색된 채팅방이 없습니다.</li>';
				}
		        $("#chat-list").html(str);
		        $(".paging-block").html(json.paging);
		    },
		    error:function(xhr, status, error) {
				console.log("err:",xhr, status, error);
		    },
		    complete:function(data) {
				console.log("complete:",data);
		    }
		});
		return false;
	},
	editRoom : function(frm) {
		var useMegaphone=0;
		var isAnonym=0;
		if (frm.chatId.value == "") {
			alert("가상계정을 설정하세요.");
			return false;
		}
		if (frm.title.value == "") {
			alert("방제목을 설정하세요.");
			return false;
		}
		if (frm.area.value == "") {
			alert("지역을 설정하세요.");
			return false;
		}
		if (frm.chatId.value == "") {
			alert("인원을 설정하세요.");
			return false;
		}
		if(frm.useMegaphone.checked){
			useMegaphone=1;
		}
		if(frm.isAnonym.checked){
			isAnonym=1;
		}
	
		var chatId = frm.chatId.value;
		var param = {
			userId : chatId,
			title : frm.title.value,
			itemSeq : frm.itemSeq.value,
			memberLimit : frm.memberLimit.value,
			area : frm.area.value,
			useMegaphone : useMegaphone,
			useFreeTicket : frm.useFreeTicket.value,
			onlyOppositeSex : frm.onlyOppositeSex.value,
			isOneone : frm.isOneone.value,
			isAnonym : isAnonym
			
		};

		$.ajax({
		    type:"GET",
		    url:"/m/chat_add.go",
			data:param,
		    dataType:"json",
			//crossDomain: true,
		    success:function(json) {
				alert(json.message);
				//alert(frm.chatId.value +","+ json.room.chatRoomSeq);
				chat.joinRoom(frm.chatId.value,json.room.chatRoomSeq);
				frm.chatId.value = "";
				frm.title.value = "";
				frm.useMegaphone.checked = false;
				frm.isAnonym.checked = false;
				frm.area.value = "";
				frm.memberLimit.value = 0;
		    },
		    error:function(xhr, status, error) {
				console.log("err:",xhr, status, error);
		    },
		    complete:function(data) {
				console.log("complete:",data);
		    }
		});
		return false;
	},
	joinRoom : function(userId, chatRoomSeq) {
		var param = {
			userId : userId,
			chatRoomSeq : chatRoomSeq
		};

		$.ajax({
		    type:"GET",
		    url:"/m/chat_join.go",
			data:param,
		    dataType:"json",
			//crossDomain: true,
		    success:function(json) {
		    	searchForm.chatId.value = userId;
				chat.enterRoom(chatRoomSeq);
		    },
		    error:function(xhr, status, error) {
				console.log("err:",xhr, status, error);
		    },
		    complete:function(data) {
				console.log("complete:",data);
		    }
		});
		return false;
	},
	
	enterRoom : function(chatRoomSeq) {
		var userId = searchForm.chatId.value;
		alert(userId);
		if (userId == '') {
			alert("가상계정을 설정해주세요.");
		} else {
			window.location = "jscall://callEnterChatRoom?"+userId+","+chatRoomSeq;
			chat.reloadTimer = setTimeout(chat.pageReload, 80);
		}
	},
	reloadTimer : null,
	
	pageReload : function() {
		document.location.reload();
		clearTimeout(chat.reloadTimer);
	}
};