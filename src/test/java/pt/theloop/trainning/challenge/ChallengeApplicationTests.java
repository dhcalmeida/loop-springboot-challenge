package pt.theloop.trainning.challenge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;
import pt.theloop.trainning.challenge.models.views.PVPView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChallengeApplicationTests {

    private final long productId;

    private final long brandId;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    private static final String serverContextPath = "/api/v1/";

    @LocalServerPort
    private int port;

    private List<PVPView> allData;

    private String baseUrl;

    public ChallengeApplicationTests() {
        productId = 35455;
        brandId = 1;
    }

    @BeforeAll
    private void setUp() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:data/expectedRecordset.json");
        allData = objectMapper.readValue(resource.getFile(), new TypeReference<>() {
        });
        baseUrl = "http://localhost:" + port + serverContextPath + "prices/";
    }

    @Test
    void contextLoads() {
        assertThat(objectMapper).isNotNull();
    }

    @Test
    void pricesRouteShouldReturnAllRecordsInDb() throws JsonProcessingException {
        String dataStr = this.restTemplate.getForObject(baseUrl, String.class);
        List<PVPView> data = objectMapper.readValue(dataStr, new TypeReference<>() {
        });
        assertEquals(data, allData);
    }

    @Test
    void recordNotFoundShouldReturnHttpStatusNotFound() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 6, 14, 10, 00);
        assertEquals(HttpStatus.NOT_FOUND, doTestCaseRequest(dateTime).getStatusCode());
    }

    @Test
    void invalidParameterShouldReturnHttpStatusBadRequest() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 6, 14, 10, 00, 00, 01);
        assertEquals(HttpStatus.BAD_REQUEST, doTestCaseRequest(dateTime).getStatusCode());
    }

    @Test
    void challengeTestCase1() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 14, 10, 00);
        assertEquals(allData.get(0), doTestCaseRequest(dateTime).getBody());
    }

    @Test
    void challengeTestCase2() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 14, 16, 00);
        assertEquals(allData.get(1), doTestCaseRequest(dateTime).getBody());
    }

    @Test
    void challengeTestCase3() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 14, 21, 00);
        assertEquals(allData.get(0), doTestCaseRequest(dateTime).getBody());
    }

    @Test
    void challengeTestCase4() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 15, 10, 00);
        assertEquals(allData.get(2), doTestCaseRequest(dateTime).getBody());
    }

    @Test
    void challengeTestCase5() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 16, 21, 00);
        assertEquals(allData.get(3), doTestCaseRequest(dateTime).getBody());
    }

    private ResponseEntity<PVPView> doTestCaseRequest(LocalDateTime dateTime) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "brands/{brandId}/products/{productId}")
                .queryParam("dateTime", dateTime);
        Map<String, Object> urlParams = new HashMap<>();
        urlParams.put("brandId", brandId);
        urlParams.put("productId", productId);

        return restTemplate.exchange(
                builder.buildAndExpand(urlParams).toUri(),
                HttpMethod.GET,
                entity,
                PVPView.class
        );
    }

}