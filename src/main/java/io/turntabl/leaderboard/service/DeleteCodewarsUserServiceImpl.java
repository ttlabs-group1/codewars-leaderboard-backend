package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.exceptions.UserNotFoundException;
import io.turntabl.leaderboard.repository.CodewarsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteCodewarsUserServiceImpl implements DeleteCodewarsUserService {

    private final CodewarsRepository codewarsRepository;

    public void delete(String username) {
        Optional<CodewarsUserDTO> user = codewarsRepository.findByUsername(username);
        if(!user.isPresent()){
            throw new UserNotFoundException("This user was not found.");
        }
        codewarsRepository.deleteByUsername(username);
    }

}
