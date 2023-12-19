package com.huge.Services;

import java.util.HashMap;
import java.util.Map;

import com.huge.Model.User.User;

public class UserInfoService {
    private static UserInfoService instance = new UserInfoService();
    private static Map<String, User> userInfo = new HashMap<>();

    private UserInfoService() {

    }

    public static UserInfoService getInstance() {
        return instance;
    }

    public void addUser(User user) {
        userInfo.put(user.getId(), user);
    }

    public User getUser(String id) {
        return userInfo.get(id);
    }

    public void removeUser(String id) {
        userInfo.remove(id);
    }

}
