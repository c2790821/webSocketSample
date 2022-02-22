package com.example.chatroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homePageController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/chat")
    public String chat(){
        return "chat";
    }
}
