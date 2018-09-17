package com.butola.restconsumer.contract

import com.butola.restconsumer.RestConsumerApplication
import com.jayway.jsonpath.DocumentContext
import com.jayway.jsonpath.JsonPath
import org.assertj.core.api.BDDAssertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.annotation.DirtiesContext
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson

@SpringBootTest(classes = RestConsumerApplication.class)
@DirtiesContext
@AutoConfigureStubRunner(ids = ["com.yogi:restproducer:+:stubs:8080"], stubsMode = StubRunnerProperties.StubsMode.LOCAL, mappingsOutputFolder = "target/outputmappings")
//@AutoConfigureStubRunner(ids = ["com.yogi:restproducer:+:stubs:8080"], stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class RestContractSpec extends Specification {
    @Autowired
    RestTemplate restTemplate

    /**
     * Tests in the consumer has to follow the same sequence in which the contracts are created at the Producer ends.
     * This holds true only when contracts at Producer's end are sequential.
     * @throws Exception
     */
    def validate_1_shouldCreateAnItem() throws Exception {
        given:

        HttpHeaders headers = new HttpHeaders()
        headers.add("Content-Type", "application/json")
        String json = "{\"itemDescription\":\"new description\",\"itemID\":\"2\",\"itemName\":\"newItem\"}"
        HttpEntity<String> entity = new HttpEntity(json, headers)

        when:
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/restproducer", entity, String.class)

        then:
        BDDAssertions.then(responseEntity.statusCode).isEqualTo(HttpStatus.CREATED)
        BDDAssertions.then(responseEntity.headers.get('Content-Type')).isEqualTo(["application/json;charset=UTF-8"])

        /**
         * Following two lines of code shows are the reason for choosing BDDAssertions.
         *
         *         BDDAssertions.then(responseEntity.headers.get('Content-Type')).isEqualTo("application/json;charset=UTF-8")
         *         assert responseEntity.headers.get('Content-Type') == "application/json;charset=UTF-8"
         */

        and:
        DocumentContext parsedJson = JsonPath.parse(responseEntity.body)
    }

    def validate_2_shouldReturnAnItem() throws Exception {
        when:
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/restproducer/2", String.class)

        then:
        BDDAssertions.then(responseEntity.statusCode).isEqualTo(HttpStatus.FOUND)
        BDDAssertions.then(responseEntity.headers.get('Content-Type')).isEqualTo(["application/json;charset=UTF-8"])

        and:
        DocumentContext parsedJson = JsonPath.parse(responseEntity.body)
        assertThatJson(parsedJson).field("['itemName']").isEqualTo("newItem")
        assertThatJson(parsedJson).field("['itemID']").isEqualTo("2")
        assertThatJson(parsedJson).field("['itemDescription']").isEqualTo("new description")
    }
}
