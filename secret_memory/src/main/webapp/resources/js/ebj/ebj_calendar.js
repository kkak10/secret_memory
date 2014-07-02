var thisDate = '';	//클릭한 곳의 날짜를 받아주는 변수

/* 달력 등록 기능 */
function insertCalendar(calendarText, thisDate){
	var color = $(".colpick_hex_field").children().eq(1).val();
	
	$.ajax({
		url:"/calendar/insertCalendar",
		type: "post",
		data:{"groupListNo":$("#groupListNo").html(), "calendarYear":Number(thisDate[0]), "calendarMonth":Number(thisDate[1])-1,"calendarDay":Number(thisDate[2]),"calendarText":calendarText,"calendarColor":"#"+color},
		dataType:"json"
	}).done(function(checkMap){
		console.log("------------------------ insertCalendar start");
		if (checkMap.check == "SUCCESS") {
			alert("입력되었습니다.");
		} else {
			alert("입력에 실패 하였습니다.");
		}
		$("#calendar").html('');
		showCalendar($("#groupListNo").html());
		$("#calendarBoradText").val('');
		console.log("------------------------ insertCalendar end");
	});
	
};

/* 달력 정보를 받아서 보여주는 기능 */
function showCalendar(groupListNo){
	$.ajax({ url : "/calendar/selectCalendar",
		 type : "post",
		 dataType : "json",
		 data : {"groupListNo":groupListNo}
		}).done(function(calendarList){
			console.log("------------------------ showCalendar start");
			var calendar = calendarList;
		
			var arr = [];
			for (var i = 0 ; i< calendar.length ; i++){

				console.log(calendar[i].calendarColor);
				arr.push(
					{title : calendar[i].calendarText ,start : new Date(Number(calendar[i].calendarYear), Number(calendar[i].calendarMonth), Number(calendar[i].calendarDay)),backgroundColor : calendar[i].calendarColor ,borderColor : calendar[i].calendarColor}
					);
			};
			console.log(calendar);
		
			$('#calendar').fullCalendar({
				header : {
					left : 'prev, next, today',
					center : 'title',
					right : 'month'
				},
				editable : false,
				events : arr,
				  dayClick: function() {
					  thisDate = $(this).attr('data-date').split('-');
					  $("#test").html('');
					  $("#test").html("<span style='font-size:24px;'>" + thisDate + "</span>");
					  $("#modalBtn").trigger('click');
	   				 }
			});
			console.log("------------------------ showCalendar end");
		});
}