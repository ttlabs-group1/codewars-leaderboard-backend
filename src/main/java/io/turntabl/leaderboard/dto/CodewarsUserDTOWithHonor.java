package io.turntabl.leaderboard.dto;

import io.turntabl.leaderboard.model.Comment;
import io.turntabl.leaderboard.model.Rank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CodewarsUserDTOWithHonor {

    private String codewarsId;
    private String username;
    private String name;
    private int honor;
}
