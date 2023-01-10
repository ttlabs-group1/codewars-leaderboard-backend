package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.model.Comment;
import io.turntabl.leaderboard.repository.CodewarsRepository;
import io.turntabl.leaderboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final CodewarsRepository codewarsRepository;
    public Comment addComment(Comment comment, String username) {
        Optional<CodewarsUserDTO> commentuser = codewarsRepository.findByUsername(username);
        if (commentuser.isPresent()){
            commentuser.get().getComments().add(comment);
            System.out.println(commentuser);
            codewarsRepository.save(commentuser.get());
        }
        return commentRepository.save(comment);

//        return comment;
    }
}
