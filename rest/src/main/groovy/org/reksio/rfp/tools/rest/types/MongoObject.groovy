package org.reksio.rfp.tools.rest.types

/**
 * MongoObject
 */
class MongoObject<T> {
    String id
    T obj

    MongoObject(T obj) {
        this.obj = obj
    }
}
