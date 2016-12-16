package org.reksio.rfp.tools.rest.processes

import org.reksio.rfp.tools.rest.executors.RESTExecutor
import org.reksio.rfp.tools.rest.requests.CreateCpty
import org.reksio.rfp.tools.rest.requests.CreateProduct
import org.reksio.rfp.tools.rest.types.MongoObject
import org.reksio.rfp.tools.rest.validators.PostIdKeeper
import org.reksio.rfp.tools.smallbusiness.gizmo.Document
import org.reksio.rfp.tools.smallbusiness.types.Cpty
import org.reksio.rfp.tools.smallbusiness.types.Product

/**
 * Manages products. Creates object and keep info about Mongo id
 */
class ProductManager {

    RESTExecutor restExecutor
    PostIdKeeper postIdKeeper
    List<MongoObject<Product>> products = []

    ProductManager(RESTExecutor executor, PostIdKeeper validator) {
        this.restExecutor = executor
        this.postIdKeeper = validator
    }

    void create(List<Product> prod_list) {
        prod_list.each { prod ->
            MongoObject<Product> mongoProd = new MongoObject<>(prod)
            postIdKeeper.setObject(mongoProd)
            restExecutor.execute(new CreateProduct(mongoProd.obj), postIdKeeper)
            this.products.add(mongoProd)
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
