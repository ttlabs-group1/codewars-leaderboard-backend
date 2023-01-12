package io.turntabl.leaderboard.dto;

import io.turntabl.leaderboard.model.Rank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CodewarsUserDTOWithRanks {
    private String username;
    private String codewarsId;
    private String name;
    private Rank ranks;
}
