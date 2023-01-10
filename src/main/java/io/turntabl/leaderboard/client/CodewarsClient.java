package io.turntabl.leaderboard.client;


import io.turntabl.leaderboard.model.CodewarsUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;



@Service
@Slf4j
@RequiredArgsConstructor
public class CodewarsClient {
    private final RestTemplateBuilder restTemplateBuilder;

    @Value("${codewars.url.get.user}")
    private String baseUrl;

    public CodewarsUser getCodewarsUser(String username) {
        return restTemplateBuilder.build()
                .getForObject(baseUrl + username,
                        CodewarsUser.class);
    }
}
