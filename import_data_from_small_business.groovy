#!/usr/bin/groovy
@Grab(group='org.reksio.rfp',module='rest', version='1.0-SNAPSHOT')

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.reksio.rfp.tools.rest.processes.ImportProcess
import org.reksio.rfp.tools.smallbusiness.gizmo.SmallBusinessParser
import org.reksio.rfp.tools.smallbusiness.gizmo.DocsSegregation
import org.reksio.rfp.tools.smallbusiness.types.Rejestr

Logger logger = Logger.getRootLogger()
logger.setLevel(Level.TRACE)

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

DocsSegregation ds = new DocsSegregation()

options.arguments().each { filename ->
    SmallBusinessParser sm = new SmallBusinessParser(filename)
    ds.add(sm.getDocuments())
}

ImportProcess.Import_to_RFP(options.url, ds)
//ImportProcess.Generate_EDI_Files(ds)

/*logger.info("Show documents.... ${sbp.documents.size()}")
sbp.documents.each {
    logger.info("${it.name}")
    it.showProperties(['SposobPlatnosci'])
    logger.info("Sub-docs: ${it.documents.size()}")
}*/
