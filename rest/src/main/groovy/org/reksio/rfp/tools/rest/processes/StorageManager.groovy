package org.reksio.rfp.tools.rest.processes

import groovyx.net.http.RESTClient
import org.reksio.rfp.tools.rest.api.IValidator
import org.reksio.rfp.tools.rest.executors.RESTExecutor
import org.reksio.rfp.tools.rest.requests.CreateStorage
import org.reksio.rfp.tools.rest.types.MongoObject
import org.reksio.rfp.tools.rest.validators.PostIdKeeper
import org.reksio.rfp.tools.smallbusiness.types.Storage

/**
 * Manages storages. Creates object and keep info about Mongo id
 */
class StorageManager {

    RESTExecutor restExecutor
    PostIdKeeper postIdKeeper
    List<MongoObject<Storage>> storages = []

    StorageManager(RESTExecutor executor, PostIdKeeper validator) {
        this.restExecutor = executor
        this.postIdKeeper = validator
    }

    void createStorages(List<Storage> store_list) {
        store_list.each { storage ->
            MongoObject<Storage> mongoStorage = new MongoObject<>(storage)
            postIdKeeper.setObject(mongoStorage)
            restExecutor.execute(new CreateStorage(mongoStorage.obj), postIdKeeper)
            this.storages.add(mongoStorage)
            System.out.println("Name: ${mongoStorage.obj.name}, id: ${mongoStorage.id}")
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
