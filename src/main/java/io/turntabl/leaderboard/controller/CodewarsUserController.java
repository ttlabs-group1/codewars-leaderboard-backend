package io.turntabl.leaderboard.controller;


import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.service.AddCodewarsUserService;
import io.turntabl.leaderboard.service.GetCodewarsUsersService;
import io.turntabl.leaderboard.service.GetUserFromCodewarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.status(HttpStatus.OK).body(addCodewarsUserService.addUser(codewarsUser));
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<CodewarsUserDTO>> getCodewarsUsersByHonor() {
        return ResponseEntity.status(HttpStatus.OK).body(getCodewarsUsersService.getUsersByHonorDescending());
    }

    @GetMapping("/getUsers/overall")
    public ResponseEntity<List<CodewarsUserDTO>> getCodewarsUsersByOverallScore() {
        return ResponseEntity.status(HttpStatus.OK).body(getCodewarsUsersService.getUsersByOverallScoreDescending());
    }

}
