package org.reksio.rfp.tools.smallbusiness.types

/**
 * Storage definition
 */
class Storage {

    String id, name, long_name, number

    Storage(String number, String name, String long_name, String id) {
        this.id = id
        this.name = name
        this.long_name = long_name
        this.number = number
    }
}
