package io.turntabl.leaderboard.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "codewarsuser")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class CodewarsUser {
    @Id
    private String id;
    private String username;
    private String name;
    private int honor;
    private String clan;
    private Rank ranks;
    private List<Comment> comments;
}

