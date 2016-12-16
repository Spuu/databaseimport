package org.reksio.rfp.tools.smallbusiness.types

/**
 * Cpty definition
 */
class Cpty {

    String name
    List<String> numbers

    Cpty(List<String> numbers, String name) {
        this.name = name
        this.numbers = numbers
    }
}
