package io.turntabl.leaderboard.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserDTO {
    private String username;
    private String password;
    private String fullName;

}
