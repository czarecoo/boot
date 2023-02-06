package com.czareg.boot.feature.github.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GithubClientException extends RuntimeException {
    private final String message;
    private final int status;
    private final String body;
}
