package com.capstone2.nanum.repo;
import com.capstone2.nanum.database.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByCategory(String category);
    void deleteByIdAndUserId(Long roomId, Long userId);
    Page<Room> findAll(Pageable pageable);
}