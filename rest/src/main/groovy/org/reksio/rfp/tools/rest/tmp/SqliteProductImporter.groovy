package org.reksio.rfp.tools.rest.tmp

import org.reksio.rfp.tools.smallbusiness.gizmo.Document

/**
 * Connect to db
 * Run query retrieving all product from Słowackiego
 * Merge with those retrieved from SmallBusiness
 * Generate EDI import file for SmallBusiness
 */
class SqliteProductImporter {

    static final String input_file = '/home/spuu/Downloads/rfp_v1_db/products.dump'
    List<ProductInfo> pInfo = []

    List<Document> produkty
    List<Document> barkody

    SqliteProductImporter(List<Document> produkty, List<Document> barkody) {
        this.produkty = produkty
        this.barkody = barkody
    }

    void execute() {

        new File(input_file).eachLine { line ->
            def (data, ean, name, svat, sbnp, ssbp) = line.tokenize('|')

            if (!isInProducts(ean) && !isInBarcodes(ean) && !isInPInfo(ean)) {
                pInfo.add(new ProductInfo(ean, name, sbnp, "", "", ssbp, svat))
            }
        }
    }

    void export(Integer index) {
        pInfo.each { p ->
            System.out.println(p.export(index))
            ++index
        }
    }

    boolean isInProducts(String ean) {
        boolean ret = false

        if(!produkty)
            return false

        produkty.first().documents.each { prod ->
            if(prod.properties['Symbol'] == ean)
                ret = true
        }

        return ret
    }

    boolean isInBarcodes(String ean) {

        boolean ret = false

        if(!barkody)
            return false

        barkody.first().documents.each { bar ->
            if(bar.properties['Barkod'] == ean)
                ret = true
        }
        return ret
    }

    boolean isInPInfo(String ean) {
        boolean ret = false
        pInfo.each { p ->
            if(p.ean == ean)
                ret = true
        }

        return ret
    }

    class ProductInfo {
        String name, ean
        String buyNettoPrice, buyBruttoPrice
        String sellNettoPrice, sellBruttoPrice
        String vat

//        ProductInfo(String ean, String name, BigDecimal bnp, BigDecimal bbp, BigDecimal snp, BigDecimal sbp, Integer vat) {
//            this.ean = ean
//            this.name = name
//            this.buyNettoPrice = bnp
//            this.buyBruttoPrice = bbp
//            this.sellNettoPrice = snp
//            this.sellBruttoPrice = sbp
//            this.vat = vat
//        }

        ProductInfo(String ean, String name, String bnp, String bbp, String snp, String sbp, String vat) {
            this.ean = ean
            this.name = name
            this.buyNettoPrice = bnp
            this.buyBruttoPrice = bbp
            this.sellNettoPrice = snp
            this.sellBruttoPrice = sbp
            this.vat = vat
        }

        String export(Integer id) {

            return """
[Poz${id}]
Nazwa=${name}
Symbol=${ean}
Jm=
CenaZakupuNetto=${buyNettoPrice}
Vat=${vat} %
VatSpr=${vat} %
CenaSprzedazyBrutto=${sellBruttoPrice}"""
        }
    }
}

//if(true) {
/*Integer vat = svat.toInteger()
BigDecimal bvat
BigDecimal bnp = sbnp ? new BigDecimal(sbnp as String) : BigDecimal.ZERO
BigDecimal sbp = ssbp ? new BigDecimal(ssbp as String) : BigDecimal.ZERO
BigDecimal bbp = bnp
BigDecimal snp = sbp

if (vat == 0)
    bvat = 0
else
    bvat = vat.toBigDecimal().divide(100)

bbp += bbp * bvat
snp -= snp * bvat*/