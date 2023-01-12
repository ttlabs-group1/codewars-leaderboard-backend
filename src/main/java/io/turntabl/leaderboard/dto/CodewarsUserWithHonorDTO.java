package io.turntabl.leaderboard.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CodewarsUserWithHonorDTO {

    private String codewarsId;
    private String username;
    private String name;
    private int honor;
}
