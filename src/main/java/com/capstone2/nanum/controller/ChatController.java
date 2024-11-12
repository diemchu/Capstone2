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

    @GetMapping("/chat-view")
    public String chatView(@RequestParam("roomId") Long roomId, Model model) {
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
        if (messages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(messages);
        }

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {

        System.out.println(message.getMessageText());
        System.out.println(message.getSenderId());
        System.out.println(message.getReceiverId());
        Message savedMessage = messageRepository.save(message);
        return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);

//        try {
//            // Kiểm tra dữ liệu đầu vào
//            if (message.getRoomId() == null || message.getSenderId() == null || message.getMessageText().isEmpty()) {
//                return new ResponseEntity<>("Invalid message data", HttpStatus.BAD_REQUEST);
//            }
//
//            // Lưu tin nhắn vào database
//            Message savedMessage = messageRepository.save(message);
//            return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Error saving message", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }
}
