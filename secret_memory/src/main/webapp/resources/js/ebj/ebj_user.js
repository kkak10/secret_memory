/**
 * 
 */
/*login 기능*/
function userLogin() {
	$.ajax({ url : "/user/login",
			 type : "post",
			 data : { "userId" : $("#userId").val(), "userPw" : $("#userPw").val() },
			 dataType : "json",}).done(function(loginMap) {
				 				  console.log("------------------------ userLogin start");
								  console.log(loginMap);
								  
								  if(loginMap.check === "SUCCESS"){
									  
									  $("#menuLogin").html("");
									  if ($("#menuLogin").hasClass("dropdown-toggle") == false) {
										  $("#menuLogin").addClass("dropdown-toggle");
										  $("#menuLogin").attr("data-toggle","dropdown");
										  $("#top").animate({"left":"26%"},400);
									  }
									  $("#menuChat").show();
									  
									  $("#menuLogin").append("<span class='caret' style='border-top-color: white'></span>");
									  $("#menuLogin").prepend("<img src='" + loginMap.dbUser.userImage + "'style='width:40px;display:inline'/>");
									  $("#loginDiv").hide();
									  //채팅 
									  $(".chatActivity").hide();
									  $(".chatGroup").remove();
									  $("#groupListBtn").show();
									  
									  chatGroupList();
									  //채팅 
									  if (loginMap.inviteGroupListNo === "true") {
										  insertGroupUser();
									  }
									  $("#sidebar-wrapper").css("left","250px");
									  groupListView();
								  }else{
									  alert("다시 입력해주세요");
									  $("#userPw").val('');
								  }
								  
								  console.log("------------------------ userLogin end");
	});
}//end of userLogin



/*회원가입 기능*/
function regist(){
	$.ajax({ url : "/user/regist",
		 type : "post",
		 data : { "userId" : $("#registId").val(), "userPw" : $("#registPw").val(),
			 "userName" : $("#registName").val(), "userNickname" : $("#registNickname").val()},
		 dataType : "json",}).done(function(checkMap) {
			 				  console.log("------------------------ regist start");
							  console.log(checkMap);
							  
							  if(checkMap.check === "SUCCESS"){
								  alert("가입이 완료되었습니다.");
								  $("#btnNewUser").trigger("click");
							  }else{
								  alert("다시 입력해주세요");
							  }
							  
							  console.log("------------------------ regist end");
	});
}//end of regist

/*실시간 id 중복 체크 기능*/
function keyUp (){
	$.ajax({ url : "/user/registIdCheck",
		 type : "post",
		 data : { "userId" : $("#registId").val()},
		 dataType : "json",}).done(function(checkMap) {
			 				  console.log("------------------------ keyUp start");
							  console.log(checkMap);
							  
							  if(checkMap.idCheck === "SUCCESS"){
								  	$("#idCheck").text("중복이 아닙니다");
									$("#idCheck").css("color","green");
									$("#idCheck").css("color","green");
									
							  } else {
								  $("#idCheck").text("중복 입니다");
								  $("#idCheck").css("color","red");
								  
							  }
							  
							  console.log("------------------------ keyUp end");
		 });
	}//end of keyup

/* Logout 기능 */
function userLogout() {
	$.ajax({ url : "/user/logout",
			 type : "get",
			 dataType : "json",}).done(function(checkMap) {
				 				  console.log("------------------------ userLogout start");
								  console.log(checkMap);
								  
								  $("#menuLogout").hide();
								  $("#menuLogin").show();
								  window.self.location.href="index.html";
							
								  console.log("------------------------ userLogout end");
	});
}//end of userLogout

/* 마이페이지에서 회원 정보를 보여주는 기능 */
function myPageView() {
	$.ajax({ url : "/user/myPageView",
			 type : "post",
			 dataType : "json",}).done(function(dbUser) {
				 				  console.log("------------------------ myPageView start");
								  console.log(dbUser);
								  
								  $("#myPageId").html(dbUser.userId);
								  $("#myPageName").html(dbUser.userName);
								  $("#myPageNickname").val(dbUser.userNickname);
								  $("#myPageRegDate").html(dbUser.userRegDate);
								  $("#myPageImageView").attr("src",dbUser.userImage);
								  
								  console.log("------------------------ myPageView end");
								  
								  
	});
}//end of myPageView

/*마이페이지에서 회원 정보 수정 (비밀번호, 닉네임)*/
function updateUser(imageUrl) {
	if($("#myPagePw").val() == $("#myPagePwCheck").val()){
		$.ajax({ url : "/user/updateUser",
				 type : "post",
				 data : { "userPw" : $("#myPagePw").val(), "userNickname" : $("#myPageNickname").val(),"userImage":imageUrl},
				 dataType : "json",}).done(function(checkMap) {
					 				  console.log("------------------------ updateUser start");
									  console.log(checkMap);
									  
									  if(checkMap.check === "SUCCESS"){
										  alert("수정 완료");
										  $('#myPage').modal('hide');
										  $("#myPageForm input").val('');
									  } else{
										  alert("다시 입력해 주세요");
									  }
									  console.log("------------------------ updateUser end");		  
		});
	}else{
		alert('비밀번호가 다릅니다.');
	};
}//end of updateUser

/*마이페이지에서 회원 이미지를 DB에 넣는 기능
function updateUserImage(userImage) {
	$.ajax({ url : "/user/updateUserImage",
			 type : "post",
			 data : { "userImage" : userImage },
			 dataType : "json",}).done(function(checkMap) {
				 				  console.log("------------------------ updateUserImage start");
								  console.log(checkMap);
								  
								  if(checkMap.check === "SUCCESS"){
									  console.log("image upload 성공");
								  } else{
									  console.log("image upload 실패");
								  }
								  console.log("------------------------ updateUserImage end");		  
	});
}*/

/*마이페이지에서 회원탈퇴 기능*/
function deleteUser() {
		$.ajax({ url : "/user/deleteUser",
				 type : "post",
				 dataType : "json",}).done(function(checkMap) {
					 				  console.log("------------------------ deleteUser start");
									  console.log(checkMap);
									  
									  if(checkMap.check === "SUCCESS"){
										  alert("회원 탈퇴 되었습니다.");
										  userLogout();
									  } else{
										  alert("회원 탈퇴 실패");
									  }
									  console.log("------------------------ deleteUser end");		  
		});
}//end of deleteUser

//그룹 정보에 그룹의 멤버를 뿌려주는 메서드
function selectGroupUserList(groupListNo){
	//jquery 템플릿
	var userTemplete =  "<div class='well col-sm-12 col-md-8 col-md-offset-2' style='width:30%;height:10%;margin-left:105px'>" +
			"<div class='row user-row'><div class='col-md-4'> <img class='groupUserImage'src='${userImage}' alt='User Pic'>" +
			"</div><div class='col-md-7'>" +
			"<strong style='font-size:15px;color:gray'>${userId}</strong><br>" +
			"<span class='text-muted'>등급: ${groupUserGrade}</span></div>" +
			"<div class='col-md-1 dropdown-user' data-for='.cyruxx'>" +
			"<i class='glyphicon glyphicon-chevron-down text-muted'></i>" +
			"</div></div><div class='row user-infos cyruxx groupUserHide'>" +
			"<div class='col-sm-10 col-md-10' style='width:100%'>" +
			"<div class='panel panel-primary' style='margin-top:10px' >" +
			"<div class='panel-heading'><h3 class='panel-title'>User information</h3></div>" +
			"<div class='panel-body'><div class='row'><div class='col-md-3'>" +
			"<img class='groupUserImage' src='${userImage}' alt='User Pic'></div>" +
			"<div class='col-md-6'><strong>${groupUser}</strong><br>" +
			"<table class='table table-condensed table-responsive table-user-information'>" +
			"<tbody><tr><td style='color:gray'>Name</td><td style='color:gray'>${userId}</td></tr>" +
			"<tr><td style='color:gray'>Nickname</td><td style='color:gray'>${userNickname}</td></tr>" +
			"<tr><td style='color:gray'>Grade</td><td style='color:gray'>${groupUserGrade}</td></tr>" +
			"<tr><td style='color:gray'>RegistDate</td><td style='color:gray'>${userRegDate}</td></tr>" +
			"</tbody></table></div></div></div>" +
			"<div class='panel-footer'><button class='btn btn-sm btn-primary' type='button'data-toggle='tooltip'data-original-title='Send message to user'>" +
			"<i class='glyphicon glyphicon-envelope'></i></button><span class='pull-right'>" +
			"<button class='btn btn-sm btn-warning' type='button'data-toggle='tooltip'data-original-title='Edit this user'>" +
			"<i class='glyphicon glyphicon-edit'></i></button><button class='btn btn-sm btn-danger' type='button'data-toggle='tooltip'data-original-title='Remove this user'>" +
			"<i class='glyphicon glyphicon-remove'></i></button></span></div></div></div></div></div>";
	
	$.template( "userTemplate", userTemplete );
	$.ajax({
		url:"/user/selectGroupUserList",
		type:"post",
		datatype:"json",
		data:{"groupListNo":groupListNo}
	}).done(function(userGroupList){
		console.log("------------------------ selectGroupUserList start");
		
		console.log(userGroupList);

		$.tmpl( "userTemplate", userGroupList ).appendTo( "#userList" );
	
		console.log("------------------------ selectGroupUserList end");
			
	});
}


/*실시간 id 형식(email) 체크 기능*/
function mailCheck(check){
	var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		if (exptext.test($("#registId").val())!=true){
			$("#idCheck").css("color","red");
			$("#idCheck").text("E-mail 형식이 아닙니다.");
			$("#registPw").val('');
		}else{
			regist();
			document.getElementById( "formRegister" ).reset();
			$("#formRegister").removeClass();
			$("#formRegister").addClass("form collapse");
			$("#userId").focus();
			
		}
}//end of emailCheck

/* 로그인시 엔터키 실행 */
function loginEnter(){
	if(event.keyCode==13){
		userLogin();
	};
}

/* 회원가입시 엔터키 실행 */
function registEnter(){
	if(event.keyCode==13){
		mailCheck();
	};
}
