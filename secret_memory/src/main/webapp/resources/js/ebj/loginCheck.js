/**
 * 로그인 check 기능 
 * 로그인을 한 경우에는 logout을 보여주고
 * 로그인을 하지 않은 경우 login을 보여준다.
 */

/*로그인 체크 기능*/
function loginCheck() {
	$.ajax({ url : "/user/loginCheck",
			 dataType : "json",})
			 .done(function(loginCheckMap) {
				 console.log("------------------------ loginCheck end");
				 console.log(loginCheckMap);
				  
				  if(loginCheckMap.check === "SUCCESS"){
					  $("#groupListBtn").show();
					  $("#menuLogout").show();
					  if (loginCheckMap.inviteGroupListNo === "true") {
						  insertGroupUser();
					  } else {
						  loginCheckMap.inviteGroupListNo = null;
					  }
					  
					  $("#menuLogin").html("");
					  if ($("#menuLogin").hasClass("dropdown-toggle") == false) {
						  $("#menuLogin").addClass("dropdown-toggle");
						  $("#menuLogin").attr("data-toggle","dropdown");
					  }
					  //채팅 
					  $(".chatActivity").hide();
					  $(".chatGroup").remove();
					  console.log("뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟뷟")
					  chatGroupList();
					  
					  //채팅 
					  $("#menuLogin").append("<span class='caret' style='border-top-color: white'></span>");
					  $("#menuLogin").prepend("<img src='" + loginCheckMap.dbUser.userImage + "'style='width:40px;display:inline'/>");
					  $("#loginDiv").hide();
					  $("#menuChat").show();
					  
					  
					  $("#sidebar-wrapper").css("left","250px");
					  groupListView();
					  $("#top").css("left","27%");
				  }else{
					  
					  $("#menuLogin").html("Login");
					  if ($("#menuLogin").hasClass("dropdown-toggle") == true) {
						  $("#menuLogin").removeClass("dropdown-toggle");
						  $("#menuLogin").attr("data-toggle","");
					  }
				
				  }
				  
				  console.log("------------------------ loginCheck end");
	});
}//end of loginCheck