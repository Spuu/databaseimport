package org.reksio.rfp.tools.rest.validators

import groovyx.net.http.HttpResponseDecorator
import org.reksio.rfp.tools.smallbusiness.types.Storage

/**
 * Storage validator responsible for filling up _id
 */
class StorageValidator extends RESTValidator {

    Storage storage

    StorageValidator(Storage storage) {
        this.storage = storage
    }

    @Override
    def validate(HttpResponseDecorator something) {
        super.validate(something)

        storage.id = something.data._id
    }
}
