package com.capstone2.nanum.services;

import com.capstone2.nanum.database.JoinRoom;
import com.capstone2.nanum.database.Room;
import com.capstone2.nanum.database.User;
import com.capstone2.nanum.repo.JoinRoomRepository;
import com.capstone2.nanum.repo.RoomRepository;
import com.capstone2.nanum.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JoinRoomService {

    @Autowired
    private JoinRoomRepository joinRoomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  RoomRepository roomRepository;

    public boolean joinRoom(Long roomId, Long userId,Long roomManagerId, String roomName) {
        try {
            JoinRoom joinRoom = new JoinRoom();
            joinRoom.setUserId(userId);
            joinRoom.setRoomId(roomId);
            joinRoom.setRoomManagerId(roomManagerId);
            joinRoom.setRoomName(roomName);

            Optional<User> optionalUser = userRepository.findById(userId);
            Optional<Room> optionalRoom =  roomRepository.findById(roomId);
            if (optionalUser.isPresent() && optionalRoom.isPresent()) {
                User user = optionalUser.get();
                Room room = optionalRoom.get();
                if (!room.getJoinList().contains(user)) {
                    room.getJoinList().add(user);
                    roomRepository.save(room);
                }
                System.out.println(room.getJoinList());
            }
            joinRoomRepository.save(joinRoom);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public List<JoinRoom> findUserId(Long userId){
        return  joinRoomRepository.findByUserId(userId) == null ? null : joinRoomRepository.findByUserId(userId);
    }
}
