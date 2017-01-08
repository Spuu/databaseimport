package org.reksio.rfp.tools.rest.processes

import org.reksio.rfp.tools.rest.api.IRestApiCall
import org.reksio.rfp.tools.rest.executors.RESTExecutor
import org.reksio.rfp.tools.rest.requests.CreateStorage
import org.reksio.rfp.tools.rest.types.MongoObject
import org.reksio.rfp.tools.rest.validators.PostIdKeeper
import org.reksio.rfp.tools.smallbusiness.gizmo.Document
import org.reksio.rfp.tools.smallbusiness.types.Storage

/**
 * Manages mongo objects. Creates object and keep info about Mongo id
 */
class MongoObjectManager<T> {

    RESTExecutor restExecutor
    PostIdKeeper postIdKeeper
    Class<IRestApiCall> apiCallClass
    List<MongoObject<T>> objects = []

    MongoObjectManager(RESTExecutor executor, PostIdKeeper validator, Class<IRestApiCall> apiCallClass) {
        this.restExecutor = executor
        this.postIdKeeper = validator
        this.apiCallClass = apiCallClass
    }

    MongoObject<T> create(Document doc, Class<T> typeClass) {
        MongoObject<T> mongoObj = new MongoObject<>(doc, typeClass)
        postIdKeeper.setObject(mongoObj)
        restExecutor.execute((IRestApiCall) apiCallClass.newInstance(doc), postIdKeeper)
        objects.add(mongoObj)
        return mongoObj
    }
}
