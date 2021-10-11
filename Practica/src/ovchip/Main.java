package ovchip;


import ovchip.adres.Adres;
import ovchip.adres.AdresDAO;
import ovchip.adres.AdresDAOPsql;
import ovchip.ovchipkaart.OVChipkaart;
import ovchip.ovchipkaart.OVChipkaartDAO;
import ovchip.ovchipkaart.OVChipkaartDAOsql;
import ovchip.product.Product;
import ovchip.product.ProductDAO;
import ovchip.product.ProductDAOPsql;
import ovchip.reiziger.Reiziger;
import ovchip.reiziger.ReizigerDAO;
import ovchip.reiziger.ReizigerDAOPsql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public Connection connection;

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=Appelcake10";
        Connection conn = DriverManager.getConnection(url);
        ReizigerDAO reizigerDAO = new ReizigerDAOPsql(conn);
        AdresDAO adresDAO = new AdresDAOPsql(conn);
        OVChipkaartDAO ovChipkaartDAO = new OVChipkaartDAOsql(conn);
        ProductDAO productDAO = new ProductDAOPsql(conn);
        testReizigerAdresRelatie(reizigerDAO, adresDAO, ovChipkaartDAO, productDAO);
    }


    private static void testReizigerAdresRelatie(ReizigerDAO reizigerDAO, AdresDAO adresDAO,OVChipkaartDAO ovChipkaartDAO, ProductDAO productDAO) throws SQLException {
//        adresDAO.findAll()        [TEST]
        System.out.println();
        List<Adres> adresList = adresDAO.findAll();
        System.out.println("[TEST] adresDAO.findAll geeft: ");
        for (Adres a : adresList) {
            System.out.println(a);
        }
        System.out.println();


//        reizigerDAO.findAll()        [TEST]
        List<Reiziger> reizigerList = reizigerDAO.findAll();
        System.out.println("[TEST] reizigerDAO.findAll geeft: ");
        for (Reiziger r : reizigerList) {
            System.out.println(r);
        }
        System.out.println();


//        test reizigerDAO.save()       [TEST]
        List<Reiziger> reizigerList1 = reizigerDAO.findAll();
        Reiziger reiziger = new Reiziger("M", null, "Moons", java.sql.Date.valueOf("2002-12-29"));
        reizigerDAO.save(reiziger);
        System.out.print("[Test] Eerst " + reizigerList1.size() + " reizigers, na reizigerDAO.save() ");
        List<Reiziger> reizigerList2 = reizigerDAO.findAll();
        System.out.println(reizigerList2.size() + " reizigers\n");


//        adresDAO.save()       [TEST]
        List<Adres> adresList1 = adresDAO.findAll();
        Adres adres = new Adres("2806EJ", "28", "krugerlaan", "Gouda", reizigerDAO.findId(reiziger));
        adresDAO.save(adres);
        System.out.print("[Test] Eerst " + adresList1.size() + " adressen, na adresDAO.save() ");
        List<Adres> adresList2 = adresDAO.findAll();
        System.out.println(adresList2.size() + " adressen\n");


//        test adresDAO.delete()       [TEST]
        System.out.print("[Test] Eerst " + adresList2.size() + " adressen, na adresDAO.delete() ");
        adresDAO.delete(adres);
        List<Adres> adresList3 = adresDAO.findAll();
        System.out.println(adresList3.size() + " adressen\n");


//        test reizigerDAO.delete()       [TEST]
        System.out.print("[Test] Eerst " + reizigerList2.size() + " reizigers, na reizigerDAO.delete() ");
        reizigerDAO.delete(reiziger);
        List<Reiziger> reizigerList3 = reizigerDAO.findAll();
        System.out.println(reizigerList3.size() + " reizigers\n");


//        test reizigerDAO.update() & adresDAO.update()      [TEST]
        Reiziger reizigerUpdate1 = new Reiziger("X", null, "Moons", java.sql.Date.valueOf("2002-12-29"));
        reizigerDAO.save(reizigerUpdate1);
        Adres adresUpdate1 = new Adres("2806EJ", "193", "Ceintuurbaan", "Amsterdam", reizigerDAO.findId(reizigerUpdate1));
        adresDAO.save(adresUpdate1);
        System.out.println("[TEST] Voor updaten: ");
        System.out.println(reizigerDAO.findById(reizigerDAO.findId(reizigerUpdate1)));
        System.out.println(adresDAO.findByReiziger(reizigerDAO.findId(reizigerUpdate1)));
        Reiziger reizigerUpdate2 = new Reiziger("X", null, "Lammers", java.sql.Date.valueOf("2002-12-29"));
        Adres adresUpdate2 = new Adres("2806EJ", "193", "Heidelberglaan", "Utrecht", reizigerDAO.findId(reizigerUpdate1));
        adresDAO.update(adresUpdate2, adresDAO.findId(adresUpdate1));
        reizigerDAO.update(reizigerUpdate2, reizigerDAO.findId(reizigerUpdate1));
        System.out.println();
        System.out.println("[TEST] Na updaten: ");
        System.out.println(reizigerDAO.findById(reizigerDAO.findId(reizigerUpdate2)));
        System.out.println(adresDAO.findByReiziger(reizigerDAO.findId(reizigerUpdate2)));
        adresDAO.delete(adresUpdate2);
        reizigerDAO.delete(reizigerUpdate2);


//        test reizigerDAO.findByGbdatum()       [TEST]
        System.out.println();
        System.out.println("[TEST] Zoek reizigers met de geboortedatum '2002-12-03': ");
        List<Reiziger> reizigers = reizigerDAO.findByGbdatum("2002-12-03");
        for (Reiziger reiziger1 : reizigers) {
            System.out.println(reiziger1);
        }


//        test reizigerDAO.update(), .delete() en ovchipDAO.findByReiziger() met de nieuwe relatie met ov_chipkaart
        System.out.println();
        System.out.println("[TEST] Geef nieuwe reiziger en zijn ov_chipkaarten: ");
        Reiziger reizigerOvchipkaart = new Reiziger("R", null, "Ossenwaarde", Date.valueOf("1974-11-11"));
        reizigerDAO.save(reizigerOvchipkaart);
        for (int i=0; i<=4; i++) {
            OVChipkaart ovChipkaart = new OVChipkaart(Date.valueOf("2032-12-29"), 1, 25.30);
            reizigerOvchipkaart.ovChipkaartList.add(ovChipkaart);
        }
        reizigerDAO.update(reizigerOvchipkaart, reizigerDAO.findId(reizigerOvchipkaart));
        System.out.println(reizigerDAO.findById(reizigerDAO.findId(reizigerOvchipkaart)) + " " + reizigerDAO.findId(reizigerOvchipkaart));
        for (OVChipkaart ovChipkaart : ovChipkaartDAO.findByReiziger(reizigerOvchipkaart, reizigerDAO.findId(reizigerOvchipkaart))) {
            System.out.println(ovChipkaart);
        }
        reizigerDAO.delete(reizigerOvchipkaart);


//        test productDAO.save(), .delete() en update()         [TEST]
        Product product = new Product("mees", "dit is een test product", 25.50);
        productDAO.save(product);
        System.out.println();
        System.out.println("[TEST] geef het opgeslagen product, update hem en verwijder hem: ");
        System.out.println(productDAO.findById(productDAO.findId(product)));
        Product updateProduct = new Product("updated", "dit is een test product", 25.50);
        productDAO.update(updateProduct, productDAO.findId(product));
        System.out.println(productDAO.findById(productDAO.findId(updateProduct)));
        productDAO.delete(updateProduct);

//        for (int i=0; i<=4; i++) {
//            OVChipkaart ovChipkaart = new OVChipkaart(Date.valueOf("2032-12-29"), 1, 25.30, 5);
//            product.ovChipkaartList.add(ovChipkaart);
//        }
//        for (OVChipkaart ovChipkaart : product.ovChipkaartList) {
//            System.out.println(ovChipkaart);
//        }


        Reiziger reiziger1 = new Reiziger("R", null, "Ossenwaarde", Date.valueOf("1974-11-11"));
        Product product1 = new Product("mees", "dit is een test product", 25.50);
        reizigerDAO.save(reiziger1);
        OVChipkaart ovChipkaart = new OVChipkaart(Date.valueOf("2032-12-29"), 1, 25.30);
        reiziger1.ovChipkaartList.add(ovChipkaart);
        reizigerDAO.update(reiziger1, reizigerDAO.findId(reiziger1));

        for (OVChipkaart ovChipkaart1 : reiziger1.ovChipkaartList) {
            ovChipkaart1.setKaart_nummer(ovChipkaartDAO.findId(ovChipkaart));
            product1.ovChipkaartList.add(ovChipkaart);
        }
        productDAO.save(product1);
    }
}
