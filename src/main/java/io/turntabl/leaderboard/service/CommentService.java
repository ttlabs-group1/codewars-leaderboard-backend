package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.exceptions.CommentTextFieldEmptyException;
import io.turntabl.leaderboard.exceptions.UserNotFoundException;
import io.turntabl.leaderboard.model.Comment;
import io.turntabl.leaderboard.repository.CodewarsRepository;
import io.turntabl.leaderboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentInterface{

    private final CommentRepository commentRepository;

    private final CodewarsRepository codewarsRepository;

    public Comment addComment(Comment comment, String id) {
        Optional<CodewarsUserDTO> commentuser = codewarsRepository.findById(id);
            if (!commentuser.isPresent()){
                throw new UserNotFoundException("This user was not found.");
            }else if(comment.getCommentText().isEmpty()){
                throw new CommentTextFieldEmptyException("Comment text cannot be empty.");
        }
        commentuser.get().getComments().add(comment);
        codewarsRepository.save(commentuser.get());
        comment.setCodewarsuserid(id);
        return commentRepository.save(comment);
    }
}
