package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.dto.CodewarsUserDTOWithHonor;
import io.turntabl.leaderboard.dto.CodewarsUserDTOWithRanks;

import java.util.List;

public interface GetCodewarsUsersService {
    List<CodewarsUserDTOWithHonor> getUsersByHonorDescending();

    public List<CodewarsUserDTOWithRanks> getUsersByOverallScoreDescending();

    public List<CodewarsUserDTOWithRanks> getUsersByLanguage(String language);

}
