package org.reksio.rfp.tools.rest.requests

import groovyx.net.http.RESTClient
import org.reksio.rfp.tools.rest.api.IRestApiCall
import org.reksio.rfp.tools.rest.processes.CptyManager
import org.reksio.rfp.tools.rest.processes.StorageManager
import org.reksio.rfp.tools.smallbusiness.gizmo.Document
import org.reksio.rfp.tools.smallbusiness.types.Invoice
import org.reksio.rfp.tools.smallbusiness.types.Product
import org.reksio.rfp.tools.smallbusiness.types.Rejestr

import static groovyx.net.http.ContentType.JSON

/**
 * Create new Invoice
 * POST: http(s)://<url>/api/invoice
 */
class CreateInvoice implements IRestApiCall {

    Map typ = [ 'FakturyZak' : 'Buy',
                'FakturySpr' : 'Sell']

    Invoice invoice

    /**
     * Ctor: takes document and sets props in rest call
     */
    CreateInvoice(Document doc) {
        this.invoice = new Invoice(doc)
    }

    /**
     * Ctor: takes product and sets props in rest call
     */
    CreateInvoice(Invoice obj) {
        this.invoice = obj
    }

    /**
     * Execute rest call
     */
    def call(RESTClient client) {
        return client.post(
                path : '/api/invoice',
                body: [
                        _cpty: CptyManager.getInstance().getIdFromNum(invoice.cptyNum),
                        name: invoice.getDocNum(),
                        _store: StorageManager.getInstance().getIdFromNum(invoice.getStorageId()),
                        document_date: invoice.getDate(),
                        type: typ[invoice.properties[Rejestr.REJESTR]]
                ],
                requestContentType : JSON
        )
    }
}
