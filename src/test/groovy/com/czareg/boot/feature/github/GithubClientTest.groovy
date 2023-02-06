package com.czareg.boot.feature.github

import com.czareg.boot.feature.github.exception.GithubClientException
import com.github.tomakehurst.wiremock.WireMockServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import utils.FileUtils

import static com.github.tomakehurst.wiremock.client.WireMock.*

@SpringBootTest(properties = "github.url: http://localhost:5135")
class GithubClientTest extends Specification {

    @Autowired
    GithubClient githubClient

    WireMockServer wireMockServer

    void setup() {
        wireMockServer = new WireMockServer(5135)
        wireMockServer.start()
    }

    def "should successfully get user"() {
        given:
        wireMockServer.stubFor(get(urlPathEqualTo("/users/czarecoo"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(FileUtils.readFileAsString("github/czarecoo.json"))
                ))
        when:
        def user = githubClient.getUser("czarecoo")
        then:
        user.id == 33862699
    }

    def "should throw exception when task is not found"() {
        given:
        wireMockServer.stubFor(get(urlPathEqualTo("/users/czarecoo"))
                .willReturn(aResponse()
                        .withStatus(404)
                ))
        when:
        githubClient.getUser("czarecoo")
        then:
        def exception = thrown(GithubClientException)
        exception.getMessage() == "Github call failed with status: 404"
    }

    void cleanup() {
        wireMockServer.stop()
    }
}
