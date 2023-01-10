package io.turntabl.leaderboard.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;


@Data
public class Rank {
    private RankInfo overall;
    private Map<String, RankInfo> languages;
}

