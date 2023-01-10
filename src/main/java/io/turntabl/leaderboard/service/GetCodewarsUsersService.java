package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.dto.CodewarsUserDTO;
import io.turntabl.leaderboard.repository.CodewarsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetCodewarsUsersService {
    private final CodewarsRepository codewarsRepository;
    public List<CodewarsUserDTO> getUsersByHonorDescending(){
        List<CodewarsUserDTO> existingCodewarsUsersSortedDesc = codewarsRepository.findAll(Sort.by(Sort.Direction.DESC,"honor"));
        return existingCodewarsUsersSortedDesc.stream().map(
                existingCodewarsUser -> CodewarsUserDTO.builder().clan(existingCodewarsUser.getClan())
                        .ranks(existingCodewarsUser.getRanks())
                        .name(existingCodewarsUser.getName())
                        .username(existingCodewarsUser.getUsername())
                        .comments(existingCodewarsUser.getComments())
                        .honor(existingCodewarsUser.getHonor())
                        .build()
                 ).toList();
    };

    public List<CodewarsUserDTO> getUsersByOverallScoreDescending(){
        List<CodewarsUserDTO> existingCodewarsUsersSortedDesc = codewarsRepository.findAll(Sort.by(Sort.Direction.DESC,"ranks.overall.score"));
        return existingCodewarsUsersSortedDesc.stream().map(
                existingCodewarsUser -> CodewarsUserDTO.builder().clan(existingCodewarsUser.getClan())
                        .ranks(existingCodewarsUser.getRanks())
                        .name(existingCodewarsUser.getName())
                        .username(existingCodewarsUser.getUsername())
                        .comments(existingCodewarsUser.getComments())
                        .honor(existingCodewarsUser.getHonor())
                        .build()
        ).toList();
    };


}
