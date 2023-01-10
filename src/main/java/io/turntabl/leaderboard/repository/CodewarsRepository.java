package io.turntabl.leaderboard.repository;


import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.model.CodewarsUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodewarsRepository extends MongoRepository<CodewarsUserDTO, Long> {
    Optional<CodewarsUserDTO> findByUsername(String username);
}
