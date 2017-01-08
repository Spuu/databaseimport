package org.reksio.rfp.tools.rest.processes

import org.reksio.rfp.tools.rest.executors.RESTExecutor
import org.reksio.rfp.tools.rest.requests.CreateProduct
import org.reksio.rfp.tools.rest.types.MongoObject
import org.reksio.rfp.tools.rest.validators.PostIdKeeper
import org.reksio.rfp.tools.smallbusiness.gizmo.Document
import org.reksio.rfp.tools.smallbusiness.types.Product
import org.reksio.rfp.tools.smallbusiness.types.Rejestr

/**
 * Manages products. Creates object and keep info about Mongo id
 */
class ProductManager extends MongoObjectManager<Product> {

    private static ProductManager instance

    Map<String, MongoObject<Product>> inserted = [:]

    private ProductManager(RESTExecutor executor, PostIdKeeper validator) {
        super(executor, validator, CreateProduct.class)
    }

    static ProductManager getInstance(RESTExecutor executor, PostIdKeeper validator) {
        if (instance)
            return instance

        instance = new ProductManager(executor, validator)
        return instance
    }

    static ProductManager getInstance() {
        return instance
    }

    void create(List<Document> list) {

        List<Document> filtered = list.each { main ->
            if (main.properties[Rejestr.REJESTR] == Rejestr.MAGAZYN) {
                main.documents.each { prod ->
                    if (!inserted.containsKey(prod.properties['Symbol'])) {
                        MongoObject<Product> mProduct = super.create(prod, Product.class)
                        if (mProduct.id)
                            inserted.put(mProduct.obj.symbol, mProduct)
                    }
                }
            }
        }
    }

    String getIdByEan(String ean) {
        for(prod in objects) {
            if(prod.obj.symbol == ean)
                return prod.id
        }

        return null
    }

    String getIdByName(String name) {
        for(prod in objects) {
            if(prod.obj.name == name) {
                System.out.println("Found by name ${name}")
                return prod.id
            }
        }
        System.out.println("Not Found: ${name}")
        return null
    }
}
