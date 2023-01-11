package io.turntabl.leaderboard.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "comment")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    @Id
    private String commentId;
    private String codewarsuserid;
    private String commentText;
}