package service


import constants.Environment
import io.restassured.http.Method
import io.restassured.response.Response
import model.example.ExampleRequest

import static constants.Endpoints.EXAMPLE_URL

class ExampleService extends AbstractBaseService {

    ExampleService() {
        baseServiceHost = Environment.valueOf(environment).templateHost
        serviceName = "Example Service"
    }

    Response sendTemplateRequest(ExampleRequest requestBody, Map headersMap) {
        return sendHttpRequest(requestBody, Method.POST, headersMap, baseServiceHost + EXAMPLE_URL)
    }
}
