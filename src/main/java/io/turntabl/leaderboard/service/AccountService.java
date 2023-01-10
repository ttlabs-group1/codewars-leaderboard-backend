package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.dto.LoginUserDTO;
import io.turntabl.leaderboard.dto.RegisterUserDTO;
import io.turntabl.leaderboard.dto.UserDTO;
import io.turntabl.leaderboard.model.User;

public interface AccountService {
    User registerUser(RegisterUserDTO userDTO);

    UserDTO authenticateUser(LoginUserDTO userDTO);

    void logout();
}
