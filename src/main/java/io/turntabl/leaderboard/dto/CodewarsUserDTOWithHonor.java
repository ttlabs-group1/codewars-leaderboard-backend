package io.turntabl.leaderboard.dto;

import io.turntabl.leaderboard.model.Comment;
import io.turntabl.leaderboard.model.Rank;
import lombok.Builder;

import java.util.List;

@Builder
public class CodewarsUserDTOWithHonor {
    private String username;
    private String name;
    private int honor;
}
