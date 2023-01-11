package io.turntabl.leaderboard.controller;


import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.dto.CodewarsUserDTOWithRanks;
import io.turntabl.leaderboard.dto.ResponseDTO;
import io.turntabl.leaderboard.service.AddCodewarsUserServiceImpl;
import io.turntabl.leaderboard.service.GetCodewarsUsersServiceImpl;
import io.turntabl.leaderboard.service.GetUserFromCodewarsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LeaderboardController {
    private final GetUserFromCodewarsServiceImpl getUserFromCodewarsServiceImpl;

    private final GetCodewarsUsersServiceImpl getCodewarsUsersServiceImpl;
    private final AddCodewarsUserServiceImpl addCodewarsUserServiceImpl;

    @PostMapping("/addUser/{username}")
    public ResponseEntity<CodewarsUserDTO> addCodewarsUser(@PathVariable String username) {
        CodewarsUserDTO codewarsUser = getUserFromCodewarsServiceImpl.getCodewarsUserService(username);
        return ResponseEntity.status(HttpStatus.CREATED).body(addCodewarsUserServiceImpl.addUser(codewarsUser));
    }

    @GetMapping("/getUser/{username}")
    public ResponseEntity<ResponseDTO> getCodewarsUser(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .success(true)
                        .data(Map.of("data",getUserFromCodewarsServiceImpl.getCodewarsUserService(username)))
                        .build());
    }

    @GetMapping("/getUsers/honors")
    public ResponseEntity<ResponseDTO> getCodewarsUsersByHonor() {

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .success(true)
                        .data(Map.of("data", getCodewarsUsersServiceImpl.getUsersByHonorDescending()))
                        .build());
    };


    @GetMapping("/getUsers")
    public ResponseEntity<ResponseDTO> getCodewarsUserByLanguage(@RequestParam String sortBy) {
        if (sortBy.equals("overall")) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDTO.builder()
                            .success(true)
                            .data(Map.of("data", getCodewarsUsersServiceImpl.getUsersByOverallScoreDescending()))
                            .build());
        }
        return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDTO.builder()
                            .success(true)
                            .data(Map.of("data", getCodewarsUsersServiceImpl.getUsersByLanguage(sortBy)))
                            .build());
        }
}
