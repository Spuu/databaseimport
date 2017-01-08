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
    String storageId

    Invoice(Document doc) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yy.MM.dd")
        date = inputFormat.parse(doc.properties['DataWyst'])

        docNum = doc.properties['NrDok'] ?: Undefined.NONE
        docId = doc.properties['IdentyfikatorDok'] ?: Undefined.NONE
        cptyNum = doc.properties['NrKontrahenta'] ?: Undefined.NONE
        cptyName = doc.properties['NazwaKontrahenta'] ?: Undefined.NONE
        cptyId = doc.properties['SymbolKontrahenta'] ?: Undefined.NONE
        storageId = doc.properties['Magazyny'].split(',').first() ?: Undefined.NONE
    }

    String getDate() {
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy.MM.dd")  //2015-03-25
        return outputFormat.format(date)
    }

    /*
Rejestr=FakturyZak
TypDok=1
DataOtrzymania=15.07.09
DataWyst=15.07.09
DataPrzyjecia=15.07.09
Termin=0
NrFilii=0
NrDowKsieg=G000038/2015
NrDok=FS/2015/21070
IdentyfikatorDok=39
NrKontrahenta=6
NrPlatnosci=1
SposobPlatnosci=gotówką
NazwaKontrahenta=Fera
AdresKontrahenta=Hubska 52                50-502 Wrocław
SymbolKontrahenta=
Nip=8992750635
Netto=162.28
Vat=12.98
Brutto=175.26
Inicjaly=MR
Uwagi=
WartWCenachSprBrutto=305.20
WartWCenachSprNetto=282.59
Vat0_Index=18
Vat0_Procent=8
Vat0_Stawka= 8 %
Vat0_Netto=162.28
Vat0_Brutto=175.26
Vat0_Vat=12.98
Vat0_NettoSprzed=282.59
Vat0_BruttoSprzed=305.20
Vat0_VatSprzed=22.61
Magazyny=0,
     */
}
