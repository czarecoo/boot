package com.czareg.boot.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TraceLogFilterContextTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void applicationContextShouldContainOneBeanOfTraceLogFilterType() {
        String[] beans = applicationContext.getBeanNamesForType(TraceLogFilter.class);

        assertNotNull(beans);
        assertEquals(1, beans.length);
    }
}