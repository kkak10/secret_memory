/**
 * 
 */

/*group 생성*/
function groupCreate(imageUrl){
	if($("#groupName").val() != null && $("#groupName").val() != ""){ 	
	$.ajax({ url : "/group/createGroup",
			 type : "post",
			 data : { "groupName" : $("#groupName").val(),"groupImage" : imageUrl },
			 dataType : "json"
			}).done(function() {
								console.log("------------------------ groupCreate start");
	 							alert("그룹이 생성되었습니다.");
	 							$("#groupName").val("");
	 							$(".makeGroupWindow").hide("500");
	 							$("#top").animate({"left":"32%","left":"27%"},500);
	 							$("#groupName").val("");
	 							$("#createGroupImage").attr("src","img/1.jpg")
	 							groupListView();
	 							console.log("------------------------ groupCreate end");
			 		});//ajax 끝
	}else{
		alert("그룹이름을 입력해주세요");
	}
}

/*groupList 보여주기*/
function groupListView(){
	$.ajax({url:"/group/selectGroupList",
			type:"post",
			datatype:"json"
		}).done(function(groupListData){
										console.log("------------------------ groupListView start");
										var groupList = "<li class='sidebar-brand'><a id='makeGroup' href='#'>그룹생성</a></li>";
										console.log(groupListData);
		
										for(var i = 0; i < groupListData.length; i++){
											groupList += "<li><a class='sidebar_a' href='#groupWindowModal' data-toggle='modal' href='#' data-no=" + groupListData[i].groupListNo + "  data-grade=" + groupListData[i].groupUserGrade + ">" + groupListData[i].groupName + "</a></li>";
										}
										
										$(".sidebar-nav").html(groupList);
										
										console.log("------------------------ groupListView end");
				});
}

//그룹 삭제
function deleteMember(){
	$.ajax({url:"/group/deleteMember",
			type:"post",
			dataType : "json",
			data:{"groupListNo":$("#groupListNo").html()}
		}).done(function(checkMap){
				console.log("------------------------ deleteMember start");
				if (checkMap.check == "SUCCESS") {			
					console.log("group에서 삭제 완료");
				} else {
					console.log("group에서 삭제 실패");
				}
				console.log("------------------------ deleteMember end");
				});
}

//그룹장 위임
function updateMaster(){
	$.ajax({url:"/group/updateMaster",
			type:"post",
			data:{"groupListNo":$("#groupListNo").html()},
			successs:function(checkMap){
				console.log("------------------------ updateMaster start");
				if (checkMap.check == "CHANGE") {
					console.log("마스터를 다음사람에게 인계한 후 그룹을 탈퇴합니다.");
				} else {
					console.log("그룹에 멤버가 없으므로  그룹을 삭제합니다.");
				}
				console.log("------------------------ updateMaster end");
			}
		});
}

//이메일 목록 추가
function mailAddList(){
	var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if (exptext.test($("#userMail").val()) != true){
		$("#mailCheck").css("color","red");
		$("#mailCheck").text("E-mail 형식이 아닙니다.");
	}else{
		$("#mailList").append($("#userMail").val()+"<br />");
		$("#userMail").val('');
		$("#mailCheck").html('');
	}
	
}

//이메일로 그룹 초대
function groupInvite(){
	$.ajax({url:"/mail/groupInvite",
			type:"post",
			data:{"inviteGroupListNo":$("#groupListNo").html(),"mailList":$("#mailList").html(),"selectGroupName":$("#selectGroupName").html()}
		}).done(function(checkMap){
			
					console.log("------------------------ groupInvite start");
					
					console.log("------------------------ groupInvite end");
					
				});
}

//이메일로 그룹 가입
function insertGroupUser(){
	$.ajax({url:"/group/insertGroupUser",
			type:"post",
		}).done(function(checkMap){
			
					console.log("------------------------ insertGroupUser start");
					if(checkMap.check == "SUCCESS"){
						alert("그룹에 가입하였습니다.");
					} else if (checkMap.check == "FAIL"){
						alert("이미 그룹에 가입하였습니다.");
					} else {
						alert("그룹가입이 실패하였습니다.");
					}
					console.log("------------------------ insertGroupUser end");
					
				});
}

//마스터일때만 그룹명 변경을 보여줌
function selectMaster(groupListNo){
	$.ajax({url:"/group/selectMaster",
			data:{"groupListNo":groupListNo},
			dataType:"json",
			type:"post",
		}).done(function(checkMap){
					console.log("------------------------ selectMaster start");
					if(checkMap.check == "SUCCESS"){
						if($("#groupSlide").hasClass("active")){
							$("#updateGroupName").show();
						}
					} else if (checkMap.check == "FAIL"){
						$("#updateGroupName").hide();
					}
					console.log("------------------------ selectMaster end");
				});
}

//그룹명 변경
function updateGroupName(){
	$.ajax({url:"/group/updateGroupName",
			data:{"groupListNo":$("#groupListNo").html(),"groupName":$("#updateGroupNameText").val()},
			dataType:"json",
			type:"post",
		}).done(function(checkMap){
					console.log("------------------------ updateGroupName start");
					if(checkMap.check == "SUCCESS"){
						alert("변경되었습니다.");
						$("#updateGroupNameModal").modal("hide");
						$("#updateGroupNameText").val("");
						$("#closeMainGroupWindow").trigger("click");
					} else if (checkMap.check == "FAIL"){
						alert("실패했습니다.");
					}
					console.log("------------------------ updateGroupName end");
				});
};