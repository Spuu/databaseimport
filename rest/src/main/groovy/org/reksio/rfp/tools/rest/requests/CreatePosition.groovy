package org.reksio.rfp.tools.rest.requests

import groovyx.net.http.RESTClient
import org.reksio.rfp.tools.rest.api.IRestApiCall
import org.reksio.rfp.tools.rest.processes.CptyManager
import org.reksio.rfp.tools.rest.processes.ProductManager
import org.reksio.rfp.tools.rest.processes.StorageManager
import org.reksio.rfp.tools.smallbusiness.gizmo.Document
import org.reksio.rfp.tools.smallbusiness.types.Invoice
import org.reksio.rfp.tools.smallbusiness.types.Position

import static groovyx.net.http.ContentType.JSON

/**
 * Create new Position
 * POST: http(s)://<url>/api/position
 */
class CreatePosition implements IRestApiCall {

    Position position

    /**
     * Ctor: takes document and sets props in rest call
     */
    CreatePosition(Document doc) {
        this.position = new Position(doc)
    }

    /**
     * Ctor: takes product and sets props in rest call
     */
    CreatePosition(Position obj) {
        this.position = obj
    }

    /**
     * Execute rest call
     */
    def call(RESTClient client) {
        return client.post(
                path : '/api/position',
                body: [
                        _invoice: position.invoiceId,
                        _store: position.storeId,
                        _product: ProductManager.getInstance().getIdByEan(position.symbol) ?:
                                ProductManager.getInstance().getIdByName(position.name),
                        index: position.idx,
                        buy_netto_price: position.buyNettoPrice,
                        sell_brutto_price: position.sellBruttoPrice,
                        quantity: 1
                ],
                requestContentType : JSON
        )
    }
}
