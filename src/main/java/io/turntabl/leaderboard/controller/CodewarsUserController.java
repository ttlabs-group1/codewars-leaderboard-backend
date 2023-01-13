package io.turntabl.leaderboard.controller;


import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.dto.ResponseDTO;
import io.turntabl.leaderboard.service.AddCodewarsUserServiceImpl;
import io.turntabl.leaderboard.service.DeleteCodewarsUserServiceImpl;
import io.turntabl.leaderboard.service.GetUserFromCodewarsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CodewarsUserController {
    private final GetUserFromCodewarsServiceImpl getUserFromCodewarsServiceImpl;
    private final AddCodewarsUserServiceImpl addCodewarsUserService;


    private final DeleteCodewarsUserServiceImpl deleteCodewarsUserServiceImpl;


    @PostMapping("user/addUser")
    public ResponseEntity<ResponseDTO> addCodewarsUser(@RequestBody CodewarsUserDTO codewarsUserDTO){
        CodewarsUserDTO codewarsUser = getUserFromCodewarsServiceImpl.getCodewarsUserService(codewarsUserDTO.getUsername());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .success(true)
                        .data(Map.of("data", addCodewarsUserService.addUser(codewarsUser)))
                        .build());
    }

    @DeleteMapping("user/deleteUser/{username}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String username) {
        deleteCodewarsUserServiceImpl.delete(username);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .success(true)
                        .data(Map.of("data", "User has been deleted"))
                        .build());
    }
}
