package io.turntabl.leaderboard.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {
    private boolean success;
    private String message;
    private Map<?, ?> data;
}
