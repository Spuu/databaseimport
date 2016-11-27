package groovy.tiara

import groovy.gizmo.IValidator
import groovy.rest.IRestApiCall
import groovy.rest.IRestExecutor
import groovyx.net.http.RESTClient

/**
 * Callback object responsible for executing rest api calls from Tiara
 */
class TiarasExecutor implements IRestExecutor {

    private RESTClient _client

    TiarasExecutor(RESTClient client) {
        _client = client
    }

    def execute(IRestApiCall request, IValidator validator) {
        validator.validate(request.call(_client))
    }
}
