package com.capstone2.nanum.repo;
import com.capstone2.nanum.database.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByCategory(String category);
}