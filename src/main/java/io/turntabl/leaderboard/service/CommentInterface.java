package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.model.Comment;

public interface CommentInterface {

    public Comment addComment(Comment comment, String id);
}
