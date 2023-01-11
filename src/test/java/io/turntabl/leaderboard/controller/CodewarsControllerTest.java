package io.turntabl.leaderboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.model.Rank;
import io.turntabl.leaderboard.service.AddCodewarsUserService;
import io.turntabl.leaderboard.service.GetCodewarsUsersService;
import io.turntabl.leaderboard.service.GetUserFromCodewarsService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;



@WebMvcTest(controllers = CodewarsUserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CodewarsControllerTest {

    private CodewarsUserDTO codewarsUser;
    @Autowired
    private ObjectMapper objectMapper;

    List<CodewarsUserDTO> codewarsUserDTOList = new ArrayList<>();

    @BeforeEach
    public void init() {
        codewarsUser = CodewarsUserDTO.builder()
                .honor(8)
                .name("John")
                .username("John47")
                .clan("turntabl")
                .ranks(new Rank())
                .build();


        codewarsUserDTOList.add(codewarsUser);
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddCodewarsUserService addCodewarsUserService;
    @MockBean
    private GetCodewarsUsersService getCodewarsUsersService;
    @MockBean
    private GetUserFromCodewarsService getUserFromCodewarsService;

    @Test
    public void CodewarsUserController_AddCodewarsUser_ReturnCreated() throws Exception {
        given(addCodewarsUserService.addUser(ArgumentMatchers.any())).willReturn(codewarsUser);
        given(getUserFromCodewarsService.getCodewarsUserService(ArgumentMatchers.any())).willReturn(codewarsUser);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/addUser/kweks45"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(codewarsUser.getUsername())));
    }

//    @Test
//    public void CodewarsUserController_GetCodewarsUser_ReturnOK() throws Exception {
//        given(getCodewarsUsersService.getUsersByHonorDescending()).willReturn(codewarsUserDTOList);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getUsers?sortBy=honor"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//         .andExpect(MockMvcResultMatchers.jsonPath("$.[0].username", CoreMatchers.is(codewarsUser.getUsername())));

//    }
}
