package com.capstone2.nanum.controller;

import com.capstone2.nanum.database.Room;
import com.capstone2.nanum.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("room")
public class TaxiRoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/create-room-view")
    public String create() {
        return "room/create-room";
    }

    @PostMapping("/create-room")
    public String createRoom(
            @RequestParam("room-name") String roomName,
            @RequestParam("category") String category,
            @RequestParam("max-people") int maxPeople,
            @RequestParam("description") String description,
            @RequestParam(value = "departure", required = false) String departure,
            @RequestParam(value = "destination", required = false) String destination,
            @RequestParam(value = "delivery-location", required = false) String deliveryLocation,
            @RequestParam(value = "product", required = false) String product,
            @RequestParam("date") String date) {


        System.out.println("hello");
        Room room = new Room();
        room.setRoomName(roomName);
        room.setCategory(category);
        room.setMaxPeople(maxPeople);
        room.setDescription(description);
        room.setDeparture(departure);
        room.setDestination(destination);
        room.setDeliveryLocation(deliveryLocation);
        room.setProduct(product);
        room.setDate(date);
        roomService.saveRoom(room);
        return "home/home";
    }
}
