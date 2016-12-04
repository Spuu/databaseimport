package org.reksio.rfp.tools.rest.processes

import groovyx.net.http.RESTClient
import org.reksio.rfp.tools.rest.api.IValidator
import org.reksio.rfp.tools.rest.executors.RESTExecutor
import org.reksio.rfp.tools.rest.requests.CreateStorage
import org.reksio.rfp.tools.rest.validators.StorageValidator
import org.reksio.rfp.tools.smallbusiness.types.Storage

/**
 * Created by spuu on 04.12.16.
 */
class StorageManager {

    RESTClient client

    List<Storage> storages = []

    /**
     * Ctor: Predefined storages
     */
    StorageManager(RESTClient client) {

        this.client = client
        storages << new Storage('0', 'Cz32', 'Czołgistów 32', null)
        storages << new Storage('0', 'St40', 'Staromiejska 40', null)
    }

    void createStorages() {

        RESTExecutor restExecutor = new RESTExecutor(client)

        storages.each { storage ->
            System.out.println("Name: ${storage.name}, id: ${storage.id}")

            IValidator validator = new StorageValidator(storage)
            restExecutor.execute(new CreateStorage(storage), validator)

            System.out.println("Name: ${storage.name}, id: ${storage.id}")
        }
    }
}
