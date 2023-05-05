package specs.template

import constants.TestSet
import constants.Variables
import model.example.ExampleRequest
import org.apache.http.HttpStatus
import service.ExampleService
import spock.lang.Requires
import spock.lang.Shared
import spock.lang.Specification

import static java.lang.System.getenv

@Requires({ [TestSet.EXAMPLE.name()].contains(getenv(Variables.TEST_SET.name())) })
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
        response = service.sendExampleRequest(request, [:])

        then:
        assert response.statusCode() == HttpStatus.SC_OK
    }

}
