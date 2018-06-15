<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
	<script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.js"></script>
	<script src="http://cdn.bootcss.com/sockjs-client/1.1.2/sockjs.js"></script>
</head>
<body>
<h1>
	Hello world!  当前时间---${serverTime}
</h1>

<input type="text" id="text">
<button onclick="send()">发送消息</button><br>
<div id="message"></div>
</body>

<script type="text/javascript">
	var websocket = null;
	/* if('WebSocket' in window) {
		websocket = new WebSocket("ws://localhost:8080/springwebsocket/websocket");
	} else {
		alert('当前浏览器不支持websocket!');
		websocket = new SockJS("http://localhost:8080/springwebsocket/websocket");
	} */
	websocket = new SockJS("http://localhost:8080/springwebsocket/websocket");
	
	websocket.onerror = function() {
		setMessage("websocket连接出错");
	}
	
	websocket.onopen= function() {
		setMessage("websocket连接成功！");
	}
	
	websocket.onmessage= function(event) {
		setMessage(event.data);
	}
	
	websocket.onclose= function() {
		close();
	}
	
	function setMessage(text) {
		$("#message").append(text).append("<br>");
	}
	
	function close() {
		websocket.close();
	}
	
	function send() {
		var msg = $("#text").val();
		websocket.send(msg);
	}
</script>
</html>