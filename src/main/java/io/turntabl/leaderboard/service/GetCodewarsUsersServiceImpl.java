package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.dto.CodewarsUserWithHonorDTO;
import io.turntabl.leaderboard.dto.CodewarsUserWithRanksDTO;
import io.turntabl.leaderboard.dto.ResponseDTO;
import io.turntabl.leaderboard.exceptions.UserNotFoundException;
import io.turntabl.leaderboard.repository.CodewarsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
@AllArgsConstructor
public class GetCodewarsUsersServiceImpl implements GetCodewarsUsersService {
    private final CodewarsRepository codewarsRepository;

    private final MongoTemplate mongoTemplate;

    public List<CodewarsUserWithHonorDTO> getUsersByHonorDescending() {
        List<CodewarsUserDTO> existingCodewarsUsersSortedDesc = codewarsRepository.findAll(Sort.by(Sort.Direction.DESC, "honor"));

        if (existingCodewarsUsersSortedDesc.size() == 0) {
            throw new UserNotFoundException("No users found");
        }

        return existingCodewarsUsersSortedDesc.stream().map(
                existingCodewarsUser -> CodewarsUserWithHonorDTO.builder()
                        .codewarsId(existingCodewarsUser.getId())
                        .name(existingCodewarsUser.getName())
                        .username(existingCodewarsUser.getUsername())
                        .honor(existingCodewarsUser.getHonor())
                        .build()
        ).toList();

    }

    public List<CodewarsUserWithRanksDTO> getUsersByOverallScoreDescending() {
        List<CodewarsUserDTO> existingCodewarsUsersSortedDesc = codewarsRepository.findAll(Sort.by(Sort.Direction.DESC, "ranks.overall.score"));
        if (existingCodewarsUsersSortedDesc.size() == 0) {
            throw new UserNotFoundException("No users found");
        }
        return existingCodewarsUsersSortedDesc.stream().map(
                existingCodewarsUser -> CodewarsUserWithRanksDTO.builder()
                        .codewarsId(existingCodewarsUser.getId())
                        .name(existingCodewarsUser.getName())
                        .username(existingCodewarsUser.getUsername())
                        .ranks(existingCodewarsUser.getRanks())
                        .build()
        ).toList();
    }

    public List<CodewarsUserWithRanksDTO> getUsersByLanguage(String language) {
        Query query = new Query();
        query.addCriteria(Criteria.where(String.format("ranks.languages.%s", language)).exists(true)).with(Sort.by(String.format("ranks.languages.%s.score", language)).descending());
        List<CodewarsUserDTO> codewarsUsersWithFilteredLanguage = mongoTemplate.find(query, CodewarsUserDTO.class);

        if (codewarsUsersWithFilteredLanguage.size() == 0) {
            throw new UserNotFoundException("No users found");
        }

        codewarsUsersWithFilteredLanguage.forEach(codewarsUserDTO -> codewarsUserDTO.getRanks().getLanguages().keySet().removeIf(key -> !Objects.equals(key, language)));

        return codewarsUsersWithFilteredLanguage.stream().map(
                existingCodewarsUser -> CodewarsUserWithRanksDTO.builder()
                        .codewarsId(existingCodewarsUser.getId())
                        .ranks(existingCodewarsUser.getRanks())
                        .name(existingCodewarsUser.getName())
                        .username(existingCodewarsUser.getUsername())
                        .build()
        ).toList();
    }

    @Override
    public ResponseDTO getUsersByOverallByFilter(String sortBy) {
        if (sortBy.equals("overall")) {
            return ResponseDTO.builder()
                            .success(true)
                            .data(Map.of("data", this.getUsersByOverallScoreDescending()))
                            .build();
        }
        return ResponseDTO.builder()
                        .success(true)
                        .data(Map.of("data", this.getUsersByLanguage(sortBy)))
                        .build();
    }
}