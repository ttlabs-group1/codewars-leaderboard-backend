package io.turntabl.leaderboard.service;


import io.turntabl.leaderboard.dto.CodewarsUserDTO;

public interface AddCodewarsUserService {
    CodewarsUserDTO addUser(CodewarsUserDTO codewarsUser);
}