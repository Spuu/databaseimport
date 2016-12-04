package org.reksio.rfp.tools.smallbusiness.gizmo

/**
 * Performs custom logic on Documents
 */
class DocsSegregation {

    private static String REJESTR = 'Rejestr'
    private List<Document> documents = []

    Map<String, List<Document>> partition = [:]

    DocsSegregation() { }

    void add(List<Document> docs) {
        this.documents.addAll(docs)
        clasify(docs)
    }

    private void clasify(List<Document> docs) {
        docs.each { doc ->
            def registry = doc.properties[REJESTR]

            if(registry) {
                if(partition[registry])
                        partition[registry].add(doc)
                else
                        partition[registry] = [doc]
            }
        }
    }
}
