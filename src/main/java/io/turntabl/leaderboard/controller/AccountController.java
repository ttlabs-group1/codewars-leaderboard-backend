package io.turntabl.leaderboard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.turntabl.leaderboard.dto.LoginUserDTO;
import io.turntabl.leaderboard.dto.RegisterUserDTO;
import io.turntabl.leaderboard.dto.ResponseDTO;
import io.turntabl.leaderboard.repository.UserRepository;
import io.turntabl.leaderboard.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private AccountService accountService;
    private final UserRepository userRepository;

    @Autowired
    public AccountController(AccountService accountService,
                             UserRepository userRepository) {
        this.accountService = accountService;
        this.userRepository = userRepository;
    }


    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterUserDTO userDTO) {
        accountService.registerUser(userDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .success(true)
                        .message("User Account created Successfully!")
                        .build());
    }

    @PostMapping("/account/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginUserDTO userDTO) {

        var user = accountService.authenticateUser(userDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .success(true)
                        .data(Map.of("data", user))
                        .build()

                );

    }

    @PostMapping("/account/logout")
    public ResponseEntity<ResponseDTO> logout() {

         accountService.logout();
        return  ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .success(true)
                        .build());
    }
}
