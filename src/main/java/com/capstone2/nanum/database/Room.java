package com.capstone2.nanum.database;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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
    private Long userId;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "room_users",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @ToString.Exclude
    private List<User> joinList = new ArrayList<>();

}
