package org.reksio.rfp.tools.smallbusiness.types

import org.reksio.rfp.tools.smallbusiness.gizmo.Document

/**
 * Invoice position definition
 */
class Position extends Product {

    String invoiceId
    String storeId
    String idx

    Position(Document doc) {
        super(doc)
        buyNettoPrice = doc.properties['CenaNetto'] ?: Undefined.NONE
        buyBruttoPrice = doc.properties['CenaBrutto'] ?: Undefined.NONE
        sellVat = buyVat

        invoiceId = doc.properties['invoiceId'] ?: Undefined.NONE
        storeId = doc.properties['storeId'] ?: Undefined.NONE
        idx = doc.properties['idx'] ?: Undefined.NONE
    }

    /*
Nazwa=Bosch mini jr małe rasy 15kg
Symbol=4015598008068
Jm=szt
CenaNetto=114.56000
CenaBrutto=123.72480
Vat= 8 %
CenaSprzedazyNetto=183.33
CenaSprzedazyBrutto=198.00
     */
}
