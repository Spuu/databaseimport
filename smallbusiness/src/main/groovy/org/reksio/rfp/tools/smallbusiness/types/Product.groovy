package org.reksio.rfp.tools.smallbusiness.types

import org.reksio.rfp.tools.smallbusiness.gizmo.Document

/**
 * Product definition
 */
class Product {

    String name, symbol, jm
    String buyNettoPrice, buyBruttoPrice, sellNettoPrice, sellBruttoPrice
    String buyVat, sellVat

    Product(Document doc) {
        name = doc.properties['Nazwa'] ?: Undefined.NONE
        symbol = doc.properties['Symbol'] ?: Undefined.NONE
        jm = doc.properties['Jm'] ?: Undefined.NONE
        buyNettoPrice = doc.properties['CenaZakupuNetto'] ?: Undefined.NONE
        buyBruttoPrice = doc.properties['CenaZakupuBrutto'] ?: Undefined.NONE
        buyVat = doc.properties['Vat'] ?: Undefined.NONE
        sellVat = doc.properties['VatSpr'] ?: Undefined.NONE
        sellNettoPrice = doc.properties['CenaSprzedazyNetto'] ?: Undefined.NONE
        sellBruttoPrice = doc.properties['CenaSprzedazyBrutto'] ?: Undefined.NONE
    }

    /*
Nazwa=RC kot Pure Slimness 0,3kg nr 2
Symbol=3182550737852
Jm=
CenaZakupuNetto=13.64000
CenaZakupuBrutto=16.77720
Vat=23 %
VatSpr=23 %
CenaSprzedazyNetto=16.26
CenaSprzedazyBrutto=20.00
     */
}
