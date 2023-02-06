package com.czareg.boot.feature.mapper


import com.czareg.boot.feature.mapper.dto.A
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class AToBMapperTest extends Specification {

    @Autowired
    AToBMapper aToBMapper

    def "should get user with correct id"() {
        given:
        def a = A.builder()
                .id(5)
                .name("name")
                .build()
        when:
        def result = aToBMapper.map(a)
        then:
        result.id == 5
        result.name == "name"
    }
}
