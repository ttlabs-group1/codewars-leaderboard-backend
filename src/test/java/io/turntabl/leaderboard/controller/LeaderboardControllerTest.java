package io.turntabl.leaderboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.dto.CodewarsUserWithHonorDTO;
import io.turntabl.leaderboard.repository.CodewarsRepository;
import io.turntabl.leaderboard.service.AddCodewarsUserServiceImpl;
import io.turntabl.leaderboard.service.GetCodewarsUsersServiceImpl;
import io.turntabl.leaderboard.service.GetUserFromCodewarsServiceImpl;
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



@WebMvcTest(controllers = LeaderboardController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
    public class LeaderboardControllerTest {

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

        codewarsUserWithHonor = CodewarsUserWithHonorDTO.builder()
                .honor(8)
                .name("John")
                .username("john47")
                .build();


        codewarsUserWithHonorDTOS.add(codewarsUserWithHonor);
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
    private CodewarsRepository codewarsRepository;

    @Test
    public void CodewarsUserController_AddCodewarsUser_ReturnCreated() throws Exception {
        given(addCodewarsUserServiceImpl.addUser(ArgumentMatchers.any())).willReturn(codewarsUser);
        given(getUserFromCodewarsServiceImpl.getCodewarsUserService(ArgumentMatchers.any())).willReturn(codewarsUser);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/addUser/kweks45"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(codewarsUserWithHonor.getUsername())));
    }

    @Test
    public void CodewarsUserController_GetCodewarsUser_ReturnCreated() throws Exception {
        given(getCodewarsUsersServiceImpl.getUsersByHonorDescending()).willReturn(codewarsUserWithHonorDTOS);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getUsers/honors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.data.[0].username", CoreMatchers.is(codewarsUserWithHonor.getUsername())));
    }
}
