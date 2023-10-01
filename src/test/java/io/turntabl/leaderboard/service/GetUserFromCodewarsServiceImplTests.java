package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.client.CodewarsClient;
import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.model.CodewarsUser;
import io.turntabl.leaderboard.model.Comment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserFromCodewarsServiceImplTests {
    @Mock
    private CodewarsClient codewarsClient;

    @InjectMocks
    private GetUserFromCodewarsServiceImpl getUserFromCodewarsService;

    @Test
    public void setGetUserFromCodewarsService_GetUser_ReturnsCodewarsDTO() {

        CodewarsUser codewarsUser = CodewarsUser.builder()
                .honor(9)
                .name("kwaku")
                .username("kb45")
                .build();

        List<Comment> comment = new ArrayList<>();

        CodewarsUserDTO codewarsUserDTO = CodewarsUserDTO.builder()
                .honor(9)
                .name("kwaku")
                .username("kb45")
                .comments(comment)
                .build();

        when(codewarsClient.getCodewarsUser(any())).thenReturn(codewarsUser);

        CodewarsUserDTO returnedCodewarsUser = getUserFromCodewarsService.getCodewarsUserService("kb45");
        Assertions.assertThat(returnedCodewarsUser).isEqualTo(codewarsUserDTO);
    }
}