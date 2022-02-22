package com.example.chatroom.controller;

import com.example.chatroom.model.ChatClientModel;
import com.example.chatroom.model.Message;
import com.example.chatroom.model.OutputMessage;
import com.example.chatroom.model.ServerResponseModel;
import com.example.chatroom.service.MsgTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Controller
public class ChatRoomController {
    private static final Logger LOG = LoggerFactory.getLogger(ChatRoomController.class);

    @MessageMapping("/messageControl")
    @SendTo("/topic/getResponse")
    public ServerResponseModel said(@RequestBody ChatClientModel chatClientModel) {


        LOG.info("收到調用:" + chatClientModel.getClientName());
//        Thread.sleep(3000);
        return new ServerResponseModel("welcome, " + chatClientModel.getClientName());
    }

    @MessageMapping("/chat")
    @SendTo(MsgTemplate.BROADCAST_DESTINATION)
    public OutputMessage send(Message message) throws Exception {
        final String time = new Date().toString();
        //LOG.info("");
        return new OutputMessage(time, message);
    }

}
