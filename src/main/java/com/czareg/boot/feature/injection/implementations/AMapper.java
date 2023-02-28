package com.czareg.boot.feature.injection.implementations;

import com.czareg.boot.feature.injection.abstractions.AElementMapper;
import com.czareg.boot.feature.injection.abstractions.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AMapper extends Mapper<AElementMapper> {
    public AMapper(List<AElementMapper> elementMappers) {
        super(elementMappers);
    }
}
