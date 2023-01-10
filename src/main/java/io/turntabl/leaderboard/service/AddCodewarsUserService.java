package io.turntabl.leaderboard.service;


import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.exceptions.UserAlreadyExistsException;
import io.turntabl.leaderboard.model.CodewarsUser;
import io.turntabl.leaderboard.repository.CodewarsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddCodewarsUserService {

    private final CodewarsRepository codewarsRepository;

    public CodewarsUserDTO addUser(CodewarsUserDTO codewarsUser){
        Optional<CodewarsUserDTO> username = codewarsRepository.findByUsername(codewarsUser.getUsername());
        if(username.isPresent()){
            throw new UserAlreadyExistsException("This user already exists");
        }

//        CodewarsUser newCodewarsUser = CodewarsUser.builder()
//                .honor(codewarsUser.getHonor())
//                .ranks(codewarsUser.getRanks())
//                .name(codewarsUser.getName())
//                .clan(codewarsUser.getClan())
//                .username(codewarsUser.getUsername())
//                .comments(codewarsUser.getComments())
//                .ranks(codewarsUser.getRanks())
//                .build();

        codewarsRepository.save(codewarsUser);
        return codewarsUser;
    }

}
