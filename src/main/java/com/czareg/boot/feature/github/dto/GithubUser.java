package com.czareg.boot.feature.github.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.Value;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUser {
    @NonNull
    Long id;
    @NonNull
    String login;
    String name;
    String type;
    @JsonProperty("avatar_url")
    String avatarUrl;
    @JsonProperty("created_at")
    String createdAt;
    Long followers;
    @JsonProperty("public_repos")
    Long publicReposCount;
}