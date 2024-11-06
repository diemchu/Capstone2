package com.capstone2.nanum.controller;

import com.capstone2.nanum.database.JoinRoom;
import com.capstone2.nanum.database.Message;
import com.capstone2.nanum.repo.JoinRoomRepository;
import com.capstone2.nanum.repo.MessageRepository;
import com.capstone2.nanum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private JoinRoomRepository joinRoomRepository;

    // API để lấy thông tin chat room và trả về view
    @GetMapping("/get-view/{roomId}")
    public String getView(@PathVariable Long roomId, Model model) {
        Long senderId = UserService.user.getId();
        Long receiverId = null;
        JoinRoom joinRoom = joinRoomRepository.findById(roomId).orElse(null);

        if (joinRoom != null) {
            if (senderId.equals(joinRoom.getUserId())) {
                receiverId = joinRoom.getRoomManagerId();
            } else if (senderId.equals(joinRoom.getRoomManagerId())) {
                receiverId = joinRoom.getUserId();
            }
        }
        System.out.println(senderId);
        System.out.println(receiverId);
        System.out.println(roomId);
        model.addAttribute("senderId", senderId);
        model.addAttribute("receiverId", receiverId);
        model.addAttribute("chatRoomId", roomId);
        return "room/chat1.html";
    }

    // API để lấy lịch sử chat giữa 2 người
    @GetMapping("/history/{roomId}")
    public ResponseEntity<List<Message>> getChatHistory(@PathVariable Long roomId) {
        List<Message> messages = messageRepository.findByRoomId(roomId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // API để gửi tin nhắn và lưu vào database
    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message savedMessage = messageRepository.save(message);
        return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
    }
}
