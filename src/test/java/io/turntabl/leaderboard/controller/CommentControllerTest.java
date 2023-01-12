package io.turntabl.leaderboard.controller;


import io.turntabl.leaderboard.model.Comment;
import io.turntabl.leaderboard.repository.CommentRepository;
import io.turntabl.leaderboard.service.CommentInterface;
import io.turntabl.leaderboard.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Comment comment;

    @BeforeEach
    public void init() {
        comment = Comment.builder()
                .commentText("Ana")
                .commentId("frfrfr")
                .build();
    }

    @MockBean
    private CommentInterface commentInterface;

    @MockBean
    private CommentService commentService;
    @MockBean
    private CommentRepository commentRepository;

    @Test
    public void CodewarsUserController_AddCommentCodewarsUser_ReturnCreated() throws Exception {
        given(commentInterface.addComment(ArgumentMatchers.any(), ArgumentMatchers.any())).willReturn(comment);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/comment/ghghg")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "commentText": "kofi" 
                        }
                        """)).andExpect(status().isOk());
    }
}
