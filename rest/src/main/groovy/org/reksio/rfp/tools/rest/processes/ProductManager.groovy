package org.reksio.rfp.tools.rest.processes

import org.reksio.rfp.tools.rest.executors.RESTExecutor
import org.reksio.rfp.tools.rest.requests.CreateCpty
import org.reksio.rfp.tools.rest.requests.CreateProduct
import org.reksio.rfp.tools.rest.types.MongoObject
import org.reksio.rfp.tools.rest.validators.PostIdKeeper
import org.reksio.rfp.tools.smallbusiness.gizmo.Document
import org.reksio.rfp.tools.smallbusiness.types.Cpty
import org.reksio.rfp.tools.smallbusiness.types.Product
import org.reksio.rfp.tools.smallbusiness.types.Rejestr

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

    void create(List<Document> prod_list) {

        List<Product> list = prepareProductsFromDocuments(prod_list)

        list.each { prod ->
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

    private static List<Product> prepareProductsFromDocuments(List<Document> docs) {
        List<Product> products = []

        docs.each { main ->
            if(main.properties['Rejestr'] == Rejestr.MAGAZYN) {
                main.documents.each {
                    products.add(new Product(it))
                }
            }
        }

        return products
    }
}
