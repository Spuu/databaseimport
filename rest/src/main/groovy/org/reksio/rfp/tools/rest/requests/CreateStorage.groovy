package org.reksio.rfp.tools.rest.requests

import groovyx.net.http.RESTClient
import org.reksio.rfp.tools.rest.api.IRestApiCall
import org.reksio.rfp.tools.smallbusiness.gizmo.Document
import org.reksio.rfp.tools.smallbusiness.types.Storage

import static groovyx.net.http.ContentType.*

/**
 * Create new Storage
 * POST: http(s)://<url>/api/storage
 */
class CreateStorage implements IRestApiCall {

    Storage storage

    /**
     * Ctor: takes document and sets props in rest call
     */
    CreateStorage(Document doc) {
        this.storage = new Storage(doc)
    }

    /**
     * Ctor: takes storage and sets props in rest call
     */
    CreateStorage(Storage obj) {
        this.storage = obj
    }

    /**
     * Execute rest call
     */
    def call(RESTClient client) {
        return client.post(
                path : '/api/store',
                body: [
                        name: storage.name
                ],
                requestContentType : JSON
        )
    }
}
