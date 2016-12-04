package org.reksio.rfp.tools.smallbusiness.types

/**
 * Cpty definition
 */
class Cpty {

    String id, name, number

    Cpty(String number, String name, String id) {
        this.id = id
        this.name = name
        this.number = number
    }
}
