#!/usr/bin/groovy
@Grab(group='org.reksio.rfp.smallbusiness',module='database-import', version='1.0-SNAPSHOT')

import org.apache.log4j.Logger
import org.apache.log4j.Level
import groovy.gizmo.ImportProcess

Logger logger = Logger.getRootLogger()
logger.setLevel(Level.INFO)

/**
 * Parse arguments and parameters
 */
def cli = new CliBuilder(usage: 'import_data_from_small_business <url> [files]')
cli.d('Debug logs')
cli._(longOpt:'url', args:1, argName:'URL', 'Set back-end URL')
def options = cli.parse(args)

if(!options.url) {
    logger.error("No url provided...")
    return cli.usage()
}

if(!options.arguments()) {
    logger.error("No import files provided..")
    return cli.usage()
}

options.arguments().each {
    new ImportProcess(options.url, it)
    /*logger.info("Show documents.... ${sbp.documents.size()}")
    sbp.documents.each {
        logger.info("${it.name}")
        it.showProperties(['SposobPlatnosci'])
        logger.info("Sub-docs: ${it.documents.size()}")
    }*/
}
