package io.turntabl.leaderboard.dto;

import io.turntabl.leaderboard.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String fullName;

    public UserDTO(User user){
        this.username = user.getUsername();
        this.fullName = user.getFullName();
    }
}
