package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.dto.CodewarsUserDTO;

public interface GetUserFromCodewarsService {
    public CodewarsUserDTO getCodewarsUserService(String username);
}
