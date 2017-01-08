package org.reksio.rfp.tools.rest.types

import org.reksio.rfp.tools.smallbusiness.gizmo.Document

/**
 * MongoObject
 */
class MongoObject<T> {
    String id
    T obj

    MongoObject(T obj) {
        this.obj = obj
    }

    MongoObject(Document doc, Class<T> clazz) {
        this.obj = clazz.newInstance(doc)
    }
}
