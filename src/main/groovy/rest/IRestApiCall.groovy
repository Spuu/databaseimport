package groovy.rest

import groovyx.net.http.RESTClient

/**
 * Rest Api Call
 */
interface IRestApiCall {
    def call(RESTClient client)
}
