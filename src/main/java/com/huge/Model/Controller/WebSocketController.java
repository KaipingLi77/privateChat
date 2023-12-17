package com.huge.Model.Controller;

import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.huge.Model.WebSocket.WebSocketUtil;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint(value = "/chatroom/{nickName}")
public class WebSocketController {

    @OnOpen
    public void OnOpen(@PathParam(value = "nickName") String NickName, Session session) {
        WebSocketUtil.regist(NickName, session);
        JsonObject msg = new JsonObject();
        msg.addProperty("type", "system");
        msg.addProperty("content", NickName + " is Coming");
        broadcastOnlineUsers(NickName);
        WebSocketUtil.sendMessageToAll(msg.toString());

    }

    @OnMessage
    public void OnMessage(@PathParam(value = "nickName") String NickName, String message) {

        WebSocketUtil.sendMessageToAll(message.toString());
    }

    @OnError
    public void OnError(Session session, Throwable t) {
        try {
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        t.printStackTrace();
    }

    @OnClose
    public void OnClose(@PathParam(value = "nickName") String NickName, Session session) {
        JsonObject msg = new JsonObject();
        msg.addProperty("type", "system");
        msg.addProperty("content", NickName + " is Leave");
        WebSocketUtil.leave(NickName);
        WebSocketUtil.sendMessageToAll(msg.toString());
    }

    private void broadcastOnlineUsers(String nickname) {
        JsonObject message = new JsonObject();
        message.addProperty("type", "onlineUsers");
        JsonArray onlineUser = new JsonArray();
        for (String name : WebSocketUtil.getOnlineUser()) {
            onlineUser.add(name);
        }

        message.add("OnlineUser", onlineUser);

        // 将在线用户信息广播给所有客户端
        WebSocketUtil.sendMessageToAll(message.toString());
    }

}
