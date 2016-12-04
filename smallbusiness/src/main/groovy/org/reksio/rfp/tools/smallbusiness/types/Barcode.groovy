package org.reksio.rfp.tools.smallbusiness.types

import org.reksio.rfp.tools.smallbusiness.gizmo.Document

/**
 * Barcode definition
 */
class Barcode {

    String symbol, barcode, rate

    Barcode(Document doc) {
        symbol = doc.properties['Symbol'] ?: Undefined.NONE
        barcode = doc.properties['Barkod'] ?: Undefined.NONE
        rate = doc.properties['Mnoznik'] ?: Undefined.NONE
    }
}
