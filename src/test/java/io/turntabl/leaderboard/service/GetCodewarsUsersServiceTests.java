package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.dto.CodewarsUserWithHonorDTO;
import io.turntabl.leaderboard.dto.CodewarsUserWithRanksDTO;
import io.turntabl.leaderboard.model.Rank;
import io.turntabl.leaderboard.model.RankInfo;
import io.turntabl.leaderboard.repository.CodewarsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class GetCodewarsUsersServiceTests {


    @Mock
    private CodewarsRepository codewarsRepository;

    @InjectMocks
    private GetCodewarsUsersServiceImpl getCodewarsUsersService;

    @Mock
    private MongoTemplate mongoTemplate;

    @Test
    public void getCodewarsUsersService_GetUsersByHonorDescending_ReturnsDtoWithHonor() {
        CodewarsUserDTO firstCodewarsUserDTO = CodewarsUserDTO.builder()
                .name("pikachu")
                .honor(9)
                .username("pikachu47")
                .build();

        CodewarsUserDTO secondCodewarsUserDTO = CodewarsUserDTO.builder()
                .name("pikac")
                .honor(7)
                .username("pika")
                .build();


        CodewarsUserDTO thirdCodewarsUserDTO = CodewarsUserDTO.builder()
                .name("lwe")
                .honor(5000)
                .username("lk")
                .build();

        List<CodewarsUserDTO> listOfCodewarsUsers = new ArrayList<>();

        listOfCodewarsUsers.add(firstCodewarsUserDTO);
        listOfCodewarsUsers.add(secondCodewarsUserDTO);
        listOfCodewarsUsers.add(thirdCodewarsUserDTO);
        when(codewarsRepository.findAll((Sort) any())).thenReturn(listOfCodewarsUsers);
        List<CodewarsUserWithHonorDTO> codewarsUserDTOWithHonor = getCodewarsUsersService.getUsersByHonorDescending();
        Assertions.assertThat(codewarsUserDTOWithHonor).isNotEmpty();
        Assertions.assertThat(codewarsUserDTOWithHonor.size()).isEqualTo(3);
    }

    @Test
    public void getCodewarsUsersService_GetUsersByOverallScoreDescending_ReturnsDtoWithHonor() {
        CodewarsUserDTO codewarsUserDTO = CodewarsUserDTO.builder()
                .name("pikachu")
                .honor(9)
                .username("pikachu47")
                .build();


        CodewarsUserWithHonorDTO codewarsUserDTOWithHonor = CodewarsUserWithHonorDTO.builder()
                .name("pikachu")
                .honor(9)
                .username("pikachu47")
                .build();

        List<CodewarsUserDTO> listOfCodewarsUsers = new ArrayList<>();
        listOfCodewarsUsers.add(codewarsUserDTO);
        List<CodewarsUserWithHonorDTO> listOfCodewarsUsersWithHonor = new ArrayList<>();
        listOfCodewarsUsersWithHonor.add(codewarsUserDTOWithHonor);
        when(codewarsRepository.findAll((Sort) any())).thenReturn(listOfCodewarsUsers);

        List<CodewarsUserWithHonorDTO> codewarsUserDTOWithHonorFromService = getCodewarsUsersService.getUsersByHonorDescending();

        Assertions.assertThat(codewarsUserDTOWithHonorFromService).isNotEmpty();
        Assertions.assertThat(codewarsUserDTOWithHonorFromService.size()).isEqualTo(1);
        Assertions.assertThat(codewarsUserDTOWithHonorFromService).isEqualTo(listOfCodewarsUsersWithHonor);
    }


    @Test
    public void getCodewarsUsersService_GetUsersByLanguage_ReturnsDtoWithHonorWithCertainLanguagesEliminated() {
        Map<String, RankInfo> mapOfLanguages = new HashMap<>();

        RankInfo rankInfoForJavaScript = RankInfo.builder()
                .rank("9 KU")
                .score(9)
                .build();

        RankInfo rankInfoForPython = RankInfo.builder()
                .rank("9 KU")
                .score(10)
                .build();

        RankInfo rankInfoForC = RankInfo.builder()
                .rank("9 KU")
                .score(11)
                .build();

        mapOfLanguages.put("javascript", rankInfoForJavaScript);
        mapOfLanguages.put("python", rankInfoForPython);
        mapOfLanguages.put("C", rankInfoForC);

        Rank rank = Rank.builder()
                .languages(mapOfLanguages)
                .build();

        CodewarsUserDTO codewarsUserDTO = CodewarsUserDTO.builder()
                .name("pikachu")
                .honor(9)
                .ranks(rank)
                .username("pikachu47")
                .build();

        CodewarsUserWithRanksDTO codewarsUserDTOWithRanks = CodewarsUserWithRanksDTO.builder()
                .name("pikachu")
                .username("pikachu47")
                .ranks(rank)
                .build();

        List<CodewarsUserDTO> listOfCodewarsUsers = new ArrayList<>();
        listOfCodewarsUsers.add(codewarsUserDTO);
        List<CodewarsUserWithRanksDTO> listOfCodewarsUsersWithRanks = new ArrayList<>();
        listOfCodewarsUsersWithRanks.add(codewarsUserDTOWithRanks);
        Query query = new Query();
        String language = "python";
        query.addCriteria(Criteria.where(String.format("ranks.languages.%s", language)).exists(true)).with(Sort.by(String.format("ranks.languages.%s.score", language)).descending());
        when(mongoTemplate.find(query,CodewarsUserDTO.class)).thenReturn(listOfCodewarsUsers);

        List<CodewarsUserWithRanksDTO> codewarsUserDTOWithRanksFromService = getCodewarsUsersService.getUsersByLanguage(language);

        Assertions.assertThat(codewarsUserDTOWithRanksFromService).isNotEmpty();
        Assertions.assertThat(codewarsUserDTOWithRanksFromService.size()).isEqualTo(1);
        Assertions.assertThat(codewarsUserDTOWithRanksFromService).isEqualTo(listOfCodewarsUsersWithRanks);
    }
}