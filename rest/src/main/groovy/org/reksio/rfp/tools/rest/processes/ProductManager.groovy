package org.reksio.rfp.tools.rest.processes

import org.reksio.rfp.tools.rest.executors.RESTExecutor
import org.reksio.rfp.tools.rest.requests.CreateProduct
import org.reksio.rfp.tools.rest.validators.PostIdKeeper
import org.reksio.rfp.tools.smallbusiness.gizmo.Document
import org.reksio.rfp.tools.smallbusiness.types.Product
import org.reksio.rfp.tools.smallbusiness.types.Rejestr

/**
 * Manages products. Creates object and keep info about Mongo id
 */
class ProductManager extends MongoObjectManager<Product> {

    private static ProductManager instance

    private ProductManager(RESTExecutor executor, PostIdKeeper validator) {
        super(executor, validator, CreateProduct.class)
    }

    static ProductManager getInstance(RESTExecutor executor, PostIdKeeper validator) {
        if (instance)
            return instance

        instance = new ProductManager(executor, validator)
        return instance
    }

    void create(List<Document> list) {
        list.each { main ->
            if (main.properties[Rejestr.REJESTR] == Rejestr.MAGAZYN) {
                super.create(main.documents, Product.class)
            }
        }
    }

    String getIdByEan(String ean) {
        for(prod in products) {
            if(prod.obj.symbol == ean)
                return prod.id
        }

        return null
    }
}
