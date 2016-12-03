package org.reksio.rfp.tools.rest.validators

import org.reksio.rfp.tools.rest.api.IValidator

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
