package com.capstone2.nanum.controller;

import com.capstone2.nanum.database.Message;
import com.capstone2.nanum.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) {
        return messageRepository.save(message);
    }

    @GetMapping("/history/{senderId}/{receiverId}")
    public List<Message> getChatHistory(@PathVariable Long senderId, @PathVariable Long receiverId) {
        return messageRepository.findBySenderIdAndReceiverId(senderId, receiverId);
    }
}
