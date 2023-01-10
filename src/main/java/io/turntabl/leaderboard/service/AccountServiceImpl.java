package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.dto.LoginUserDTO;
import io.turntabl.leaderboard.dto.RegisterUserDTO;
import io.turntabl.leaderboard.dto.UserDTO;
import io.turntabl.leaderboard.error.UserNotFoundException;
import io.turntabl.leaderboard.error.UsernameNotAvailableException;
import io.turntabl.leaderboard.model.User;
import io.turntabl.leaderboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
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
    public User registerUser(RegisterUserDTO userDTO) {
        var optionalUser = userRepository.findByUsername(userDTO.getUsername());
        if (optionalUser.isPresent()) {
            throw new UsernameNotAvailableException("Username Not Available!");
        }
        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getFullName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDTO authenticateUser(LoginUserDTO userDTO) {

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
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof UsernamePasswordAuthenticationToken){

            System.out.println(authentication.getName());

        }
    }
}
