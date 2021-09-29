package ovchip;


import ovchip.adres.Adres;
import ovchip.adres.AdresDAO;
import ovchip.adres.AdresDAOPsql;
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

        AdresDAO adresDAO = new AdresDAOPsql(conn);
        ReizigerDAO reizigerDAO = new ReizigerDAOPsql(conn);
//        testAdresDAO(adresDAO);
//        testReizigerDAO(reizigerDAO);
        testReizigerAdresRelatie(reizigerDAO, adresDAO);
    }

    private static void testAdresDAO(AdresDAO adresDAO) {
//        List<Adres> adresList = adresDAO.findAll();
//        System.out.println("test find all");
//        for (Adres a : adresList) {
//            System.out.println(a);
//        }
//        System.out.println();
//
//        List<Adres> adresList1 = adresDAO.findAll();
//        System.out.println("test .save adres");
//        Adres newAdres = new Adres("2806EJ", "28", "krugerlaan", "gouda");
//        System.out.print("[Test] Eerst " + adresList1.size() + " adressen, na adresDAO.save() ");
//        adresDAO.save(newAdres);
//        List<Adres> adressen = adresDAO.findAll();
//        System.out.println(adressen.size() + " adressen\n");
//
//        System.out.println("test delete adres");
//        List<Adres> adressen1 = adresDAO.findAll();
//
//        System.out.print("[Test] Eerst " + adressen1.size() + " adressen, na adresDAO.delete() ");
//        adresDAO.delete(newAdres);
//        List<Adres> adressen11 = adresDAO.findAll();
//        System.out.println(adressen11.size() + " adressen\n");
//
//        System.out.println("test .update adres");
//        Adres nieuwAdres = new Adres(1, "2806EJ", "28", "krugerlaan", "gouda", 1);
//        adresDAO.update(nieuwAdres);
    }

    private static void testReizigerAdresRelatie(ReizigerDAO reizigerDAO, AdresDAO adresDAO) throws SQLException {

        List<Adres> adresList = adresDAO.findAll();
        System.out.println("test find all");
        for (Adres a : adresList) {
            System.out.println(a);
        }
        System.out.println();


        Reiziger reiziger = new Reiziger("M", null, "Moons", java.sql.Date.valueOf("2002-12-29"));
        reizigerDAO.save(reiziger);


        List<Adres> adresList1 = adresDAO.findAll();
        System.out.println("test .save adres");
        Adres adres = new Adres("2806EJ", "28", "krugerlaan", "Gouda", reizigerDAO.findId(reiziger));
        adresDAO.save(adres);
        System.out.print("[Test] Eerst " + adresList1.size() + " adressen, na adresDAO.save() ");

        List<Adres> adressen = adresDAO.findAll();
        System.out.println(adressen.size() + " adressen\n");

        System.out.println(adresDAO.findId(adres));
    }

    private static void testReizigerDAO(ReizigerDAO reizigerDAO) throws SQLException {
        Reiziger reiziger = new Reiziger("R", "", "Ossenwaarde", Date.valueOf("1974-11-11"));
        reizigerDAO.save(reiziger);
//
//        for (int i=0; i<=4; i++) {
//            // maak nieuwe ovchipkkart c aan
//            reiziger.add(c);
//        }
//        reizigerDAO.update(reiziger);
//        // print uit dat er 5 ovchipkaarten aan vast zitten


    }
}
