package groovy.rest

import groovy.gizmo.Document
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

/**
 * Create new product
 * POST: http(s)://<url>/api/product
 */
class CreateProduct implements IRestApiCall {

    static final String NONE = 'Undefined'
    static final String NAME = 'Nazwa'
    static final String EAN = 'Symbol'

    String name
    String ean

    /**
     * Ctor: takes document and sets props in rest call
     */
    CreateProduct(Document doc) {
        name = doc.properties[NAME] ?: NONE
        ean = doc.properties[EAN] ?: NONE
    }

    /**
     * Execute rest call
     */
    def call(RESTClient client) {
        return client.post(
                path : '/api/product',
                body: [
                        name: name,
                        ean : ean
                ],
                requestContentType : JSON
        )
    }
}
