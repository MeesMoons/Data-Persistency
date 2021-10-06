package Practica.P2.domein;

import ovchip.reiziger.Reiziger;
import ovchip.reiziger.ReizigerDAO;
import ovchip.reiziger.ReizigerDAOPsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public Connection connection;

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=Appelcake10";
        Connection conn = DriverManager.getConnection(url);

        ReizigerDAO reizigerDAO = new ReizigerDAOPsql(conn);

        testReizigerDAO(reizigerDAO);
    }

    private static void getConnection() throws SQLException {

    }

    private static void closeConnection() {

    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();


//        haal reiziger op met specifiek id
        System.out.println("[Test] ReizigerDAO.findById() geeft de volgende reiziger:");
        Reiziger reiziger = (Reiziger) rdao.findById(1);
        System.out.println(reiziger);
        System.out.println();

//      haal reizigers op met specifieke geboortedatum
        System.out.println("[Test] ReizigerDAO.findByGbdatum() geeft de volgende reiziger:");
        List<Reiziger> reizigers1 = rdao.findByGbdatum("1981-03-14");

        for (Reiziger r : reizigers1) {
            System.out.println(r);
        }
        System.out.println();



        // Maak een nieuwe reiziger aan en persisteer deze in de database
        System.out.println("[Test] ReizigerDAO.save() geeft de volgende uitkomst:");
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger("S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");


//        verwijder sietske met specifiek id
        System.out.println("[Test] ReizigerDAO.delete() geeft de volgende uitkomst:");
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.delete() ");
        rdao.delete(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

//        sietske updaten
        Reiziger test = new Reiziger("test", "", "test", java.sql.Date.valueOf(gbdatum));
//        rdao.update(test);
    }
}
