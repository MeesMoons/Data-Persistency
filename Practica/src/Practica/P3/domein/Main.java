package Practica.P3.domein;

import Practica.P2.domein.ReizigerDAO;
import Practica.P2.domein.ReizigerDAOPsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public Connection connection;


    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=Appelcake10";
        Connection conn = DriverManager.getConnection(url);

        AdresDAO adresDAO = new AdresDAOPsql(conn);
        testAdresDAO(adresDAO);
    }

    private static void testAdresDAO(AdresDAO adresDAO) {
        List<Adres> adresList = adresDAO.findAll();
        System.out.println("test find all");
        for (Adres a : adresList) {
            System.out.println(a);
        }
        System.out.println();

        List<Adres> adresList1 = adresDAO.findAll();
        System.out.println("test .save adres");
        Adres newAdres = new Adres(2, "2806EJ", "28", "krugerlaan", "gouda", 99);
        System.out.print("[Test] Eerst " + adresList1.size() + " adressen, na adresDAO.save() ");
//        adresDAO.save(newAdres);
        List<Adres> adressen = adresDAO.findAll();
        System.out.println(adressen.size() + " adressen\n");

        System.out.println("test delete adres");
        System.out.print("[Test] Eerst " + adresList.size() + " adressen, na adresDAO.delete() ");
//        adresDAO.delete(newAdres);
        List<Adres> adressen1 = adresDAO.findAll();
        System.out.println(adressen1.size() + " adressen\n");

        System.out.println("test .update adres");
        Adres nieuwAdres = new Adres(2, "2806EJ", "28", "krugerlaan", "gouda", 99);
//        adresDAO.update(nieuwAdres);
    }
}