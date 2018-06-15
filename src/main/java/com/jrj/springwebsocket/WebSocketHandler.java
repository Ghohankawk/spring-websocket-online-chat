package com.jrj.springwebsocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class WebSocketHandler extends AbstractWebSocketHandler {

	//连接队列
	public static final List<WebSocketSession> sessions = new ArrayList();

	@Override
	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(webSocketSession, status);
		sessions.remove(webSocketSession);
		StringBuilder sessionIds = new StringBuilder("");
		for (WebSocketSession session : sessions) {
				sessionIds.append(" ").append(session.getId()).append(" ");
		}
		for (WebSocketSession session : sessions) {
			session.sendMessage(new TextMessage("用户" + webSocketSession.getId() + "离开了聊天室"));
			session.sendMessage(new TextMessage("当前聊天室还剩下"+sessions.size()+"人，有id为【" + sessionIds + "】的用户"));
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
		//加入连接队列
		sessions.add(webSocketSession);

		webSocketSession.sendMessage(new TextMessage("你与服务器连接成功了！你的sessionID为【" + webSocketSession.getId() + "】"));

		StringBuilder sessionIds = new StringBuilder("");
		for (WebSocketSession session : sessions) {
			session.sendMessage(new TextMessage("用户" + webSocketSession.getId() + "已加入聊天室"));
			sessionIds.append(" " + session.getId() + " ");
		}

		System.out.println("一个客户端连接上了服务器！webSocketSessionId为【" + webSocketSession.getId() + "】, 当前服务器session队列中有:【" + sessionIds + "】");

		for (WebSocketSession session : sessions) {
			session.sendMessage(new TextMessage("当前聊天室一共有"+sessions.size()+"人，有id为【" + sessionIds + "】的用户"));
		}
	}

	@Override
	public void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) throws Exception {
		String payload = message.getPayload();
		System.out.println("server received:" + payload);
		if (payload != null) {
			System.out.println("服务器收到来自sessionId【" + webSocketSession.getId() + "】的信息：【" + payload + "】");
			//如果是管理员说的，hank
			if(payload.contains("hank")){
				webSocketSession.sendMessage(new TextMessage("管理员说: 【" + payload + "】"));
			}else {
				for (WebSocketSession session : sessions) {
					if(session.getId().equals(webSocketSession.getId())){
						session.sendMessage(new TextMessage("我【" + webSocketSession.getId() + "】说："+payload));
					}else {
						session.sendMessage(new TextMessage("用户【" + webSocketSession.getId() + "】说："+payload));
					}

				}
			}
		}

	}

}
