package com.czareg.boot.feature.injection.abstractions;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class Mapper<T extends ElementMapper> {
    private final List<T> elementMappers;

    public List<String> map(String string) {
        return elementMappers.stream()
                .map(mapper -> mapper.map(string))
                .flatMap(Optional::stream)
                .toList();
    }
}
