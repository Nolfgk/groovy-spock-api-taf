package exceptions

class ServiceNotAvailableException extends RuntimeException {

    ServiceNotAvailableException(String message) {
        super(message)
    }
}
