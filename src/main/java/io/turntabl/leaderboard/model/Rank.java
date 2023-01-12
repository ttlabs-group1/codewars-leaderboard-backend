package io.turntabl.leaderboard.model;

import lombok.Data;

import java.util.Map;


@Data
public class Rank {
    private RankInfo overall;
    private Map<String, RankInfo> languages;
}

