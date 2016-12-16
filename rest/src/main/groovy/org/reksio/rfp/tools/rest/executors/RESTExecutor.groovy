package org.reksio.rfp.tools.rest.executors

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.HttpResponseException
import org.reksio.rfp.tools.rest.api.IValidator
import org.reksio.rfp.tools.rest.api.IRestApiCall
import org.reksio.rfp.tools.rest.api.IRestExecutor
import groovyx.net.http.RESTClient

/**
 * Callback object responsible for executing rest api calls
 */
class RESTExecutor implements IRestExecutor {

    private RESTClient _client

    RESTExecutor(RESTClient client) {
        _client = client
    }

    def execute(IRestApiCall request, IValidator validator) {
        HttpResponseDecorator hrp

        try {
            hrp = request.call(_client)
        } catch (HttpResponseException ex) {
            hrp = ex.getResponse()
        } finally {
            validator.validate(hrp)
        }
    }
}
