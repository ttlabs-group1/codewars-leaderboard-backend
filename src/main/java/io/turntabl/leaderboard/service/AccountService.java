package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.dto.LoginUserDTO;
import io.turntabl.leaderboard.dto.RegisterUserDTO;
import io.turntabl.leaderboard.dto.UserDTO;
import io.turntabl.leaderboard.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public interface AccountService {
    boolean registerUser(RegisterUserDTO userDTO);

    UserDTO authenticateUser(LoginUserDTO userDTO);

    void logout(HttpServletRequest request) throws ServletException;
}
