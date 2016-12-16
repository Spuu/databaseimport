package org.reksio.rfp.tools.rest.processes

import org.reksio.rfp.tools.rest.api.IRestApiCall
import org.reksio.rfp.tools.rest.executors.RESTExecutor
import org.reksio.rfp.tools.rest.requests.CreateStorage
import org.reksio.rfp.tools.rest.types.MongoObject
import org.reksio.rfp.tools.rest.validators.PostIdKeeper
import org.reksio.rfp.tools.smallbusiness.types.Storage

/**
 * Manages mongo objects. Creates object and keep info about Mongo id
 */
class MongoObjectManager<T> {

    RESTExecutor restExecutor
    PostIdKeeper postIdKeeper
    Class<IRestApiCall> apiCallClass
    List<MongoObject<T>> storages = []

    MongoObjectManager(RESTExecutor executor, PostIdKeeper validator, Class<IRestApiCall> apiCallClass) {
        this.restExecutor = executor
        this.postIdKeeper = validator
        this.apiCallClass = apiCallClass
    }

    void create(List<T> store_list) {
        store_list.each { storage ->
            MongoObject<T> mongoStorage = new MongoObject<>(storage)
            postIdKeeper.setObject(mongoStorage)
            restExecutor.execute((IRestApiCall)apiCallClass.newInstance(storage), postIdKeeper)
            this.storages.add(mongoStorage)
        }
    }

    String getIdByName(String name) {
        for(store in storages) {
            if(store.obj.name == name)
                return store.id
        }

        return null
    }
}
