package io.turntabl.leaderboard.controller;

import io.turntabl.leaderboard.config.SecurityConfig;
import io.turntabl.leaderboard.dto.LoginUserDTO;
import io.turntabl.leaderboard.dto.RegisterUserDTO;
import io.turntabl.leaderboard.dto.UserDTO;
import io.turntabl.leaderboard.exceptions.UsernameNotAvailableException;
import io.turntabl.leaderboard.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AccountController.class)
@Import(SecurityConfig.class)
@MockBean(UserDetailsService.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountService accountService;
    private RegisterUserDTO registerUserDTO;
    private LoginUserDTO loginUserDTO;

    @BeforeEach
    void setUp() {

        registerUserDTO = RegisterUserDTO.builder()
                .fullName("Fred Arthur")
                .username("fred")
                .password("pass123")
                .build();
        loginUserDTO = new LoginUserDTO("fred", "pass123");

    }

    @Test
    @DisplayName("Create User Account Should Succeed!")
    void register_GivenValidUser_CreateUser() throws Exception {

        when(accountService.registerUser(registerUserDTO)).thenReturn(true);

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


    @Test
    @DisplayName("Create User Account With Existing username Should Fail")
    void register_GivenExistingUsername_ShouldFail() throws Exception {

        when(accountService.registerUser(registerUserDTO)).thenThrow(new UsernameNotAvailableException("Username Not Available!"));

        mockMvc.perform(post("/api/v1/account/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "username": "fred",
                                "password": "pass123",
                                "fullName": "Fred Arthur"
                                }
                                """)).andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    @DisplayName("Login With Valid Credentials Should Pass")
    void login_GivenValidCredentials_ShouldSucceed() throws Exception {
        when(accountService.authenticateUser(loginUserDTO))
                .thenReturn(new UserDTO("fred", "Fred Arthur"));
        mockMvc.perform(post("/api/v1/account/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "username": "kofi",
                        "password": "pass123"
                        }
                        """)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Logout should succeed")
    void logout(){

//        mockMvc.perform()
    }
}
