/**
 * 
 */
/*<div id='replyList' class='col-xs-10 col-md-11'><div style='float:left'><a href='#' style='clear:both'><img src='replyUserImage' /></a></div></div>*/

function selectReplyList(boardNo,insertReplyBtn){
	$.ajax({
		url:"/reply/selectReply",
		data:{"boardNo" : boardNo},
		dataType:"json",
		type:"post",
		success:function(replyList){
			console.log(replyList);
			$(".replyList[data-boardNo="+boardNo+"]").html('');
			console.log($(".replyList[data-boardNo="+boardNo+"]").attr("data-boardNo"));
			for(var i = 0 ; i < replyList.length ; i++){
				$(".replyList[data-boardNo="+boardNo+"]").append("<div><img src='' /><strong>" +replyList[i].replyNickname + "</strong> : "  +  replyList[i].replyText + "</div>");
			}
		}
	});
};

function insertReply(boardNo,insertReplyBtn,replyList){
	$.ajax({
		url:"/reply/insertReply",
		data:{"boardNo":boardNo,"replyText":insertReplyBtn.parents('span').prev().val()},
		dataType:"json",
		type:"post",
		success:function(checkMap){
			if(checkMap.check == "SUCCESS"){
				selectReplyList(boardNo);
				insertReplyBtn.parents('span').prev().val('');
				alert("등록 되었습니다.");	
			}
		}
	});
}