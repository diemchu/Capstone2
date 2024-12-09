package com.capstone2.nanum.services;

import com.capstone2.nanum.database.Room;
import com.capstone2.nanum.repo.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }
    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }
    public List<Room> findByCategory(String category){
        return roomRepository.findByCategory(category);
    }
    @Transactional
    public boolean deleteRoom(Long roomId, Long userId) {
        if (roomRepository.existsById(roomId)) {
            roomRepository.deleteByIdAndUserId(roomId, userId);
            return true;
        }
        return false;
    }
    public Page<Room> getRooms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // page bắt đầu từ 0
        return roomRepository.findAll(pageable);
    }
}
