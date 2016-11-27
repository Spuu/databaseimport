package groovy.rest

import groovy.gizmo.IValidator

/**
 * Validate if rest call succeeded
 */
class RESTValidator implements IValidator {

    enum Result {
        Success,
        Failure,
        None
    }

    def results = []

    RESTValidator() {
    }

    def validate(Object something) {
        results.add(
                something.status == 200 ?
                        Result.Success :
                        Result.Failure
        )
    }
}
