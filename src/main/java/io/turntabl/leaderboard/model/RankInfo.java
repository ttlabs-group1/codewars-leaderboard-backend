package io.turntabl.leaderboard.model;

import lombok.Data;

@Data
public class RankInfo {
    private String rank;
    private String name;
    private String color;
    private Integer score;
}