package io.turntabl.leaderboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.dto.CodewarsUserWithHonorDTO;
import io.turntabl.leaderboard.model.Comment;
import io.turntabl.leaderboard.repository.CodewarsRepository;
import io.turntabl.leaderboard.repository.CommentRepository;
import io.turntabl.leaderboard.service.*;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = CodewarsUserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CodewarsUserControllerTest{
    private CodewarsUserWithHonorDTO codewarsUserWithHonor;

    private CodewarsUserDTO codewarsUser;


    @Autowired
    private ObjectMapper objectMapper;

    List<CodewarsUserWithHonorDTO> codewarsUserWithHonorDTOS = new ArrayList<>();

    List<CodewarsUserDTO> codewarsUserDTO = new ArrayList<>();
    @BeforeEach
    public void init() {

        codewarsUser = CodewarsUserDTO.builder()
                .honor(4)
                .name("John")
                .username("john47")
                .build();


    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddCodewarsUserServiceImpl addCodewarsUserServiceImpl;
    @MockBean
    private GetCodewarsUsersServiceImpl getCodewarsUsersServiceImpl;
    @MockBean
    private GetUserFromCodewarsServiceImpl getUserFromCodewarsServiceImpl;

    @MockBean
    private DeleteCodewarsUserServiceImpl deleteCodewarsUserServiceImpl;

    @MockBean
    private CodewarsRepository codewarsRepository;


    @Test
    public void CodewarsUserController_AddCodewarsUser_ReturnCreated() throws Exception {
        given(addCodewarsUserServiceImpl.addUser(ArgumentMatchers.any())).willReturn(codewarsUser);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/addUser/")
                        .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "id": "xxxx",
                        "username": "john47",
                        "honor":9                    
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.data.username", CoreMatchers.is(codewarsUser.getUsername())));
    }


    @Test
    public void CodewarsUserController_DeleteCodewarsUser_ReturnOK() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/deleteUser/kweks45"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

