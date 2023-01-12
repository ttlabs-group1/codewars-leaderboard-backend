package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.dto.LoginUserDTO;
import io.turntabl.leaderboard.dto.RegisterUserDTO;

import io.turntabl.leaderboard.exceptions.UsernameNotAvailableException;
import io.turntabl.leaderboard.model.User;
import io.turntabl.leaderboard.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.ArgumentMatchers.any;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @InjectMocks
    private AccountServiceImpl accountService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private RegisterUserDTO registerUser;
    private LoginUserDTO loginUser;
    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        registerUser = RegisterUserDTO.builder()
                .username("fred")
                .password("pass123")
                .fullName("Fred Arthur")
                .build();
        loginUser = new LoginUserDTO("fred", "pass123");
    }

    @Test
    @DisplayName("Should Pass If Username Does Not Exist")
    void registerUser_GivenNewUsername_ShouldSucceed() {
        Mockito.when(userRepository.findByUsername(registerUser.getUsername()))
                .thenReturn(Optional.empty());

        Mockito.when(passwordEncoder.encode(any(String.class)))
                .thenReturn("fjsskwfdhwjdhwqahq");

        boolean result = accountService.registerUser(registerUser);
        Assertions.assertEquals(true, result);
    }

    @Test
    @DisplayName("Should Throw Exception If Username Exist")
    void registerUser_GivenUsernameExist_ShouldThrow_UsernameNotAvailableException() {
        User user = new User("fred", "passkjjoiownjnkja", "Fred Arthur");

        Mockito.when(userRepository.findByUsername(registerUser.getUsername()))
                .thenReturn(Optional.of(user));

        // boolean result = accountService.registerUser(registerUserDTO);

        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
                    accountService.registerUser(registerUser);
                }).isInstanceOf(UsernameNotAvailableException.class)
                .hasMessage("Username Not Available!");
    }

    @Test
    @DisplayName("Given Valid Credentials Login Should Succeed")
    void authenticateUser_GivenValidCredentials_ShouldSucceed() {


    }

    @Test
    @DisplayName("Logout Should Succeed")
    void logout() throws ServletException {
        accountService.logout(request);
        Mockito.verify(request, Mockito.times(1)).logout();
    }
}