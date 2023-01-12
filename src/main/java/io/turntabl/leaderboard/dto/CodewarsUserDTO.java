package io.turntabl.leaderboard.dto;


import io.turntabl.leaderboard.model.Comment;
import io.turntabl.leaderboard.model.Rank;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Builder
@Data
public class CodewarsUserDTO {
    private String username;
    private String id;
    private String name;
    private int honor;
    private String clan;
    private Rank ranks;
    private List<Comment> comments;
}