package org.reksio.rfp.tools.rest.processes

import org.reksio.rfp.tools.rest.api.IRestApiCall
import org.reksio.rfp.tools.rest.executors.RESTExecutor
import org.reksio.rfp.tools.rest.requests.CreateCpty
import org.reksio.rfp.tools.rest.types.MongoObject
import org.reksio.rfp.tools.rest.validators.PostIdKeeper
import org.reksio.rfp.tools.smallbusiness.types.Cpty

/**
 * Manages cpty. Creates object and keep info about Mongo id
 */
class CptyManager extends MongoObjectManager<Cpty> {

    private static CptyManager instance

    private CptyManager(RESTExecutor executor, PostIdKeeper validator) {
        super(executor, validator, CreateCpty.class)
    }

    static CptyManager getInstance(RESTExecutor executor, PostIdKeeper validator) {
        if (instance)
            return instance

        instance = new CptyManager(executor, validator)
        return instance
    }

    static CptyManager getInstance() {
        return instance
    }

    void create(List<Cpty> list) {
        list.each { elem ->
            MongoObject<Cpty> mongoStorage = new MongoObject<>(elem)
            postIdKeeper.setObject(mongoStorage)
            restExecutor.execute((IRestApiCall)apiCallClass.newInstance(elem), postIdKeeper)
            objects.add(mongoStorage)
        }
    }

    String getIdFromNum(String num) {
        for(cpty in objects) {
            if(num in cpty.obj.numbers) {
                return cpty.id
            }
        }
        System.out.println("Looking for ${num}, but didn't found.")
        return null
    }
}
