package com.capstone2.nanum.controller;


import com.capstone2.nanum.database.Post;
import com.capstone2.nanum.database.Room;
import com.capstone2.nanum.services.PostService;
import com.capstone2.nanum.services.RoomService;
import com.capstone2.nanum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("home")
public class HomeController {
    private final RoomService roomService;

    @Autowired
    public HomeController(RoomService roomService) {
        this.roomService = roomService;
    }

    @Autowired
    private PostService postService;

    @GetMapping("/home")
    public  String home(Model model){
        System.out.println(UserService.user.getName());
        model.addAttribute("nickname", UserService.user.getName());
        return  "home/home";
    }
    @GetMapping("/board-view")
    public String board(Model model) {
        List<Room> rooms = roomService.findAllRooms();
//        List<Room> rooms = new ArrayList<>();
//        Long currentUserId = UserService.user.getId();
//        System.out.println(currentUserId);
//        for (int i = 0 ; i< roomList.size();i++){
//            System.out.println(roomList.get(i).getUserId());
//            if(!currentUserId.equals(roomList.get(i).getUserId())){
//                rooms.add(roomList.get(i));
//            }
//        }

        model.addAttribute("rooms", rooms);
        model.addAttribute("nickname", UserService.user.getName());
        model.addAttribute("currentUserId",UserService.user.getId());
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        System.out.println(posts);
        return "board/board";
    }

    @GetMapping("/user-info-view")
    public  String user_info(){
        return  "home/user-info";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        try {
            postService.deletePostById(id);
            return ResponseEntity.ok("Post deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
