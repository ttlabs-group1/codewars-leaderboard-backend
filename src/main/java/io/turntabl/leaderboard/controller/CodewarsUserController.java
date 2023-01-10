package io.turntabl.leaderboard.controller;


import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.service.AddCodewarsUserService;
import io.turntabl.leaderboard.service.GetUserFromCodewarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/getUser")
@RequiredArgsConstructor
public class CodewarsUserController {
    private final GetUserFromCodewarsService getUserFromCodewarsService;
    private final AddCodewarsUserService addCodewarsUserService;


    @PostMapping("/addUser/{username}")
    public ResponseEntity<CodewarsUserDTO> addCodewarsUser(@PathVariable String username) {
        CodewarsUserDTO codewarsUser = getUserFromCodewarsService.getCodewarsUserService(username);
        return ResponseEntity.status(HttpStatus.OK).body(addCodewarsUserService.addUser(codewarsUser));
    }

    @DeleteMapping("/deleteUser/{username}")
    public void deleteUser(@PathVariable String username){
        addCodewarsUserService.delete(username);
    }


}
