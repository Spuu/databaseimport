package org.reksio.rfp.tools.rest.api

import groovyx.net.http.HttpResponseDecorator

/**
 * Validate something
 */
interface IValidator {
    def validate(HttpResponseDecorator something)
}