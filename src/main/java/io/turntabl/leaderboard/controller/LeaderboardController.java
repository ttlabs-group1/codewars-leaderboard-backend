package io.turntabl.leaderboard.controller;


import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.dto.ResponseDTO;
import io.turntabl.leaderboard.service.AddCodewarsUserServiceImpl;
import io.turntabl.leaderboard.service.GetCodewarsUsersServiceImpl;
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
public class LeaderboardController {
    private final GetUserFromCodewarsServiceImpl getUserFromCodewarsServiceImpl;

    private final GetCodewarsUsersServiceImpl getCodewarsUsersServiceImpl;
    private final AddCodewarsUserServiceImpl addCodewarsUserServiceImpl;

    //add a user from codewars into our system
    @PostMapping("/addUser/{username}")
    public ResponseEntity<CodewarsUserDTO> addCodewarsUser(@PathVariable String username) {
        CodewarsUserDTO codewarsUser = getUserFromCodewarsServiceImpl.getCodewarsUserService(username);
        return ResponseEntity.status(HttpStatus.CREATED).body(addCodewarsUserServiceImpl.addUser(codewarsUser));
    }

    //get a user from codewars external
    @GetMapping("/getUser/codewars/{username}")
    public ResponseEntity<ResponseDTO> getCodewarsUser(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .success(true)
                        .data(Map.of("data", getUserFromCodewarsServiceImpl.getCodewarsUserService(username)))
                        .build());
    }

    // sort by honors
    @GetMapping("/getUsers/honors")
    public ResponseEntity<ResponseDTO> getCodewarsUsersByHonor() {

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .success(true)
                        .data(Map.of("data", getCodewarsUsersServiceImpl.getUsersByHonorDescending()))
                        .build());
    }


    // get user by overall and by language
    // ?sortby=overall
    // ?sortBy=<language>
    @GetMapping("/getUsers")
    public ResponseEntity<ResponseDTO> getCodewarsUserWithFilter(@RequestParam String sortBy) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(getCodewarsUsersServiceImpl.getUsersByOverallByFilter(sortBy));

    }

    //get a particular user
    @GetMapping("/getUser/{id}")
    public ResponseEntity<ResponseDTO> getCodewarsUserWithID(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .success(true)
                        .data(Map.of("data", getCodewarsUsersServiceImpl.getUsersByCodewarsID(id)))
                        .build());
    }
}
