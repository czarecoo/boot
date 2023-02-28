package com.czareg.boot.feature.injection.implementations.a;

import com.czareg.boot.feature.injection.abstractions.AElementMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ToUpperCaseAMapper implements AElementMapper {
    @Override
    public Optional<String> map(String string) {
        return Optional.of(string.toUpperCase());
    }
}
