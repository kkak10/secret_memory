var imageUrl = '';

/* 마이페이지에서 회원 이미지를 넣는 기능 (DB에는 가지 않는다) */
function UserImageUpload() {
	console.log("----------------------UserImageUpload Start");
	$("#myPageImageUploadForm").ajaxSubmit({
		url 		: "/file/imageUpload",
		type 		: "post",
		dataType 	: "json",
		data		: { "folderName" : $("#myPageId").html()},
		complete 	: function(imageUrlMap) {

			console.log(imageUrlMap);
			imageUrl = JSON.parse(imageUrlMap.responseText).dbInsertPath;
			
			//$("#myPageImageView").attr("src","http://121.160.208.118:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			$("#myPageImageView").attr("src","http://localhost:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			 
			//$("#myPageLightBox").attr("href","http://121.160.208.118:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			$("#myPageLightBox").attr("href","http://localhost:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			
			$('#myPageLightBox').trigger('click');
		}
	});
	console.log("----------------------UserImageUpload End");
};//end of UserImageUpload

/* 게시판에서 이미지를 넣는 기능 (DB에는 가지 않는다) */
function boardImageUpload (){
	console.log("----------------------boardImageUpload Start");
	$("#boardImageUploadForm").ajaxSubmit({
		url 		: "/file/boardImageUpload",
		type 		: "post",
		dataType 	: "json",
		data		: { "folderName" : $("#selectGroupName").html()},
		complete 	: function(imageUrlMap) {

			console.log(imageUrlMap);
			imageUrl = JSON.parse(imageUrlMap.responseText).dbInsertPath;
			
			//$("#boardImageView").attr("src","http://121.160.208.118:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			$("#boardImageView").attr("src","http://localhost:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
	
			//$("#myPageLightBox").attr("href","http://121.160.208.118:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			$("#boardLightBox").attr("href","http://localhost:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			
			$('#boardLightBox').trigger('click');
		}
	});
	console.log("----------------------boardImageUpload End");
};

/* 맵 게시판에서 이미지를 넣는 기능 (DB에는 가지 않는다) */
function mapBoardImageUpload(){
	console.log("----------------------mapBoardImageUpload Start");
	$("#mapBoardImageUploadForm").ajaxSubmit({
		url 		: "/file/mapBoardImageUpload",
		type 		: "post",
		dataType 	: "json",
		data		: { "folderName" : $("#selectGroupName").html()},
		complete 	: function(imageUrlMap) {

			console.log(imageUrlMap);
			imageUrl = JSON.parse(imageUrlMap.responseText).dbInsertPath;
			
			//$("#boardImageView").attr("src","http://121.160.208.118:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			$("#mapBoardImageView").attr("src","http://localhost:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
	
			//$("#myPageLightBox").attr("href","http://121.160.208.118:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			$("#mapBoardLightBox").attr("href","http://localhost:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			
			$('#mapBoardLightBox').trigger('click');
		}
	});
	console.log("----------------------mapBoardImageUpload End");
	
};

/* db에 있는 게시판 이미지들을 그룹별로 뿌려준다. */
function galleryImage(){
	console.log("----------------------galleryImage Start");
	$("#boardImageUploadForm").ajaxSubmit({
		url 		: "/file/boardImageUpload",
		type 		: "post",
		dataType 	: "json",
		complete 	: function(imageUrlMap) {

			console.log(imageUrlMap);
			imageUrl = JSON.parse(imageUrlMap.responseText).dbInsertPath;
			
			//$("#boardImageView").attr("src","http://121.160.208.118:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			$("#boardImageView").attr("src","http://localhost:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
	
			//$("#myPageLightBox").attr("href","http://121.160.208.118:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			$("#boardLightBox").attr("href","http://localhost:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			
			$('#boardLightBox').trigger('click');
		}
	});
	console.log("----------------------galleryImage End");
};

//그룹이미지 전송
function groupImageUpload() {
	console.log("----------------------groupImageUpload Start");
	$("#groupImageUploadForm").ajaxSubmit({
		url 		: "/file/groupImageUpload",
		type 		: "post",
		dataType 	: "json",
		data		: { "folderName" : $("#groupName").val()},
		complete 	: function(imageUrlMap) {

			console.log(imageUrlMap);
			imageUrl = JSON.parse(imageUrlMap.responseText).dbInsertPath;
			
			//$("#myPageImageView").attr("src","http://121.160.208.118:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			$("#createGroupImage").attr("src","http://localhost:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			 
			//$("#myPageLightBox").attr("href","http://121.160.208.118:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			$("#groupLightBox").attr("href","http://localhost:8080/" + JSON.parse(imageUrlMap.responseText).fileInsertPath);
			
			$('#groupLightBox').trigger('click');
		}
	});
	console.log("----------------------groupImageUpload End");
};//end of UserImageUpload

