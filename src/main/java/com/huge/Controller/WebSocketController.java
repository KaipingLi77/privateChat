package com.huge.Controller;

import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.huge.Model.User.User;
import com.huge.Model.WebSocket.WebSocketUtil;
import com.huge.Services.UserInfoService;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint(value = "/chatroom/{id}")
public class WebSocketController {
    private UserInfoService userInfoService = UserInfoService.getInstance();

    @OnOpen
    public void OnOpen(@PathParam(value = "id") String id, Session session) {
        WebSocketUtil.regist(id, session);
        User user = userInfoService.getUser(id);
        JsonObject msg = new JsonObject();
        msg.addProperty("type", "system");
        msg.addProperty("content", user.getNicKName() + ": " + user.getWelcomeMessage());
        broadcastOnlineUsers();
        WebSocketUtil.sendMessageToAll(msg.toString());

    }

    @OnMessage
    public void OnMessage(@PathParam(value = "id") String id, String message) {
        JsonObject msg = new JsonObject();
        User user = userInfoService.getUser(id);
        msg.addProperty("id", user.getId());
        msg.addProperty("name", user.getNicKName());
        msg.addProperty("iconId", user.getIconId());
        msg.addProperty("content", message);
        WebSocketUtil.sendMessageToAll(msg.toString());
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
    public void OnClose(@PathParam(value = "id") String id, Session session) {
        User user = userInfoService.getUser(id);
        JsonObject msg = new JsonObject();
        msg.addProperty("type", "system");
        msg.addProperty("content", user.getNicKName() + ": " + user.LeavingMessage());
        WebSocketUtil.leave(id);
        WebSocketUtil.sendMessageToAll(msg.toString());
    }

    private void broadcastOnlineUsers() {
        JsonObject message = new JsonObject();
        message.addProperty("type", "onlineUsers");
        JsonArray onlineUser = new JsonArray();
        for (String id : WebSocketUtil.getOnlineUser()) {
            JsonObject userInfo = new JsonObject();
            User user = userInfoService.getUser(id);
            userInfo.addProperty("name", user.getNicKName());
            userInfo.addProperty("iconId", user.getIconId());
            onlineUser.add(userInfo);
        }

        message.add("OnlineUser", onlineUser);

        WebSocketUtil.sendMessageToAll(message.toString());
    }

}
