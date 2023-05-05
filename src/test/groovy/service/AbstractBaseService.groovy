package service

import io.restassured.RestAssured
import io.restassured.config.EncoderConfig
import io.restassured.config.HttpClientConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.http.Method
import io.restassured.response.Response

import static constants.Environment.LOCAL
import static constants.Variables.TARGET_ENVIRONMENT
import static io.restassured.http.Method.GET
import static io.restassured.http.Method.POST

abstract class AbstractBaseService {

    static final String environment = System.getenv(TARGET_ENVIRONMENT.name()) ?: LOCAL.name()
    protected static final String SERVICE_HEALTH_CHECK = ""
    protected String baseServiceHost
    protected String serviceName

    protected RestAssuredConfig restConf = RestAssuredConfig.newConfig()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam("http.socket.timeout", 30000)
                    .setParam("http.connection.timeout", 30000))
            .encoderConfig(new EncoderConfig()
                    .appendDefaultContentCharsetToContentTypeIfUndefined(
                            false))

    void checkServiceStatus() {
        println("Checking service status for $serviceName")
    }

    Response postEmptyBody(String Url, Map headers = getValidHeaders()) {
        return sendHttpRequest(null, POST, headers, baseServiceHost + Url)
    }

    Response sendEmptyBody(Method method, String Url, Map headers = getValidHeaders()) {
        return sendHttpRequest(null, method, headers, baseServiceHost + Url)
    }

    Response sendRequestWithHeaders(Object requestBody, Method httpMethod, Map headersMap, String url) {
        return sendHttpRequest(requestBody, httpMethod, headersMap, baseServiceHost + url)
    }

    Response sendHttpRequest(Object requestBody, Method httpMethod, Map headersMap, String url) {
        println("Sending http request.")
        def request = RestAssured.with()
                .config(restConf)
                .headers(headersMap)
                .filter(new RequestLoggingFilter())

        if (requestBody != null) {
            request.body(requestBody)
        }

        println("<=== SEND REQUEST:")
        Response resp = request.request(httpMethod, url)
        println("<=== GET RESPONSE:")
        println(resp.statusCode())
        resp.prettyPrint()
        return resp
    }

    Response getWithQuery(Map queryParams, Map headersMap, String url) {
        println("Sending http request.")
        def request = RestAssured.with()
                .config(restConf)
                .queryParams(queryParams)
                .headers(headersMap)
                .filter(new RequestLoggingFilter())


        println("<=== SEND REQUEST:")
        Response resp = request.request(GET, url)
        println("<=== GET RESPONSE:")
        println(resp.statusCode())
        resp.prettyPrint()
        return resp
    }

}
