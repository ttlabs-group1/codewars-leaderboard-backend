package io.turntabl.leaderboard.controller;

import io.turntabl.leaderboard.model.Comment;
import io.turntabl.leaderboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/{username}")
    public Comment addComment(@RequestBody Comment comment, @PathVariable String username){

        return commentService.addComment(comment, username);
    }
}
