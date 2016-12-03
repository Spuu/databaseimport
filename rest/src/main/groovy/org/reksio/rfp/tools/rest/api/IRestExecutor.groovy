package org.reksio.rfp.tools.rest.api
/**
 * Abstract callback for executing rest call
 */
interface IRestExecutor {
    def execute(IRestApiCall request, IValidator validator)
}