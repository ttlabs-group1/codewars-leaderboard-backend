package io.turntabl.leaderboard.service;


import io.turntabl.leaderboard.client.CodewarsClient;
import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.model.CodewarsUser;
import io.turntabl.leaderboard.model.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetUserFromCodewarsServiceImpl implements GetUserFromCodewarsService {
    private final CodewarsClient codewarsClient;

    public CodewarsUserDTO getCodewarsUserService(String username){
        CodewarsUser codewarsUser = codewarsClient.getCodewarsUser(username);

        List<Comment> comments = new ArrayList<>();

        return CodewarsUserDTO.builder()
                .id(codewarsUser.getId())
                .honor(codewarsUser.getHonor())
                .username(codewarsUser.getUsername())
                .name(codewarsUser.getName())
                .clan(codewarsUser.getClan())
                .ranks(codewarsUser.getRanks())
                .comments(comments)
                .build();
    }
}
