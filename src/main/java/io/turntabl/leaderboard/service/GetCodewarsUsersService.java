package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.dto.CodewarsUserWithHonorDTO;
import io.turntabl.leaderboard.dto.CodewarsUserWithRanksDTO;
import io.turntabl.leaderboard.dto.ResponseDTO;

import java.util.List;
import java.util.Optional;

public interface GetCodewarsUsersService {
    List<CodewarsUserWithHonorDTO> getUsersByHonorDescending();

    public List<CodewarsUserWithRanksDTO> getUsersByOverallScoreDescending();

    public List<CodewarsUserWithRanksDTO> getUsersByLanguage(String language);
    public Optional<CodewarsUserDTO> getUsersByCodewarsID(String id);

    public ResponseDTO getUsersByOverallByFilter(String sortBy);

}
