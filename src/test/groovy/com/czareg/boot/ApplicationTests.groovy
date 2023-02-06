package com.czareg.boot

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ApplicationTests extends Specification {

    def "should create spring boot context"() {
        expect: "context is created without errors"
    }

    def "should run application and throw no exceptions"() {
        when:
        BootApplication.main(new String[]{})
        then:
        noExceptionThrown()
    }
}

