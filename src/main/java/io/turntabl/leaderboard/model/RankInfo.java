package io.turntabl.leaderboard.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RankInfo {
    private String rank;
    private String name;
    private String color;
    private Integer score;
}