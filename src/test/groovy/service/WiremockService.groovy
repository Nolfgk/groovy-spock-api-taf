package service

import constants.Environment
import io.restassured.RestAssured
import io.restassured.http.Method
import io.restassured.response.Response

import static constants.Endpoints.WIRE_MOCK_REQUESTS_URL
import static constants.Variables.TARGET_ENVIRONMENT

class WiremockService {
    String environment = System.getenv(TARGET_ENVIRONMENT.name()) ?: Environment.LOCAL.name()
    private String wiremockHost = Environment.valueOf(environment).wiremockHost

    private Response sendRequestWithParameter(String parameterName, String parameterValue, Method httpMethod) {
        def wireMockGetRequest = RestAssured.with()
                .log().all()
                .param(parameterName, parameterValue)
        return wireMockGetRequest.request(httpMethod, wiremockHost + WIRE_MOCK_REQUESTS_URL)
    }

    /**
     * Delete all requests from deployed Wiremock
     */
    void deleteAllRequests() {
        RestAssured.with().delete(wiremockHost + WIRE_MOCK_REQUESTS_URL)
    }
}
