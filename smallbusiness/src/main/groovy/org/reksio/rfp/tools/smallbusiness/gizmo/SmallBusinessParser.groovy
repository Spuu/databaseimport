package org.reksio.rfp.tools.smallbusiness.gizmo

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Parse Small Business export file into document container
 */
class SmallBusinessParser {

    protected static final def DOCUMENT = /Dokument/
    protected static final def POSITION = /Poz[0-9]+/

    protected Document major_doc, minor_doc
    protected String filename
    protected boolean isIgnored
    protected Logger logger = LoggerFactory.getLogger(SmallBusinessParser.class)

    List<Document> documents = []

    SmallBusinessParser(String filename) {
        this.filename = filename
        parse()
    }

    /**
     * Start parsing process which ends with filling up documents
     */
    private void parse() {
        File file = new File(filename)
        logger.trace("Processing file: ${filename}")

        if(!file.exists() || file.isDirectory()) {
            logger.warn("File does not exists or is a directory: ${filename}")
        }

        def lines = file.readLines()
        lines.each {
            processLine(it)
        }

        // finalize processing
        saveMinorDoc()
        saveMajorDoc()
    }

    /**
     * Recognizes type of line and forward for further processing
     */
    private processLine(String line) {
        def isEmpty = line.isEmpty()
        def isDocument = (line =~ /^\[(\w+)\]$/)
        def isProperty = (line =~ /^(\w+)=(.*)$/)

        if(isEmpty)
            processEmpty()
        else if(isProperty.size())
            processProperty(isProperty[0][1], isProperty[0][2])
        else if(isDocument.size())
            processDocument(isDocument[0][1])
    }

    /**
     * Saves minor (position) document
     */
    private void saveMinorDoc() {
        if(minor_doc) {
            major_doc.documents.add(minor_doc)
            minor_doc = null
        }
    }

    /**
     * Saves major (document) document
     */
    private void saveMajorDoc() {
        if(major_doc) {
            documents.add(major_doc)
            major_doc = null
        }
    }

    /**
     * Process empty line
     */
    private void processEmpty() {
        logger.debug('Empty')
        saveMinorDoc()
    }

    /**
     * Process property line
     */
    private void processProperty(String key, String value) {
        if(isIgnored) {
            logger.debug("Property ignored")
            return
        }

        logger.debug("Property: ${key} -> ${value}")

        def doc = minor_doc ?: major_doc
        doc.properties[key] = value
    }

    /**
     * Process document line
     */
    private void processDocument(String name) {
        logger.debug("Dokument: ${name}")

        def isPosition = (name ==~ /$POSITION/)
        def isDocument = (name ==~ /$DOCUMENT/)

        def isInScope = isPosition || isDocument
        if(!isInScope) {
            logger.debug("Doc: ${name} ignored.")
            isIgnored = true
            return
        }

        isIgnored = false

        if(isPosition) {
            logger.debug("New minor doc: ${name}")
            minor_doc = new Document(name)
        } else {
            saveMajorDoc()
            major_doc = new Document(name)
        }
    }
}