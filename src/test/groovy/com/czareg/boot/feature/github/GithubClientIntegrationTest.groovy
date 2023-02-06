package com.czareg.boot.feature.github


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class GithubClientIntegrationTest extends Specification {

    @Autowired
    GithubClient githubClient

    def "should get user with correct id"() {
        when:
        def user = githubClient.getUser("czarecoo")
        then:
        user.id == 33862699
    }
}
