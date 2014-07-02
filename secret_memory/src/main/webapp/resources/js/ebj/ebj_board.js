// 일단 임시로 Map에 넣어놓음 -- 마커가 있는 경우 글을 등록해주는 메서드
// 카테고리의 번호를 Ajax를 이용해서 쏴줘야 하는데 BoardVO에는 현재 categoryNo가 없음 그러므로 임시로 만들어줌.

var pageNo = 0 ;	//10개씩 나눈 페이지 번호

function insertMapBoard(imageUrl){
   	$.ajax({url:"/board/insertMapBoard",
			type:"post",
			datatype:"json",
			data:{"groupListNo":$("#groupListNo").html(),"boardText":$("#mapBoardText").val(),"categoryNo":$(".form-control option:selected").val(), "boardImage" : imageUrl}
			}).done(function(checkMap){
				console.log("------------------------ insertBoard start");
	        	console.log(checkMap);
	        	boardInsertboolean = false;
	        	
	        	 if(checkMap.check === "SUCCESS"){
	        		 $("#mapBoardText").val('');
	        		 $("#mapBoardImageView").attr("src","");
	        		 alert("글 등록 완료.");
	        		 
	        		 pageNo = 0;
	        		 selectBoardList($("#groupListNo").html(),"insertNo");
	        		 
				 } else {
					 alert("글 등록 실패!!!");
				 }
	        	console.log("------------------------ insertBoard end");
	 }); 
}

/*게시판에서 글을 등록하는 기능*/
function insertBoard(imageUrl){
   	$.ajax({url:"/board/insertBoard",
			type:"post",
			datatype:"json",
			data:{"groupListNo":$("#groupListNo").html(),"boardText":$("#boardText").val(), "boardImage" : imageUrl}
			}).done(function(checkMap){
				console.log("------------------------ insertBoard start");
	        	console.log(checkMap);
	        	
	        	 if(checkMap.check === "SUCCESS"){
	        		 $("#boardText").val('');
	        		 $("#boardImageView").attr("src","");
	        		 
	        		 pageNo = 0;
	        		 selectBoardList($("#groupListNo").html(), "insertNo");
				 } else {
					 alert("글 등록 실패!!!");
				 }
	        	console.log("------------------------ insertBoard end");
	 }); 
}


/*게시판에서 글을 보여주는 기능*/
function selectBoardList(groupListNo, insertNo){
	pageNo++;
	console.log("pageNo : " + pageNo);
	//jquery 템플릿
	var boardTemplete = "<div class='container' style='width:auto'> <div class='row'> <div class='panel-default widget'> <div> <ul class='list-group'> <li class='list-group-item'> <div class='row'> <div class='col-xs-2 col-md-1' style='padding:0;padding-left:10px'> <img src='${userImage}' class='img-responsive' alt='' /></div> <div class='col-xs-10 col-md-11'> <div> <a href='#'> <strong style='font-size:20px'>${boardNickname}</strong></a> <div class='mic-info' style='font-size:11px'>${boardRegDate}</div> <div class='comment-text' style='width:auto;font-size:16px;margin-top:5px'> ${boardText}<br /><img src='${boardImage}' class='boardImage' style='width:100px;height:100px;margin:10px;margin-left:0' /> </div> <div></div> </div> <div class='action'> <button type='button' class='btn btn-primary btn-xs replyListBtn' data-toggle='collapse' data-target='#${boardNickname}${boardNo}' data-boardNo='${boardNo}'> <span class='glyphicon glyphicon-pencil'></span> </button> <span class='boardUser' style='display:none'>${boardUser}</span></div> </div></div> <div id='${boardNickname}${boardNo}' style='margin-top:20px' class='collapse'> <div class='row'><div class='replyList' style='margin-left:70px' data-boardNo='${boardNo}' class='col-xs-10 col-md-11'></div><div class='panel-body'><div class='input-group' style='margin-top:10px'><input id='replyText' type='text' class='form-control' style='margin-left:55px' /><span class='input-group-btn'><button id='insertReply' class='btn btn-primary' type='button' data-boardNo='${boardNo}' >Write</button></span></div></div> </div> </li></ul> </div> </div> </div> </div>";	
	$.template( "boardTemplete", boardTemplete );
   	$.ajax({url:"/board/selectBoardList",
			type:"post",
			datatype:"json",
			data:{"groupListNo" : groupListNo,"pageNo" : pageNo}
			}).done(function(boardList){
				console.log("------------------------ selectBoardList start");
				console.log("★★★★★★★★★★★",boardList);
				console.log("★★★★★★★★★★★",insertNo);
				
				if(insertNo == "insertNo"){
					$("#boardList").html('');
				}
				
				if(boardList.length == 0){
					$("#boardWriteToggleBtn").tigger("click");
				}
				
	        	console.log(boardList);
	        	$.tmpl( "boardTemplete", boardList ).appendTo( "#boardList" );

	        	selectBoardDeleteUser();
	        	$(".boardImage[src='']").remove();
	        	console.log("------------------------ selectBoardList end");
			
	 });  
}

/*맵에서 좌표로 해당 게시판의 목록을 보여주는 기능*/
function selectBoard(oPoint){
	$.ajax({url:"/board/selectBoard",
		type:"post",
		datatype:"json",
		data:{"markerX":oPoint.y, "markerY":oPoint.x}
		}).done(function(boardVOAdd){
			console.log("------------------------ selectBoard start");
        	console.log(boardVOAdd);	
        	$("#infoImage").attr("src",boardVOAdd.userImage);
        	$("#infoTitle").html(boardVOAdd.boardNickname);
        	$("#infoTitle").append("<span id='infoId' style='display:none'>"+boardVOAdd.boardUser+"</span>");
        	$("#infoText").html(boardVOAdd.boardText);
        	selectMaster($("#groupListNo").html());
        	if (boardVOAdd.boardImage != null) {
        		$("#infoTextImage").css("width","80px");
        		$("#infoTextImage").attr("src",boardVOAdd.boardImage);
			}else{
				$("#infoTextImage").remove();
			}
        	selectMapMarkerDeleteUser();
        	console.log("------------------------ selectBoard end");
	}); 
	
}

//게시판 이미지를 겔러리에 뿌려준다.
function selectBoardImage(groupListNo){
	var boardImageTemplete = "<li><a href='${boardImage}' class='example-image-link' data-lightbox='gallery-set'><img class='example-image' style='width:150px; height:120px' src='${boardImage}'/></a></li>";
	$.template( "boardImageTemplete", boardImageTemplete );
	
	$.ajax({url:"/board/selectBoardImage",
		type:"post",
		datatype:"json",
		data:{"groupListNo" : groupListNo}
		}).done(function(boardList){
			console.log("------------------------ selectBoardImage start");
        	console.log(boardList);
        	
        	$.tmpl( "boardImageTemplete", boardList ).appendTo( "#gallery" );
        	
        	
        	//gallery에서 이미지 클릭시
/*			$("#gallery").groupGallery({
		        maxWidth: '700',
		        maxheight: '700'
		    });*/
			
        	
        	console.log("------------------------ selectBoardImage end");
	});
}

/*맵에서 좌표로 해당 게시판과 마커 삭제 */
function deleteMarkerBoard(oPoint){
	$.ajax({url:"/board/deleteMarkerBoard",
		type:"post",
		datatype:"json",
		data:{"markerX":oPoint.y, "markerY":oPoint.x}
		}).done(function(checkMap){
			console.log("------------------------ deleteMarkerBoard start");
        	console.log(checkMap);
        	if (checkMap.check == "SUCCESS") {
        		alert("마커가 삭제되었습니다.");
        		oInfoWnd.setVisible(false);
		 		oMap.clearOverlay();
				oMap.addOverlay(oLabel);
				oMap.addOverlay(oInfoWnd);
				nowLocation();
				selectMarker();
				pageNo = 0;
				$("#boardList").html('');
				selectBoardList(groupListNo);
			} else {
				alert("마커가 삭제하는데 실패하였습니다.");
			}
        	
        	console.log("------------------------ deleteMarkerBoard end");
	}); 
	
}

//게시판에서 삭제할때 자기가 쓴 글인지 판별해서 자기가 쓴 글이면 삭제버튼이 나오게함
function selectBoardDeleteUser(){
	$.ajax({url:"/board/selectBoardDeleteUser",
		dataType:"json",
		type:"post",
	}).done(function(checkMap){
				console.log("------------------------ selectBoardDeleteUser start");
				console.log("캬캬캬캬캬캬캬캬캬캬",checkMap.boardUser);
				if(checkMap.check == "SUCCESS"){
					$(".removeBoard").remove();
					$(".boardUser:contains('"+checkMap.boardUser+"')").each(function(index,element){
						var removeBoard = $(element).prev().clone().attr("class","btn btn-danger btn-xs removeBoard").removeClass("collapsed").removeAttr("data-toggle").removeAttr("data-target");
						$(removeBoard).children().attr("class","glyphicon glyphicon-trash");
						$(element).after(removeBoard);
					});
				}else{
					
				}
				console.log("------------------------ selectBoardDeleteUser end");
			});
}

//게시물과 마커를 지워줌
function deleteBoard(boardNo,groupListNo){
	$.ajax({url:"/board/deleteBoard",
		dataType:"json",
		data: {"boardNo":boardNo},
		type:"post",
	}).done(function(checkMap){
				console.log("------------------------ deleteBoard start");
				
				if(checkMap.check == "SUCCESS"){
					alert("글이 삭제 되었습니다");
					pageNo = 0;
					$("#boardList").html('');
					selectBoardList(groupListNo);
	        		oInfoWnd.setVisible(false);
			 		oMap.clearOverlay();
					oMap.addOverlay(oLabel);
					oMap.addOverlay(oInfoWnd);
					nowLocation();
					selectMarker();
				}else{
					
				}
				console.log("------------------------ deleteBoard end");
			});
}