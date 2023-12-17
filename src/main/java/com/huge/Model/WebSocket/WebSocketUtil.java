package com.huge.Model.WebSocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.websocket.Session;

public class WebSocketUtil {
    private static final Map<String, Session> Online_Session = new ConcurrentHashMap<String, Session>();

    public static void regist(String nickname, Session session) {
        Online_Session.put(nickname, session);
    }

    public static void SendMessage(Session session, String msg) {
        try {
            if (session == null) {
                return;
            }
            synchronized (Online_Session) {
                session.getBasicRemote().sendText(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendMessageToAll(String message) {
        Online_Session.forEach((k, v) -> SendMessage(v, message));
    }

    public static void leave(String nickname) {
        Online_Session.remove(nickname);
    }

    public static List<String> getOnlineUser() {
        return new ArrayList<String>(Online_Session.keySet());
    }

}
