package com.czareg.boot.feature.input;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inputs")
@RequiredArgsConstructor
@Validated
public class InputController {
    @GetMapping
    public List<Input> getAll() {
        return List.of(Input.builder()
                .name("test")
                .build());
    }
}