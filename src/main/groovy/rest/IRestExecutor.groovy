package groovy.rest

import groovy.gizmo.IValidator

/**
 * Abstract callback for executing rest call
 */
interface IRestExecutor {
    def execute(IRestApiCall request, IValidator validator)
}