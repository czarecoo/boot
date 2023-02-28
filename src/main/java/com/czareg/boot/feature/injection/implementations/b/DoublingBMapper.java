package com.czareg.boot.feature.injection.implementations.b;

import com.czareg.boot.feature.injection.abstractions.BElementMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DoublingBMapper implements BElementMapper {
    @Override
    public Optional<String> map(String string) {
        return Optional.of(string + string);
    }
}
