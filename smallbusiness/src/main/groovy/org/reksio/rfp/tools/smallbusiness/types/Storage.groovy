package org.reksio.rfp.tools.smallbusiness.types

/**
 * Storage definition
 *
 * number - smallbusiness 'magazyn' id
 * name - shortname
 * long_name - longname
 */
class Storage {

    String name, long_name, number

    Storage(String number, String name, String long_name) {
        this.name = name
        this.long_name = long_name
        this.number = number
    }
}
