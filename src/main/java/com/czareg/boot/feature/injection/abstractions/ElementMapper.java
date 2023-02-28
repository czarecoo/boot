package com.czareg.boot.feature.injection.abstractions;

import java.util.Optional;

public interface ElementMapper {

    Optional<String> map(String string);
}
