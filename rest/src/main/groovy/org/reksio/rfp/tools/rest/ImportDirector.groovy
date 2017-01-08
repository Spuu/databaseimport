package org.reksio.rfp.tools.rest

import org.reksio.rfp.tools.rest.processes.ImportProcess
import org.reksio.rfp.tools.smallbusiness.gizmo.DocsSegregation
import org.reksio.rfp.tools.smallbusiness.gizmo.SmallBusinessParser
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Starting point of application.
 * Replace for groovy script.
 */

final Logger logger = LoggerFactory.getLogger("ImportDirector")

def cli = new CliBuilder(usage: 'import_data_from_small_business <url> [files]')
cli.d('Debug logs')
cli._(longOpt: 'url', args: 1, argName: 'URL', 'Set back-end URL')
def options = cli.parse(args)

if (!options.url) {
    logger.error("No url provided...")
    cli.usage()
    return
}

if (!options.arguments()) {
    logger.error("No import files provided..")
    cli.usage()
    return
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
