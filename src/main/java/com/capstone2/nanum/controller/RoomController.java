package com.capstone2.nanum.controller;

import com.capstone2.nanum.database.Room;
import com.capstone2.nanum.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/create-room-view")
    public String create() {
        return "room/create-room";
    }
    @GetMapping("/join-room-view")
    public String join_room() {
        return "room/join-room";
    }

    @GetMapping("/taxiroom-list-view")
    public String taxiroom() {
        return "room/taxiroom-list";
    }
    @GetMapping("/deliveryroom-view")
    public String deliveryroom() {
        return "room/deliveryroom-list";
    }
    @GetMapping("/jointroom-list-view")
    public String jointroom() {
        return "room/jointroom-list";
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
        return "redirect:/home/board-view";
    }


    //select taxi room
    @GetMapping("/select-taxi-room")
    public  String selectTaxiRoom(@RequestParam("category") String category  ,Model model){
        List<Room> rooms  = roomService.findByCategory(category);
        model.addAttribute("rooms",rooms);
        return "board/board";
    }

    //select delivery
    @GetMapping("/select-delivery-room")
    public  String selectDeliveryRoom(@RequestParam("category") String category  ,Model model){
        List<Room> rooms  = roomService.findByCategory(category);
        model.addAttribute("rooms",rooms);
        return "board/board";
    }


    //select taxi room
    @GetMapping("/select-group-room")
    public  String selectGroupRoom(@RequestParam("category") String category  ,Model model){
        List<Room> rooms  = roomService.findByCategory(category);
        model.addAttribute("rooms",rooms);
        return "board/board";
    }
}
