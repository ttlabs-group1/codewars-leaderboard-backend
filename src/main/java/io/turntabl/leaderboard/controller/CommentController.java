package io.turntabl.leaderboard.controller;

import io.turntabl.leaderboard.dto.ResponseDTO;
import io.turntabl.leaderboard.model.Comment;
import io.turntabl.leaderboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/user/comment/{id}")
    public ResponseEntity<ResponseDTO> addComment(@RequestBody Comment comment, @PathVariable String id){

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .success(true)
                        .data(Map.of("data",commentService.addComment(comment, id)))
                        .build());
    }
}
