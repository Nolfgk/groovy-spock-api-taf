package specs.template


import model.example.ExampleRequest
import org.apache.http.HttpStatus
import service.ExampleService
import spock.lang.Requires
import spock.lang.Shared
import spock.lang.Specification

import static constants.TestSet.EXAMPLE
import static constants.Variables.TEST_SET
import static java.lang.System.getenv

@Requires({ [EXAMPLE.name()].contains(getenv(TEST_SET.name())) })
class ExampleSpec extends Specification {

    @Shared
    def service = new ExampleService()
    @Shared
    Random random = new Random()

    def setupSpec() {
        service.checkServiceStatus()
    }

    def "Test template request"() {
        given:
        ExampleRequest request = new ExampleRequest(id: random.nextInt(999999),
                name: "Mylo", tag: "Silver")
        def response
        when:
        response = service.sendTemplateRequest(request, [:])

        then:
        assert response.statusCode() == HttpStatus.SC_OK
        assert response.jsonPath().getString("statusName") == "Created"
    }

}
