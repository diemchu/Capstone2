package com.capstone2.nanum.controller;

import com.capstone2.nanum.services.JoinRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class JoinRoomController {

    @Autowired
    private JoinRoomService joinRoomService;

    @PostMapping("/join-room")
    public ResponseEntity<?> joinRoom(@RequestBody Map<String, Object> payload) {
        Long roomId = Long.parseLong((String) payload.get("roomId"));
        Long userId = Long.parseLong((String) payload.get("userId"));
        String roomName = (String) payload.get("roomName");
        boolean success = joinRoomService.joinRoom(roomId, userId, roomName);
        if (success) {
            return ResponseEntity.ok(Map.of("success", true));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false));
        }
    }
}
