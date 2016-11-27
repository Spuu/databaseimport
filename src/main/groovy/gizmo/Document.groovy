package groovy.gizmo

import org.apache.log4j.Logger

/**
 * Small Business Document
 */
class Document {
    String name
    def properties = [:]
    List<Document> documents = []

    private Logger logger = Logger.getLogger(Document.class)

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