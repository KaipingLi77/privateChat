package com.huge.Model.User;

import java.util.Random;
import java.util.UUID;

public class User {
    private String id;
    private String NickName;
    private String WelcomeMessage;
    private String LeavingMessage;
    private int iconId;

    public User() {

    }

    public User(String NickName, String WelcomeMessage, String quitMsg) {
        this.id = UUID.randomUUID().toString();
        this.NickName = NickName;
        this.WelcomeMessage = WelcomeMessage;
        this.LeavingMessage = quitMsg;
        this.iconId = new Random().nextInt(6) + 1;
    }

    public String getId() {
        return id;
    }

    public String getNicKName() {
        return NickName;
    }

    public void setNickName(String name) {
        this.NickName = name;
    }

    public String getWelcomeMessage() {
        return WelcomeMessage;
    }

    public void setWelcomeMessage(String WelcomeMessage) {
        this.WelcomeMessage = WelcomeMessage;
    }

    public String LeavingMessage() {
        return LeavingMessage;
    }

    public void setLeavingMessage(String LeavingMessage) {
        this.LeavingMessage = LeavingMessage;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int id) {
        this.iconId = id;
    }

}
