package constants

enum Environment {
    LOCAL('http://localhost:8080',
            'http://localhost:8080',
            'http://localhost:8080')


    Environment(
            String gradleTemplateHost,
            String wiremockHost,
            String greenmailHost
    ) {

        this.templateHost = gradleTemplateHost
        this.wiremockHost = wiremockHost
        this.greenmailHost = greenmailHost
    }
    String templateHost
    String wiremockHost
    String greenmailHost
}