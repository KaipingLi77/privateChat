package com.huge.Model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private int id;
    private String NickName;
    private String WelcomeMessage;
    private String LeavingMessage;
    private int iconId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNicKName() {
        return NickName;
    }

    public void setFirstName(String name) {
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
