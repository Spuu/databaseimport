package org.reksio.rfp.tools.rest.requests

import groovyx.net.http.RESTClient
import org.reksio.rfp.tools.rest.api.IRestApiCall
import org.reksio.rfp.tools.smallbusiness.gizmo.Document
import org.reksio.rfp.tools.smallbusiness.types.Cpty

import static groovyx.net.http.ContentType.JSON

/**
 * Create new Storage
 * POST: http(s)://<url>/api/storage
 */
class CreateCpty implements IRestApiCall {

    Cpty cpty

    /**
     * Ctor: takes document and sets props in rest call
     */
    CreateCpty(Document doc) {
        this.cpty = new Cpty(doc)
    }

    /**
     * Ctor: takes storage and sets props in rest call
     */
    CreateCpty(Cpty obj) {
        this.cpty = obj
    }

    /**
     * Execute rest call
     */
    def call(RESTClient client) {
        return client.post(
                path : '/api/cpty',
                body: [
                        name: cpty.name
                ],
                requestContentType : JSON
        )
    }
}
