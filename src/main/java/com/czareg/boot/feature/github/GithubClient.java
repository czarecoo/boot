package com.czareg.boot.feature.github;

import com.czareg.boot.feature.github.dto.GithubUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class GithubClient {

    private static final String GET_USER_ENDPOINT_PATH = "users/{userName}";
    private final WebClient githubWebClient;

    public GithubUser getUser(String userName) {
        return githubWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(GET_USER_ENDPOINT_PATH)
                        .build(userName))
                .retrieve()
                .bodyToMono(GithubUser.class)
                .block();
    }
}
