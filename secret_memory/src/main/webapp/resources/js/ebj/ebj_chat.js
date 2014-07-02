var nick_name = '';

/* 자신의 닉네임 가져온다. */
function joinChat(groupListNo,groupName){
	$.ajax({
		url : '/chat/nickName',
		dataType : 'JSON',
		type : 'post',
		complete : function(nickName){
			console.log("닉네임 가져오기 성공 - " , nickName);
			nick_name = nickName.responseText;
			console.log("보내는 네임 Nick : " , nick_name);
			
			socket.emit('join', {
				nick_name : nick_name,
				groupListNo : groupListNo
			});
			$("#chatJoinGroupName").html( groupName+ "톡");
			$(".chatActivity").show();
		}
	});
};

/* 채팅에서 자동 스크롤 증가  */
function scrollDonw(){
	$("#chatGroupList").animate({
		'scrollTop' : $("#chatGroupList")[0].scrollHeight
	}, 200);
};

/*groupList 보여주기*/
function chatGroupList(){
	console.log("윽");
	$.ajax({url:"/group/selectGroupList",
			type:"post",
			datatype:"json"
		}).done(function(groupListData){
			console.log("------------------------ chatGroupList start");
			console.log("♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥",groupListData);
			for(var i = 0 ; i < groupListData.length ; i++){
				$("#chatGroupList").prepend("<li class='chatGroup' data-no='"+groupListData[i].groupListNo+"' style='text-align:left'><a href='#'><span class='label label-warning'>"+(i+1)+"</span><span class='groupListName'>"+groupListData[i].groupName+"</span></a></li>");				
			}
			console.log("------------------------ chatGroupList end");
		});
}


