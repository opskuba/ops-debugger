package com.opskuba.debugger.websocket;

import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 跟踪用户协议
 * @author albert
 *
 */
@ServerEndpoint("/protocol")
public class ProtocolTracerHandler extends TextWebSocketHandler {

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		// ...
		
		session.getRemoteAddress();
	}

}
