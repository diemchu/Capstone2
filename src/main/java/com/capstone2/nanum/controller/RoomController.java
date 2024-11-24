package com.capstone2.nanum.controller;

import com.capstone2.nanum.database.JoinRoom;
import com.capstone2.nanum.database.Room;
import com.capstone2.nanum.services.JoinRoomService;
import com.capstone2.nanum.services.RoomService;
import com.capstone2.nanum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private  JoinRoomService joinRoomService;


    @GetMapping("/join-room-list-view")
    public String join_rooms_list(Model model) {
        List<Room> roomList = roomService.findAllRooms();
        List<Room> rooms = new ArrayList<>();
        Long currentUserId = UserService.user.getId();
        List<JoinRoom> joinRoomList = joinRoomService.findUserId(currentUserId);
        System.out.println(joinRoomList);

        for (int i = 0 ; i< roomList.size();i++){
            if(!currentUserId.equals(roomList.get(i).getUserId())){
                rooms.add(roomList.get(i));
            }
        }
        for (int i = 0; i< rooms.size() ; i++){
            for (int j = 0 ; j< joinRoomList.size();j++){
                if(rooms.get(i).getId().equals(joinRoomList.get(j).getRoomId())){
                    rooms.remove(i);
                }
            }
        }
        model.addAttribute("rooms", rooms);
        model.addAttribute("nickname", UserService.user.getName());
        model.addAttribute("currentUserId",UserService.user.getId());
        return "room/join-room-list";
    }


    @GetMapping("/create-room-view")
    public String create() {
        return "room/create-room";
    }
    @GetMapping("/join-room-view")
    public String join_room(Model model) {
        List<Room> roomList = roomService.findAllRooms();
        List<Room> rooms = new ArrayList<>();
        Long currentUserId = UserService.user.getId();
        List<JoinRoom> joinRoomList = joinRoomService.findUserId(currentUserId);

//        for (int i = 0 ; i< roomList.size();i++){
//            if(currentUserId.equals(roomList.get(i).getUserId())){
//                rooms.add(roomList.get(i));
//            }
//        }
        for (int i = 0; i< roomList.size() ; i++){
            for (int j = 0 ; j< joinRoomList.size();j++){
                if(roomList.get(i).getId().equals(joinRoomList.get(j).getRoomId())){
                    rooms.add(roomList.get(i));
                }
            }
            if(currentUserId.equals(roomList.get(i).getUserId())){
                rooms.add(roomList.get(i));
            }
        }
        model.addAttribute("rooms",rooms);
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
        room.setUserId(UserService.user.getId());
        roomService.saveRoom(room);
        return "redirect:/room/join-room-view";
    }


    //select taxi room
    @GetMapping("/select-taxi-room")
    public  String selectTaxiRoom(@RequestParam("category") String category  ,Model model){
        List<Room> rooms  = roomService.findByCategory(category);
        model.addAttribute("category",category);
        model.addAttribute("rooms",rooms);
        return "room/join-room-list";
    }

    //select delivery
    @GetMapping("/select-delivery-room")
    public  String selectDeliveryRoom(@RequestParam("category") String category  ,Model model){
        List<Room> rooms  = roomService.findByCategory(category);
        model.addAttribute("category",category);
        model.addAttribute("rooms",rooms);
        return "room/join-room-list";

    }


    //select taxi room
    @GetMapping("/select-group-room")
    public  String selectGroupRoom(@RequestParam("category") String category  ,Model model){
        List<Room> rooms  = roomService.findByCategory(category);
        model.addAttribute("category",category);
        model.addAttribute("rooms",rooms);
        return "room/join-room-list";
    }


    @DeleteMapping("/delete-room")
    public ResponseEntity<Map<String, Object>> deleteRoom(@RequestParam("roomId") Long roomId) {
        Long currentUerId = UserService.user.getId();
        boolean deleted = roomService.deleteRoom(roomId,currentUerId);
        if (deleted == false){
            deleted = joinRoomService.deleteRoom(roomId,currentUerId);
        }
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("success", true);
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
