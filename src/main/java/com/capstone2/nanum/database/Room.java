package com.capstone2.nanum.database;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rooms")
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;
    private String category;
    private int maxPeople;
    private String description;
    private String departure;
    private String destination;
    private String deliveryLocation;
    private String product;
    private String date;

}
