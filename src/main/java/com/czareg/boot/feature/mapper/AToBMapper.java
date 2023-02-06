package com.czareg.boot.feature.mapper;

import com.czareg.boot.feature.mapper.dto.A;
import com.czareg.boot.feature.mapper.dto.B;
import org.mapstruct.Mapper;

@Mapper
public interface AToBMapper {
    B map(A a);
}
