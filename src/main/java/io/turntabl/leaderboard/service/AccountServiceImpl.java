package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.dto.LoginUserDTO;
import io.turntabl.leaderboard.dto.RegisterUserDTO;
import io.turntabl.leaderboard.dto.UserDTO;
import io.turntabl.leaderboard.error.LogoutFailedException;
import io.turntabl.leaderboard.error.UserNotFoundException;
import io.turntabl.leaderboard.error.UsernameNotAvailableException;
import io.turntabl.leaderboard.model.User;
import io.turntabl.leaderboard.repository.UserRepository;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Service
@Log4j2
public class AccountServiceImpl implements AccountService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                              AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public boolean registerUser(RegisterUserDTO userDTO) throws UsernameNotAvailableException {
        var optionalUser = userRepository.findByUsername(userDTO.getUsername());
        if (optionalUser.isPresent()) {
            throw new UsernameNotAvailableException("Username Not Available!");
        }
        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getFullName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
         userRepository.save(user);
         return  true;
    }

    @Override
    public UserDTO authenticateUser(LoginUserDTO userDTO) throws UserNotFoundException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())

        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var user = userRepository.findByUsername(userDTO.getUsername());
        return user.map(UserDTO::new).orElseThrow(
                () -> {
                    throw new UserNotFoundException("The Requested User Does Not Exist");
                }
        );
    }

    @Override
    public void logout(HttpServletRequest request) throws LogoutFailedException {
       try {
           request.logout();
       } catch (ServletException exception){
           throw new LogoutFailedException(exception.getMessage());
       }
    }
}
