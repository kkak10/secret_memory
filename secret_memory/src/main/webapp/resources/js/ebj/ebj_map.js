/**
 * 네이버 map API 이용
 * latitude 가로 x 위도
 * longitude 세로 y 경도
 * oPoint는 반대로 되어있음 (y,x)
 */

var markerX = '';	//위도(x)
var markerY = '';	//경도(y)
var groupListNo = '';	//그룹 리스트 번호
var oneMap = false;	//map 하나로만 쓰이게 한다
var oMap = '';	//지도객체
var oIcon = '';	//아이콘객체
var daumApikey = 'd6eb92f1c2c73af2a1a522b11bda1cbc33609695';
var oPoint = '';	//클릭시 위치 좌표객체
var oTarget = '';	//클릭시 객체 구분
var oLabel = '';	//마우스가 올라갔을때
var oInfoWnd = '';	//마커 클릭시 정보
var oSize = '';		//마커 사이즈
var oOffset = '';	//마커  oOffset
var nowLocationPosition = '';	//현재 위치 좌표
var nowMarker = '';	//현재 위치 마커
var watchX = '';	//watchPosition x좌표
var watchY = '';	////watchPosition y좌표
var watchId = '';


//myLocation 시작
function groupListNoReturn(data_no){
	groupListNo = data_no;
};

if (navigator.geolocation) {
	console.log("-----------------------geolocation 가능");
	navigator.geolocation.getCurrentPosition(successHandler, errorHandler, {
	enableHighAccuracy : true
	});
} else {
	console.log("-----------------------geolocation 불가능");
	alert("브라우저를 업데이트를 해주세요 원활하지 않습니다.");
	loadMap( 37.566535,126.977969 );
}

/* myLocation 성공 */
function successHandler( position ){
	markerX = position.coords.latitude;
	markerY = position.coords.longitude;
	console.log("successHandler : " , markerX, markerY);
	loadMap( markerX, markerY );
};

/* myLocation 실패 */
function errorHandler( error ){
    alert("errorHandler" + "\n" + "code: " + error.code + "\n" + "message: " + error.message + "\n");
};

//myLocation 끝

//watchPosition 시작

if (navigator.geolocation) {
	console.log("-----------------------watchPosition 실행");
	watchId = navigator.geolocation.watchPosition(watchPositionSuccessHandler, watchPositionErrorHandler, {
		maximumAge : 600000,
		timeout : 5000,
		enableHighAccuracy : true
	});
} else {
	console.log("-----------------------watchPosition 불가능");
	console.log("브라우저를 업데이트를 해주세요 원활하지 않습니다.");
}



function watchPositionSuccessHandler( position ){
	
	if($("#groupListNo").html() != undefined){
		
		oMap.removeOverlay(nowMarker);
		
		watchX = position.coords.latitude;
		
		watchY = position.coords.longitude;
		
		nowLocationPosition = new nhn.api.map.LatLng(watchX, watchY);;
		
		console.log(nowLocationPosition);
		console.log("watchPositionSuccessHandler : " , watchX, watchY);
		
		nowMarker.setPoint(nowLocationPosition);
		console.log(nowMarker);
		oMap.addOverlay(nowMarker);
		
		//바꿈
		if($("#mapAlarm").children().hasClass('switch-on')){
			$.ajax({
				url:"/map/selectMarkerCheck",
				dataType:"json",
				type:'post',
				data:{"groupListNo":groupListNo, "markerX":watchX, "markerY":watchY}
			}).done(function(alarmMarkerList){
				var groupName = [];
				var uniqueGroupName = [];
				
				$("#alarmGroupList").html("");
				
				for (var i = 0; i < alarmMarkerList.length; i++) {
					console.log("selectMarkerCheck 데이터 받아옴");
					console.log(alarmMarkerList[i].groupName);
					
					groupName.push(alarmMarkerList[i].groupName);
					
					
				}
				
				$.each(groupName, function(i, el){
					if($.inArray(el, uniqueGroupName) === -1){
						uniqueGroupName.push(el);
					}
				});
				
				for (var i = 0; i < uniqueGroupName.length; i++) {
					$("#alarmGroupList").append(uniqueGroupName[i]);
				}
				
				if (alarmMarkerList != '') {
					alarmWindow();
				}
			});
			console.log("------------------------ selectCategoryAll end");
		};
	} else {
		console.log("map 내용이 없습니다.");
	}
	

}

function watchPositionErrorHandler( error ){
    alert("watchPositionErrorHandler" + "\n" + "code: " + error.code + "\n" + "message: " + error.message + "\n");
};
//watchPosition 끝

/* 현재위치 마커 Start */
function nowLocation(){	
	nowLocationPosition = new nhn.api.map.LatLng(markerX, markerY);
	console.log(nowLocationPosition);
	var nowIcon = new nhn.api.map.Icon("img/marker/nowIcon.png", oSize, oOffset);
	nowMarker = new nhn.api.map.Marker(nowIcon, { title : '현재 위치'});
	nowMarker.setPoint(nowLocationPosition);
	oMap.addOverlay(nowMarker);
}   	
/* 현재위치 마커 End */

//네이버 map 시작
function loadMap( markerX, markerY ){
		console.log("--------loadMap--------" + markerX + "/" +  markerY);
		var StartPoint = new nhn.api.map.LatLng(markerX, markerY);
	    var defaultLevel = 11;
	    oMap = new nhn.api.map.Map(document.getElementById('map'), { 
	                                    point : StartPoint,	//point : Coord 지도 중심점의 좌표
	                                    zoom : defaultLevel,		//zoom : Number 지도의 축척 레벨
	                                    enableWheelZoom : true,		//enableWheelZoom : Boolean 마우스 휠 동작으로 지도를 확대/축소할지 여부
	                                    enableDragPan : true,		//enableDragPan : Boolean 마우스로 끌어서 지도를 이동할지 여부
	                                    enableDblClickZoom : false, //enableDblClickZoom : Boolean 더블클릭으로 지도를 확대할지 여부
	                                    mapMode : 0,				//mapMode : Number 지도 모드(0 : 일반 지도, 1 : 겹침 지도, 2 : 위성 지도)
	                                    //activateTrafficMap : false,	//activateTrafficMap : Boolean 실시간 교통 활성화 여부
	                                    //activateBicycleMap : false,	//activateBicycleMap : Boolean 자전거 지도 활성화 여부
	                                    minMaxLevel : [ 1, 14 ],	 //minMaxLevel : Array 지도의 최소/최대 축척 레벨
	                                    size : new nhn.api.map.Size(802, 480)	//size : Size 지도의 크기         
	                                    //boundary : Array 지도 생성 시 주어진 array 에 있는 점이 모두 보일 수 있도록 지도를 초기화한다.
	            						//boundaryOffset : Number boundary로 지도를 초기화 할 때 지도 전체에서 제외되는 영역의 크기.
	    								//detectCoveredMarker : Boolean 겹쳐 있는 마커를 클릭했을 때 겹친 마커 목록 표시 여부
	   
	   });	   
                
	   //줌 bar
       var oSlider = new nhn.api.map.ZoomControl();
       oMap.addControl(oSlider);
       oSlider.setPosition({
	       top : 10,
	       right : 10
       });
       
	   //일반 or 위성 지도 
	   var oMapTypeBtn = new nhn.api.map.MapTypeBtn();
	   oMap.addControl(oMapTypeBtn);
	   oMapTypeBtn.setPosition({
	       bottom : 10,
	       right : 10
	   });
	   
       	
	   oSize = new nhn.api.map.Size(28, 37);
	   oOffset = new nhn.api.map.Size(14, 37);
	   oIcon = new nhn.api.map.Icon('http://static.naver.com/maps2/icons/pin_spot2.png', oSize, oOffset);
		
	   oInfoWnd = new nhn.api.map.InfoWindow();
	   oInfoWnd.setVisible(false);
	   oMap.addOverlay(oInfoWnd);
	
	   oInfoWnd.setPosition({
		   top : 20,
		   left :20
	   });

	   oLabel = new nhn.api.map.MarkerLabel(); // - 마커 라벨 선언.
	   oMap.addOverlay(oLabel); // - 마커 라벨 지도에 추가. 기본은 라벨이 보이지 않는 상태로 추가됨.
	
	   oInfoWnd.attach('changeVisible', function(oCustomEvent) {
	       if (oCustomEvent.visible) {
	               oLabel.setVisible(false);
	       }
	   });

	
	   oMap.attach('mouseenter', function(oCustomEvent) {
	
	       var oTarget = oCustomEvent.target;
	       // 마커위에 마우스 올라간거면
	       if (oTarget instanceof nhn.api.map.Marker) {
	               var oMarker = oTarget;
	               oLabel.setVisible(true, oMarker); // - 특정 마커를 지정하여 해당 마커의 title을 보여준다.
	       }
	   });
	
	   oMap.attach('mouseleave', function(oCustomEvent) {
	
	       var oTarget = oCustomEvent.target;
	       // 마커위에서 마우스 나간거면
	       if (oTarget instanceof nhn.api.map.Marker) {
	               oLabel.setVisible(false);
	       }
	   });
	   
	   //Click Start
	   oMap.attach('click', function(oCustomEvent) {
	        oPoint = oCustomEvent.point;
	        oTarget = oCustomEvent.target;
	        oInfoWnd.setVisible(false);
	        // 마커 클릭하면
	        if (oTarget instanceof nhn.api.map.Marker) {
	            // 겹침 마커 클릭한거면
	            if (oCustomEvent.clickCoveredMarker) {
	                    return;
	            }
	            // - InfoWindow 에 들어갈 내용은 setContent 로 자유롭게 넣을 수 있습니다. 외부 css를 이용할 수 있으며, 
	            // - 외부 css에 선언된 class를 이용하면 해당 class의 스타일을 바로 적용할 수 있습니다.
	            // - 단, DIV 의 position style 은 absolute 가 되면 안되며, 
	            // - absolute 의 경우 autoPosition 이 동작하지 않습니다. 
	            /*oInfoWnd.setContent('<DIV style="border-top:1px solid; border-bottom:2px groove black; border-left:1px solid; border-right:2px groove black;margin-bottom:1px;color:black;background-color:white; width:auto; height:auto;">'+
                '<span id="markerInfo" style="color: #000000 !important;display: inline-block;font-size: 12px !important;font-weight: bold !important;letter-spacing: -1px !important;white-space: nowrap !important; padding: 2px 5px 2px 2px !important">' + 
	            '<span></div>');*/
	            
	            oInfoWnd.setContent("<div class='spotly _infolay_frame'><div class='_infowindow_content' > <div class='spotly_header'> <div class='sh_flexible'><img id='infoImage' style='width:50px;' /> &nbsp; &nbsp;<strong id='infoTitle' class='sh_h _title nclicks'><strong/> </div> </div> <div class='spotly_container'><div class='spotly_detail'><div class='spotly_flexible'><img id='infoTextImage'/><dl id='infoText' class='default_dl'></dl></div></div><div class='spotly_btnset'><input id='infoDelete' class='btn-danger pull-right' type= 'button'value='Delete' /></div> </div> <div class='spotly_act'> <a href='#' id='infoClose' class='spm spm_spot_close nclicks' title='레이어 닫기'>레이어 닫기</a> </div> <div class='spmh spmh_spot_arrow _arrow'></div> <div class='spotly_oc'></div> </div>");
	            oInfoWnd.setPoint(oTarget.getPoint());
	            oInfoWnd.setPosition({left : 100, top : 200});
	            oInfoWnd.setVisible(true);
	            oInfoWnd.autoPosition();
	            $("#infoDelete").hide();
	            selectBoard(oTarget.getPoint());
	            
	            return;
	        }

	        /* 다음 마커좌표 -> 주소 값 받아오기 End */
	        	MarkerX = oPoint.y;
	        	MarkerY = oPoint.x;

				$("#openModal").trigger("click");
	        	
				
	   		});	//Click END
	   
	   	
		//맵 스타팅포인트 이동
		$("#showAddress").delegate(".moveMap", "click", function(e){
			var movePoint = new nhn.api.map.LatLng($(this).attr("data-markerX"), $(this).attr("data-markerY"));

			oMap.setCenter(movePoint);
			//e.preventDefault();
			console.log($(this).attr("data-markerX"));
			console.log($(this).attr("data-markerY"));

		});
		
		oneMap = false;
		
}
//네이버 map 끝


/* marker map에 뿌려 주는  기능 Start */
function selectMarker(){
	$.ajax({url:"/map/selectMarker",
		type:"post",
		datatype:"json",
		data:{"groupListNo":groupListNo}
	}).done(function(mapListData){
		console.log("--------------------- SelectMarker start");
		console.log(mapListData);
		console.log("●●●●●●●●●●●●",mapListData);
		for(var i = 0; i < mapListData.length; i++){
			var oPoint = new nhn.api.map.LatLng(mapListData[i].markerX, mapListData[i].markerY); 
			console.log(oPoint);
			var oMarker = new nhn.api.map.Marker(createIcon(mapListData[i].categoryNo), { title : mapListData[i].markerNickname });  //마커 생성 
			oMarker.setPoint(oPoint);
			oMap.addOverlay(oMarker); 
		}
		console.log("------------------------ SelectMarker end");
	});
}
/* marker map에 뿌려 주는  기능 End */


/* 다음 주소 검색 -> 좌표 받아오기 Start */
function showAddress(){

	console.log("------------------------ showAddress");
	$("#showAddress").html('');
	var q = $("#mapSerch").val();
	console.log(q);
	$.ajax({url: "http://apis.daum.net/local/geo/addr2coord?apikey=" + daumApikey + "&q=" + q + "&output=json",
			type:"GET",
			dataType:"jsonp",
			jsonp:"callback",
			success:function(daumMapData){
				console.log("------------------------ showAddress start");
				console.log("!!!!!!!!!!!!!!" + daumMapData);
				var mapChannelData = daumMapData.channel;
				var	mapItemData = mapChannelData.item;
	        	for ( var i in mapItemData) {

					console.log(i);
					console.log(mapItemData);
					console.log(mapItemData[i].title);
					mapItemData[i].lat;
					mapItemData[i].lng;
					console.log(mapItemData[i].lat + "/" + mapItemData[i].lng);
					$("#showAddress").append(" <a class='moveMap' href='#' data-markerX=" + mapItemData[i].lat +" data-markerY=" + mapItemData[i].lng + "> " + mapItemData[i].title + "</a> <br />");
					
				}
	        	
	        	console.log("------------------------ showAddress end");
			}
	});
				
}
/* 카테고리 전체를 뿌려줌 */
function selectCategoryAll(){
	$.ajax({
		url:"/map/selectMarker",
		dataType:"json",
		type:'post',
		data:{"groupListNo":$("#groupListNo").html()}
	}).done(function(mapListData){
		oMap.clearOverlay();
		oMap.addOverlay(oLabel);
		oMap.addOverlay(oInfoWnd);
		nowLocation();
		console.log("------------------------ selectCategoryAll start");
		for(var i = 0; i < mapListData.length; i++){
	        	oPoint = new nhn.api.map.LatLng(mapListData[i].markerX, mapListData[i].markerY); 
	        	console.log(oPoint);
	        	console.log(mapListData[i].categoryNo);
	        	oMarker = new nhn.api.map.Marker(createIcon(mapListData[i].categoryNo), { title : mapListData[i].markerNickname });  //마커 생성 
	        	oMarker.setPoint(oPoint);
	        	oMap.addOverlay(oMarker); 
       	}
	});
	console.log("------------------------ selectCategoryAll end");
}

/* 카테고리를 받아서 뿌려줌 */
function selectCategory(categoryNo){
	$.ajax({
		url:"/map/selectCategoryMarker",
		dataType:"json",
		type:'post',
		data:{"groupListNo":$("#groupListNo").html(),"categoryNo":categoryNo}
	}).done(function(mapListData){
		console.log("------------------------ selectCategory start");
		oMap.clearOverlay();
		oMap.addOverlay(oLabel);
		oMap.addOverlay(oInfoWnd);
		nowLocation();
		
		for(var i = 0; i < mapListData.length; i++){
	        	oPoint = new nhn.api.map.LatLng(mapListData[i].markerX, mapListData[i].markerY); 
	        	console.log(oPoint);
	        	oMarker = new nhn.api.map.Marker(createIcon(parseInt(categoryNo)), { title : mapListData[i].markerNickname });  //마커 생성 
	        	oMarker.setPoint(oPoint);
	        	console.log(oMarker);
	        	oMap.addOverlay(oMarker); 
       	}
		
	});
	console.log("------------------------ selectCategory end");
}

/* 마커 생성 */
function createMarker(){
	/* 다음 마커좌표 -> 주소 값 받아오기 */
	$.ajax({url: "http://apis.daum.net/local/geo/coord2addr?apikey=" + daumApikey + "&longitude=" + oPoint.x  + "&latitude=" + oPoint.y + "&format=fullname&output=json",
		type:"GET",
		dataType:"jsonp",
		jsonp:"callback",
		success:function(mapAddress){
			console.log("------------------------ markerAddress start");
			console.log(mapAddress.fullName);
			var markerAddressName = mapAddress.fullName;
			console.log("markerAddressName" + markerAddressName);
			


			/* map 클릭 이벤트 발생 시 마커 찍히는 곳에 DB 삽입 기능 Start */
		   	$.ajax({url:"/map/createMarker",
		   			type:"post",
		   			datatype:"json",
		   			data:{"markerX":oPoint.y,"markerY":oPoint.x,"groupListNo":$("#groupListNo").html(),"markerAddress":markerAddressName,"categoryNo":$(".form-control option:selected").val()}
		   			}).done(function(checkMap){
		   				
		   				console.log("createMarker",oPoint);
		   				
		   				if ($("#mapBoardText").val() != '') {
		   					boardInsertboolean = true;
		   				}
		   				
		   				console.log("------------------------ createMarker start");
		   	        	console.log(checkMap);
		   	        	
		   	        	 if(checkMap.check === "SUCCESS"){
		   	        		console.log("createMarker 성공!!!");
		   				 } else {
		   					console.log("createMarker 실패!!!");
		   				 }
		   	        	console.log("------------------------ createMarker end");
		   	        	
		   	        	//category별 마커생성
		   	        	var categoryNo = parseInt($(".form-control option:selected").val());
			
			        	var oMarker = new nhn.api.map.Marker(createIcon(categoryNo), { title : '마커 : ' + oPoint.toString() });
			  	        oMarker.setPoint(oPoint);
			  	        oMap.addOverlay(oMarker);
			  	        
		   	 });    				
		     /* map 클릭 이벤트 발생 시 마커 찍히는 곳에 DB 삽입 기능 End */
        	console.log("------------------------ markerAddress end");
		}
	});
}

/* 카테고리별 마커 아이콘 생성 */
function createIcon(categoryNo){
	
	console.log("------------------------ createIcon start");
	console.log(categoryNo);
	var categoryIcon = '';
	
	switch (categoryNo) {
	
	case 1:
		console.log("1번 음식점");
		categoryIcon = new nhn.api.map.Icon("img/marker/category_01.png", oSize, oOffset);
		break;
	case 2:
		console.log("2번 숙박");
		categoryIcon = new nhn.api.map.Icon("img/marker/category_02.png", oSize, oOffset);
		break;
	case 3:
		console.log("3번 까페");
		categoryIcon = new nhn.api.map.Icon("img/marker/category_03.png", oSize, oOffset);
		break;
	case 4:
		console.log("4번 병원");
		categoryIcon = new nhn.api.map.Icon("img/marker/category_04.png", oSize, oOffset);
		break;	
	case 5:
		console.log("5번 기타");
		categoryIcon = new nhn.api.map.Icon("img/marker/category_05.png", oSize, oOffset);
		break;
	}
	
	return categoryIcon;
	console.log("------------------------ createIcon end");
}

// 100m 안에 들어가면 알람창을 뜨게함.
function alarmWindow(){
	var width = document.documentElement.clientWidth;
	var height = document.documentElement.clientHeight;

	var centerW = width / 2 - 150; 
	var centerH = height / 2 - 100; 

	$("#alarmWindow").css({"position":"absolute","top":centerH,"left":centerW,"z-index":"2000"});
	$("#alarmWindow").fadeIn("slow");
	$("#alarmBackground").css({"background-color":"black","opacity":0.8,"display":"block"});
}

//알람창 닫기를 눌면 알람창을 닫아줌.
function alarmWindowClose(){
	$("#alarmWindow").fadeOut("fast");
	$("#alarmBackground").css({"background-color":"inherit","display":"none"});
}

//삭제할때 자기가 쓴 글인지 판별해서 자기가 쓴 글이면 삭제버튼이 나오게함
function selectMapMarkerDeleteUser(){
	$.ajax({url:"/map/selectMapMarkerDeleteUser",
		data:{"mapMarkerUser":$("#infoId").html()},
		dataType:"json",
		type:"post",
	}).done(function(checkMap){
				console.log("------------------------ selectMapMarkerDeleteUser start");
				if(checkMap.check == "SUCCESS"){
					$("#infoDelete").show();
					console.log("selectMapMarkerDeleteUser SUCCESS");
				}else{
					$("#infoDelete").hide();
				}
				console.log("------------------------ selectMapMarkerDeleteUser end");
			});
}
