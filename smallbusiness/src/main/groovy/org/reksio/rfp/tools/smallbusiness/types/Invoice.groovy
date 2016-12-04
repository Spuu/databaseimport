package org.reksio.rfp.tools.smallbusiness.types

import org.reksio.rfp.tools.smallbusiness.gizmo.Document

import java.text.SimpleDateFormat

/**
 * Invoice definition
 */
class Invoice {

    Date date
    String docNum, docId
    String cptyNum, cptyName, cptyId
    String storage

    Invoice(Document doc) {
        SimpleDateFormat parser = new SimpleDateFormat("yy.MM.dd")

        date = parser.parse(doc.properties['DataOtrzymania'])

        docNum = doc.properties['NrDok'] ?: Undefined.NONE
        docId = doc.properties['IdentyfikatorDok'] ?: Undefined.NONE
        cptyNum = doc.properties['NrKontrahenta'] ?: Undefined.NONE
        cptyName = doc.properties['NazwaKontrahenta'] ?: Undefined.NONE
        cptyId = doc.properties['SymbolKontrahenta'] ?: Undefined.NONE
        storage = doc.properties['Magazyny'] ?: Undefined.NONE
    }

    /*
Netto=2038.84
Vat=163.11
Brutto=2201.95
WartWCenachSprBrutto=4228.80
WartWCenachSprNetto=3915.56
Vat0_Index=18
Vat0_Procent=8
Vat0_Stawka= 8 %
Vat0_Netto=2038.84
Vat0_Brutto=2201.95
Vat0_Vat=163.11
Vat0_NettoSprzed=3915.56
Vat0_BruttoSprzed=4228.80
Vat0_VatSprzed=313.24
     */
}
