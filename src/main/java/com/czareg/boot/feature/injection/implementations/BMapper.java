package com.czareg.boot.feature.injection.implementations;

import com.czareg.boot.feature.injection.abstractions.BElementMapper;
import com.czareg.boot.feature.injection.abstractions.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BMapper extends Mapper<BElementMapper> {
    public BMapper(List<BElementMapper> elementMappers) {
        super(elementMappers);
    }
}
