package org.reksio.rfp.tools.rest.processes

import org.reksio.rfp.tools.rest.executors.RESTExecutor
import org.reksio.rfp.tools.rest.requests.CreateInvoice
import org.reksio.rfp.tools.rest.validators.PostIdKeeper
import org.reksio.rfp.tools.smallbusiness.gizmo.Document
import org.reksio.rfp.tools.smallbusiness.types.Invoice
import org.reksio.rfp.tools.smallbusiness.types.Rejestr

/**
 * Manages invoices. Creates object and keep info about Mongo id
 */
class InvoiceManager extends MongoObjectManager<Invoice> {

    private static InvoiceManager instance

    private InvoiceManager(RESTExecutor executor, PostIdKeeper validator) {
        super(executor, validator, CreateInvoice.class)
    }

    static InvoiceManager getInstance(RESTExecutor executor, PostIdKeeper validator) {
        if (instance)
            return instance

        instance = new InvoiceManager(executor, validator)
        return instance
    }

    void create(List<Document> list) {
        list.each { main ->
            if (main.properties[Rejestr.REJESTR] in [Rejestr.FAKTURY_ZAK]) {
                super.create(main, Invoice.class)
            }
        }
    }
}
