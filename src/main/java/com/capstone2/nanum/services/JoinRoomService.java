package com.capstone2.nanum.services;

import com.capstone2.nanum.database.JoinRoom;
import com.capstone2.nanum.repo.JoinRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinRoomService {

    @Autowired
    private JoinRoomRepository joinRoomRepository;

    public boolean joinRoom(Long roomId,Long userId) {
        try {
            JoinRoom joinRoom = new JoinRoom();
            joinRoom.setUserId(userId);
            joinRoom.setRoomId(roomId);
            joinRoomRepository.save(joinRoom);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
