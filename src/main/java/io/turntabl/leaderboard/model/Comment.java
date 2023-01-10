package io.turntabl.leaderboard.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "comment")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    private String codewarsuserid;
    private String commentText;
}