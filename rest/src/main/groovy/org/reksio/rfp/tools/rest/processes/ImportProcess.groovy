package org.reksio.rfp.tools.rest.processes

import org.reksio.rfp.tools.rest.tmp.SqliteProductImporter
import org.reksio.rfp.tools.rest.validators.PostIdKeeper
import org.reksio.rfp.tools.smallbusiness.gizmo.DocsSegregation
import org.reksio.rfp.tools.rest.executors.RESTExecutor
import groovyx.net.http.RESTClient
import org.apache.log4j.Logger
import org.reksio.rfp.tools.smallbusiness.types.Cpty
import org.reksio.rfp.tools.smallbusiness.types.Invoice
import org.reksio.rfp.tools.smallbusiness.types.Rejestr
import org.reksio.rfp.tools.smallbusiness.types.Storage

/**
 * Calls REST for provided documents, them validate
 */
class ImportProcess {

    static final Logger logger = Logger.getLogger(ImportProcess.class)

    static void Generate_EDI_Files(DocsSegregation docsSegregation) {
        SqliteProductImporter spi = new SqliteProductImporter(null, null
                /*docsSegregation.partition[Rejestr.MAGAZYN],
                docsSegregation.partition[Rejestr.BARKODY]*/)

        spi.execute()
        spi.export(1)
    }

    static void main(String [] args) {
        logger.info("MAIN MAIN MAIN")
    }

    static void Import_to_RFP(String url, DocsSegregation docsSegregation) {

        RESTClient client = new RESTClient(url)
        RESTExecutor restExecutor = new RESTExecutor(client)
        PostIdKeeper postIdKeeper = new PostIdKeeper()


        StorageManager.
                getInstance(restExecutor, postIdKeeper)
                .create(prepareStorages())

        CptyManager.
                getInstance(restExecutor, postIdKeeper)
                .create(prepareCpties())

        ProductManager.getInstance(restExecutor, postIdKeeper).
                create(docsSegregation.partition[Rejestr.MAGAZYN])

        PositionManager.getInstance(restExecutor, postIdKeeper)

        InvoiceManager.getInstance(restExecutor, postIdKeeper).
                create(docsSegregation.partition[Rejestr.FAKTURY_ZAK] +
                        docsSegregation.partition[Rejestr.FAKTURY_SPR])

//        documents.each {
//            DoccyTiara.decides_about_(it, tiarasExecutor, restValidator)
//        }

        logger.info("Successful REST Api calls: ${postIdKeeper.getNumberOfValid()}")
        logger.info("Failed REST Api calls: ${postIdKeeper.getNumberOfInvalid()}")
    }

    private static List<Storage> prepareStorages() {
        List<Storage> storages = []
        storages << new Storage('0', 'Cz32', 'Czołgistów 32')
        storages << new Storage('1', 'St40', 'Staromiejska 40')
        storages << new Storage('2', 'Slow', 'Słowackiego 16')
        storages << new Storage('3', 'St13', 'Staromiejska 13')

        return storages
    }

    private static List<Cpty> prepareCpties() {
        List<Cpty> cpties = []
        cpties << new Cpty(['1'], 'Medivet')
        cpties << new Cpty(['11'], 'Rolfel')
        cpties << new Cpty(['12'], 'AmiPlay')
        cpties << new Cpty(['13'], 'Azan')
        cpties << new Cpty(['14'], 'Proven')
        cpties << new Cpty(['15'], 'Lessie Land')
        cpties << new Cpty(['2'], 'Aquael')
        cpties << new Cpty(['23'], 'Koala')
        cpties << new Cpty(['24', '71'], 'Sum-Plast')
        cpties << new Cpty(['26'], 'Zooleszcz')
        cpties << new Cpty(['27', '75'], 'Champion')
        cpties << new Cpty(['29'], 'Bets4Pets')
        cpties << new Cpty(['3'], 'Hobby')
        cpties << new Cpty(['30'], 'Fiona')
        cpties << new Cpty(['31'], 'Ste-ma')
        cpties << new Cpty(['35'], 'Abakus')
        cpties << new Cpty(['36'], 'Damick')
        cpties << new Cpty(['37'], 'Diamsil')
        cpties << new Cpty(['39', '67'], 'Marcelak')
        cpties << new Cpty(['4'], 'Oryza')
        //cpties << new Cpty(['40'], 'Słowackiego')
        cpties << new Cpty(['41'], 'Dingo')
        cpties << new Cpty(['42'], 'Grzegorz Cerafin')
        cpties << new Cpty(['43'], 'Grande Finale')
        cpties << new Cpty(['44'], 'Beaphar')
        cpties << new Cpty(['45'], 'Krajan')
        cpties << new Cpty(['46'], 'Rokus')
        cpties << new Cpty(['47'], 'Bayleg')
        cpties << new Cpty(['48'], 'Dino')
        cpties << new Cpty(['49'], 'Plantis')
        cpties << new Cpty(['5'], 'World Pets')
        cpties << new Cpty(['50'], 'Rettenmaier')
        cpties << new Cpty(['51'], 'Millmar')
        cpties << new Cpty(['52'], 'Happet')
        cpties << new Cpty(['53'], 'A.Ziarko')
        cpties << new Cpty(['54'], 'Animal')
        cpties << new Cpty(['55'], 'Heros')
        cpties << new Cpty(['56'], 'Zolux')
        cpties << new Cpty(['57'], 'Żmijka')
        cpties << new Cpty(['58'], 'Giełda')
        cpties << new Cpty(['59'], 'Ziarnko')
        cpties << new Cpty(['6'], 'Fera')
        //cpties << new Cpty(['60'], 'Remanent Czołgistów 32')
        cpties << new Cpty(['61'], 'Dako')
        cpties << new Cpty(['62'], 'Incomed')
        cpties << new Cpty(['63'], 'Wet-Art')
        cpties << new Cpty(['64'], 'Dermapharm')
        cpties << new Cpty(['65'], 'Wid-Pol')
        cpties << new Cpty(['66'], 'Wromak')
        cpties << new Cpty(['68'], 'Bonzai')
        //cpties << new Cpty(['69'], 'Bez paragonu')
        cpties << new Cpty(['7'], 'Royal')
        cpties << new Cpty(['70'], 'Juris')
        cpties << new Cpty(['72'], 'Jamski')
        cpties << new Cpty(['73'], 'Zoolek')
        cpties << new Cpty(['74'], 'Katrinex')
        cpties << new Cpty(['76'], 'Zoologia')
        cpties << new Cpty(['77'], 'Lessie-Land')
        cpties << new Cpty(['78'], 'Rekin')
        cpties << new Cpty(['79'], 'Zooplus')
        cpties << new Cpty(['8'], 'Adbi')
        cpties << new Cpty(['80'], 'Cezar')
        cpties << new Cpty(['81'], 'Jaro')
        cpties << new Cpty(['82', '87'], 'Altum')
        //cpties << new Cpty(['83'], 'Staromiejska 13')
        cpties << new Cpty(['84'], 'Rafał Wieczorek')
        cpties << new Cpty(['85'], 'Spin')
        cpties << new Cpty(['86'], 'Koral')
        cpties << new Cpty(['88'], 'Nestle')
        cpties << new Cpty(['89'], 'SMD')
        cpties << new Cpty(['90'], 'Witrall Wit')
        cpties << new Cpty(['92'], 'Acana')
        cpties << new Cpty(['93'], 'Erbe')
        cpties << new Cpty(['94'], 'Amazonia')
        cpties << new Cpty(['95'], 'Genus')
        cpties << new Cpty(['96'], 'Ola')
    }

    private def displayCpties(DocsSegregation docsSegregation) {
        Map<String, String> cpty = [:]
        (
                docsSegregation.partition[Rejestr.FAKTURY_ZAK] +
                docsSegregation.partition[Rejestr.FAKTURY_SPR] +
                docsSegregation.partition[Rejestr.FAKTURY_ZAK_KOR]
        ).each {
            cpty << [
                    (it.properties['NrKontrahenta']) : it.properties['NazwaKontrahenta']
            ]
        }

        (cpty.sort()*.key).each { k ->
            def val = cpty[k]
            System.out.println("cpties << new Cpty('$k', '$val')")
        }
    }
}
