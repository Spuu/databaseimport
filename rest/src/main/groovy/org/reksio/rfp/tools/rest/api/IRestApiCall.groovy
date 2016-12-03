package org.reksio.rfp.tools.rest.api

import groovyx.net.http.RESTClient

/**
 * Rest Api Call
 */
interface IRestApiCall {
    def call(RESTClient client)
}
