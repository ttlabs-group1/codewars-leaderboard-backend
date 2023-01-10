package io.turntabl.leaderboard.service;


import io.turntabl.leaderboard.client.CodewarsClient;
import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.model.CodewarsUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetUserFromCodewarsService {
    private final CodewarsClient codewarsClient;

    public CodewarsUserDTO getCodewarsUserService(String username){
        CodewarsUser codewarsUser = codewarsClient.getCodewarsUser(username);


        return CodewarsUserDTO.builder()
                .honor(codewarsUser.getHonor())
                .username(codewarsUser.getUsername())
                .name(codewarsUser.getName())
                .clan(codewarsUser.getClan())
                .ranks(codewarsUser.getRanks())
                .comments(codewarsUser.getComments())
                .build();
    }
}
