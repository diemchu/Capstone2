package com.capstone2.nanum.database;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

//import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long senderId;
    private Long receiverId;
    private Long roomId;
    @JsonProperty("text")
    private String messageText;
    private LocalDateTime timestamp = LocalDateTime.now();
    private boolean isRead = false;

    // Getters and setters
}
