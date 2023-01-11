package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.dto.CodewarsUserDTO;

public interface CodewarsUserInterface {

    public CodewarsUserDTO addUser(CodewarsUserDTO codewarsUser);

    public void delete(String username);
}
