package service

import constants.Environment
import io.restassured.internal.RestAssuredResponseImpl
import io.restassured.response.Response
import model.example.ExampleRequest
import org.apache.http.HttpStatus

class ExampleService extends AbstractBaseService {

    ExampleService() {
        baseServiceHost = Environment.valueOf(environment).templateHost
        serviceName = "Example Service"
    }

    static Response sendExampleRequest(ExampleRequest requestBody, Map headersMap) {
        Response response = new RestAssuredResponseImpl()
        response.setStatusCode(HttpStatus.SC_OK)
        return response

    }
}
