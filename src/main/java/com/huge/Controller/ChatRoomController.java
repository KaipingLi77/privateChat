package com.huge.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huge.Model.User.User;
import com.huge.Services.UserInfoService;

@Controller
@RequestMapping("/chatroom")
public class ChatRoomController {
    private UserInfoService uService = UserInfoService.getInstance();

    @PostMapping("/chat")
    public String greeting(@RequestParam(name = "nickName", required = true) String name,
            @RequestParam(name = "welcomeMsg", required = false) String welcomeMsg,
            @RequestParam(name = "quitMsg", required = false) String quitMsg,
            Model model) {
        User user = new User(name, welcomeMsg, quitMsg);
        uService.addUser(user);
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/")
    public String genUser() {
        return "user";
    }

}
