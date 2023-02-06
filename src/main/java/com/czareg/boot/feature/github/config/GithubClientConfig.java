package com.czareg.boot.feature.github.config;

import com.czareg.boot.feature.github.exception.GithubClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class GithubClientConfig {
    private static final String ERROR_MSG = "Github call failed with status: %s";
    private final GithubConfigurationProperties properties;

    @Bean("githubWebClient")
    public WebClient getWebClient(WebClient.Builder builder) {
        return builder
                .filter(errorHandler())
                .baseUrl(properties.getUrl())
                .build();
    }

    private ExchangeFilterFunction errorHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().isError()) {
                final var message = String.format(ERROR_MSG, clientResponse.statusCode().value());
                return clientResponse.bodyToMono(String.class)
                        .switchIfEmpty(Mono.error(GithubClientException.builder()
                                .message(message)
                                .status(clientResponse.statusCode().value())
                                .build()))
                        .flatMap(errorBody -> Mono.error(GithubClientException.builder()
                                .message(message)
                                .status(clientResponse.statusCode().value())
                                .body(errorBody)
                                .build()));
            }
            return Mono.just(clientResponse);
        });
    }
}
