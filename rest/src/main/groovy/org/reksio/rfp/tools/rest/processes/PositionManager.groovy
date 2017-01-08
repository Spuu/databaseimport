package org.reksio.rfp.tools.rest.processes

import org.reksio.rfp.tools.rest.executors.RESTExecutor
import org.reksio.rfp.tools.rest.requests.CreateInvoice
import org.reksio.rfp.tools.rest.requests.CreatePosition
import org.reksio.rfp.tools.rest.types.MongoObject
import org.reksio.rfp.tools.rest.validators.PostIdKeeper
import org.reksio.rfp.tools.smallbusiness.gizmo.Document
import org.reksio.rfp.tools.smallbusiness.types.Invoice
import org.reksio.rfp.tools.smallbusiness.types.Position
import org.reksio.rfp.tools.smallbusiness.types.Rejestr

/**
 * Manages positions. Creates object and keep info about Mongo id
 */
class PositionManager extends MongoObjectManager<Invoice> {

    private static PositionManager instance

    private PositionManager(RESTExecutor executor, PostIdKeeper validator) {
        super(executor, validator, CreatePosition.class)
    }

    static PositionManager getInstance(RESTExecutor executor, PostIdKeeper validator) {
        if (instance)
            return instance

        instance = new PositionManager(executor, validator)
        return instance
    }

    static PositionManager getInstance() {
        return instance
    }

    void create(MongoObject<Invoice> mInvoice, List<Document> list) {
        list.eachWithIndex { poz, idx ->
            poz.properties.put('idx', idx.toString())
            poz.properties.put('invoiceId', mInvoice.id)
            poz.properties.put('storeId', StorageManager.getInstance().getIdFromNum(mInvoice.obj.getStorageId()))
            super.create(poz, Position.class)
        }
    }
}
