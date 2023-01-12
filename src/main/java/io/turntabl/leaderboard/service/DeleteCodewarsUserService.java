package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.repository.CodewarsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCodewarsUserService implements DeleteCodewarsUserInterface {

    private final CodewarsRepository codewarsRepository;

    public void delete(String username) {
        codewarsRepository.deleteByUsername(username);
    }

}
