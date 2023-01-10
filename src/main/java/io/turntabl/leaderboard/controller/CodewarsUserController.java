package io.turntabl.leaderboard.controller;


import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.service.CodewarsService;
import io.turntabl.leaderboard.service.GetCodewarsUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/getUser")
@RequiredArgsConstructor
public class CodewarsUserController {
    private final GetCodewarsUserService getCodewarsUserService;
    private final CodewarsService codewarsService;


    @PostMapping("/adduser/{username}")
    public ResponseEntity<CodewarsUserDTO> addCodewarsUser(@PathVariable String username) {
        CodewarsUserDTO codewarsuser = getCodewarsUserService.getCodewarsUserService(username);
        return ResponseEntity.status(HttpStatus.OK).body(codewarsService.addUser(codewarsuser));
    }

}
