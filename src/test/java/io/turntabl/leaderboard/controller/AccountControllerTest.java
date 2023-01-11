package io.turntabl.leaderboard.controller;

import io.turntabl.leaderboard.dto.RegisterUserDTO;
import io.turntabl.leaderboard.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
   @MockBean
    private AccountService accountService;
    private RegisterUserDTO userDTO;

    @BeforeEach
    void setUp() {


    }

    @Test
    void register_GivenValidUser_CreateUser() throws Exception {
        userDTO =  RegisterUserDTO.builder()
                .fullName("Fred Arthur")
                .username("fred")
                .password("password123")
                .build();
        Mockito.when(accountService.registerUser(userDTO)).thenReturn(true);

        mockMvc.perform(post("/api/v1/account/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "username": "fred",
                        "password": "pass123",
                        "fullName": "Fred Arthur"
                        }
                        """)).andExpect(status().isOk());



    }
}