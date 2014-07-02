var fs = require('fs');
var server = require('http').createServer(function(req,res){
	fs.readFile('http://localhost:8080/resources/index.html',function(error,data){
		res.writeHead(200,{'Content-Type':'text/html'});
		res.end(data);
	});

}).listen(4500,function(){
	console.log('Server Running Start');
});

var io = require('socket.io').listen(server);

io.set('log level',2);

//소켓 서버 이벤트를 연결합니다.
io.sockets.on('connection',function(socket){
	socket.on("join",function(joinJson){
		socket.get("room",function(error,room){
			console.log("my room is ",room);
			if(room == null){
			console.log("User's Nickname : " + joinJson.nick_name);
			console.log("User's GroupListRoomNo : " + joinJson.groupListNo);
			socket.join(joinJson.groupListNo);
			socket.set("nick_name",joinJson.nick_name);
			socket.set("room",joinJson.groupListNo);
			io.sockets.in(joinJson.groupListNo).emit("firstJoin",joinJson.nick_name);
			console.log("firstJoin nick :",joinJson.nick_name);
			};
		});
	});
	
	//메시지 전송 이벤트
	socket.on('eventMessage',function(eventMessage){
	socket.get("room",function(error,room){
			if(room !== null){
				socket.get("nick_name",function(error,nick_name){
					var line = nick_name + " - " + eventMessage.eventMessage;
					console.log("end message" ,line );
					io.sockets.in(room).emit("line",line);
				});
			};
		});
	});
	
	//그룹 채팅 나감
	socket.on('leave',function(){
		socket.get("room",function(error,room){
			socket.get("nick_name",function(error,nick_name){
				io.sockets.in(room).emit("leave", nick_name);			
			});
			socket.leave(room);
			console.log(room,"방에서 나가짐.");
			socket.set("room",null);
		});
	});
	
});

//socket 서버에서 나감
io.sockets.on('disconnect',function(){
	console.log("Client out");
});
	