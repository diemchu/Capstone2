package com.capstone2.nanum.repo;

import com.capstone2.nanum.database.JoinRoom;
import com.capstone2.nanum.database.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  JoinRoomRepository  extends JpaRepository< JoinRoom,Long> {
    List<JoinRoom> findByUserId(Long userId);
    JoinRoom findById(long id);
    void deleteByIdAndUserId(Long roomId, Long userId);
}
