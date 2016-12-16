package org.reksio.rfp.tools.rest.processes

import org.reksio.rfp.tools.rest.validators.PostIdKeeper
import org.reksio.rfp.tools.smallbusiness.gizmo.DocsSegregation
import org.reksio.rfp.tools.rest.validators.RESTValidator
import org.reksio.rfp.tools.rest.executors.RESTExecutor
import groovyx.net.http.RESTClient
import org.apache.log4j.Logger
import org.reksio.rfp.tools.smallbusiness.types.Storage

/**
 * Calls REST for provided documents, them validate
 */
class ImportProcess {

    static final Logger logger = Logger.getLogger(ImportProcess.class)

    static void Import_to_RFP(String url, DocsSegregation docsSegregation) {

        RESTClient client = new RESTClient(url)
        RESTExecutor restExecutor = new RESTExecutor(client)
        RESTValidator restValidator = new RESTValidator()
        PostIdKeeper postIdKeeper = new PostIdKeeper()

        List<Storage> storages = []
        storages << new Storage('0', 'Cz32', 'Czołgistów 32')
        storages << new Storage('1', 'St40', 'Staromiejska 40')
        storages << new Storage('2', 'Slow', 'Słowackiego 16')
        storages << new Storage('3', 'St13', 'Staromiejska 13')

        StorageManager storageManager = new StorageManager(restExecutor, postIdKeeper)
        storageManager.createStorages(storages)

        System.out.println('End of storage creation')

//        documents.each {
//            DoccyTiara.decides_about_(it, tiarasExecutor, restValidator)
//        }

        logger.info(storageManager.getIdByName('Cz32'))
        logger.info(storageManager.getIdByName('St40'))

        logger.info("Successful REST Api calls: ${postIdKeeper.getNumberOfValid()}")
        logger.info("Failed REST Api calls: ${postIdKeeper.getNumberOfInvalid()}")
    }
}
