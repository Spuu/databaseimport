package org.reksio.rfp.tools.rest.validators

import groovyx.net.http.HttpResponseDecorator
import org.reksio.rfp.tools.rest.types.MongoObject

/**
 * Storage validator responsible for filling up _id
 */
class PostIdKeeper extends RESTValidator {

    private MongoObject obj

    PostIdKeeper() { }

    def setObject(MongoObject obj) {
        this.obj = obj
    }

    @Override
    def validate(HttpResponseDecorator something) {
        super.validate(something)

        if(obj)
            obj.id = something.data?._id
    }
}
