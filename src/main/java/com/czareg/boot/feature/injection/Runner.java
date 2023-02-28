package com.czareg.boot.feature.injection;

import com.czareg.boot.feature.injection.implementations.AMapper;
import com.czareg.boot.feature.injection.implementations.BMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final AMapper aMapper;
    private final BMapper bMapper;

    @Override
    public void run(String... args) {
        String string = "TeStStRiNg";
        log.info(aMapper.map(string).toString());
        log.info(bMapper.map(string).toString());
    }
}
