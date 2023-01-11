package io.turntabl.leaderboard.controller;


import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.dto.CodewarsUserDTOWithHonor;
import io.turntabl.leaderboard.dto.CodewarsUserDTOWithRanks;
import io.turntabl.leaderboard.service.AddCodewarsUserService;
import io.turntabl.leaderboard.service.GetCodewarsUsersService;
import io.turntabl.leaderboard.service.GetUserFromCodewarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CodewarsUserController {
    private final GetUserFromCodewarsService getUserFromCodewarsService;

    private final GetCodewarsUsersService getCodewarsUsersService;
    private final AddCodewarsUserService addCodewarsUserService;

    @PostMapping("/addUser/{username}")
    public ResponseEntity<CodewarsUserDTO> addCodewarsUser(@PathVariable String username) {
        CodewarsUserDTO codewarsUser = getUserFromCodewarsService.getCodewarsUserService(username);
        return ResponseEntity.status(HttpStatus.CREATED).body(addCodewarsUserService.addUser(codewarsUser));
    }

    @GetMapping("/getUsers/honors")
    public ResponseEntity<List<CodewarsUserDTOWithHonor>> getCodewarsUsersByHonor() {
            return ResponseEntity.status(HttpStatus.OK).body(getCodewarsUsersService.getUsersByHonorDescending());
        };

    @GetMapping("/getUser/{username}")
    public ResponseEntity<CodewarsUserDTO> getCodewarsUser(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(getUserFromCodewarsService.getCodewarsUserService(username));
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<CodewarsUserDTOWithRanks>> getCodewarsUserByLanguage(@RequestParam String sortBy) {
        if (sortBy.equals("overall")) {
            return ResponseEntity.status(HttpStatus.OK).body(getCodewarsUsersService.getUsersByOverallScoreDescending());
        }
        return ResponseEntity.status(HttpStatus.OK).body(getCodewarsUsersService.getUsersByLanguage(sortBy));
    }
}
