package groovy.gizmo

import groovy.rest.RESTValidator
import groovy.tiara.DoccyTiara
import groovy.tiara.TiarasExecutor
import groovyx.net.http.RESTClient
import org.apache.log4j.Logger

/**
 * Whole process gathering all steps from file parsing to validate rest calls responses
 */
class ImportProcess extends SmallBusinessParser {

    static final Logger logger = Logger.getLogger(ImportProcess.class)

    ImportProcess(String url, String filename) {
        super(filename)

        parse()

        RESTClient client = new RESTClient(url)
        TiarasExecutor tiarasExecutor = new TiarasExecutor(client)
        RESTValidator restValidator = new RESTValidator()

        documents.each {
            DoccyTiara.decides_about_(it, tiarasExecutor, restValidator)
        }

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
