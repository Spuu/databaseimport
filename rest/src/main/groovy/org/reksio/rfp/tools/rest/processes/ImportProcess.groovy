package org.reksio.rfp.tools.rest.processes

import org.reksio.rfp.tools.smallbusiness.gizmo.Document
import org.reksio.rfp.tools.rest.validators.RESTValidator
import org.reksio.rfp.tools.rest.executors.DoccyTiara
import org.reksio.rfp.tools.rest.executors.RESTExecutor
import groovyx.net.http.RESTClient
import org.apache.log4j.Logger

/**
 * Calls REST for provided documents, them validate
 */
class ImportProcess {

    static final Logger logger = Logger.getLogger(ImportProcess.class)

    ImportProcess(String url, List<Document> documents) {

        RESTClient client = new RESTClient(url)
        RESTExecutor tiarasExecutor = new RESTExecutor(client)
        RESTValidator restValidator = new RESTValidator()

        StorageManager storageManager = new StorageManager(client)
        storageManager.createStorages()

        System.out.println('End of storage cration')

//        documents.each {
//            DoccyTiara.decides_about_(it, tiarasExecutor, restValidator)
//        }

        def num_of_valid = restValidator.results.count {
            it == RESTValidator.Result.Success
        }

        def num_of_invalid = restValidator.results.count {
            it == RESTValidator.Result.Failure
        }

        logger.info("Successful REST Api calls: ${num_of_valid}")
        logger.info("Failed REST Api calls: ${num_of_invalid}")
    }
}
