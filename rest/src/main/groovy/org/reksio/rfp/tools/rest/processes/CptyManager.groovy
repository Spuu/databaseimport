package org.reksio.rfp.tools.rest.processes

import org.reksio.rfp.tools.rest.executors.RESTExecutor
import org.reksio.rfp.tools.rest.requests.CreateCpty
import org.reksio.rfp.tools.rest.types.MongoObject
import org.reksio.rfp.tools.rest.validators.PostIdKeeper
import org.reksio.rfp.tools.smallbusiness.types.Cpty

/**
 * Manages cpty. Creates object and keep info about Mongo id
 */
class CptyManager {

    RESTExecutor restExecutor
    PostIdKeeper postIdKeeper
    List<MongoObject<Cpty>> cpties = []

    CptyManager(RESTExecutor executor, PostIdKeeper validator) {
        this.restExecutor = executor
        this.postIdKeeper = validator
    }

    void create(List<Cpty> cpty_list) {
        cpty_list.each { cpty ->
            MongoObject<Cpty> mongoCpty = new MongoObject<>(cpty)
            postIdKeeper.setObject(mongoCpty)
            restExecutor.execute(new CreateCpty(mongoCpty.obj), postIdKeeper)
            this.cpties.add(mongoCpty)
        }
    }

    String getIdByName(String name) {
        for(cpty in cpties) {
            if(cpty.obj.name == name)
                return cpty.id
        }

        return null
    }
}
