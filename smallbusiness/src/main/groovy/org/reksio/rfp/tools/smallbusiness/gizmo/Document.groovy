package org.reksio.rfp.tools.smallbusiness.gizmo

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Small Business org.reksio.rfp.tools.smallbusiness.gizmo.Document
 */
class Document {
    String name
    Map<String, String> properties = [:]
    List<Document> documents = []

    private Logger logger = LoggerFactory.getLogger(Document.class)

    Document(String name) {
        this.name = name
    }

    void showProperties(List keys = []) {
        properties.each { k, v ->
            if(keys.isEmpty() || k in keys)
                logger.info("${k} -> ${v}")
        }
    }
}