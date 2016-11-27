package groovy.tiara

import groovy.gizmo.Document
import groovy.gizmo.IValidator
import groovy.rest.CreateProduct
import groovy.rest.IRestApiCall
import groovy.rest.IRestExecutor
import org.apache.log4j.Logger

/**
 * Tiara decides and diverts given document into REST object
 */
class DoccyTiara {

    static final String TYPE = 'Rejestr'
    static final String PRODUCT = 'Magazyn'

    static final Logger logger = Logger.getLogger(DoccyTiara.class)

    static def decides_about_(Document doc, IRestExecutor executor, IValidator validator) {
        switch (doc.properties[TYPE]) {
            case PRODUCT:
                logger.info("${doc.getName()} recognized as Products...")
                doc.documents.each {
                    executor.execute(new CreateProduct(it), validator)
                }
                break

            default:
                logger.info('nothing recognized')
        }
    }
}
