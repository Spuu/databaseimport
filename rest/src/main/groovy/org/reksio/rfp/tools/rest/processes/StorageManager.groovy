package org.reksio.rfp.tools.rest.processes

import org.reksio.rfp.tools.rest.api.IRestApiCall
import org.reksio.rfp.tools.rest.executors.RESTExecutor
import org.reksio.rfp.tools.rest.requests.CreateStorage
import org.reksio.rfp.tools.rest.types.MongoObject
import org.reksio.rfp.tools.rest.validators.PostIdKeeper
import org.reksio.rfp.tools.smallbusiness.types.Storage

/**
 * Manages storages. Creates object and keep info about Mongo id
 */
class StorageManager extends MongoObjectManager<Storage> {

    private static StorageManager instance

    private StorageManager(RESTExecutor executor, PostIdKeeper validator) {
        super(executor, validator, CreateStorage.class)
    }

    static StorageManager getInstance(RESTExecutor executor, PostIdKeeper validator) {
        if (instance)
            return instance

        instance = new StorageManager(executor, validator)
        return instance
    }

    static StorageManager getInstance() {
        return instance
    }

    void create(List<Storage> list) {
        list.each { elem ->
            MongoObject<Storage> mongoStorage = new MongoObject<>(elem)
            postIdKeeper.setObject(mongoStorage)
            restExecutor.execute((IRestApiCall)apiCallClass.newInstance(elem), postIdKeeper)
            objects.add(mongoStorage)
        }
    }

    String getIdFromNum(String num) {
        for(store in objects) {
            if(store.obj.number == num) {
                return store.id
            }
        }
        System.out.println("Looking for ${num}, but didn't found")
        return null
    }
}
