package com.capstone2.nanum.repo;
import com.capstone2.nanum.database.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}