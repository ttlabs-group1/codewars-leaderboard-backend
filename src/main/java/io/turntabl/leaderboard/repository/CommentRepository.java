package io.turntabl.leaderboard.repository;

import io.turntabl.leaderboard.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

//    Optional<Comment> findByUsername(String username);
}
