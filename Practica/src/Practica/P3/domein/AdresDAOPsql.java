package Practica.P3.domein;

import Practica.P2.domein.ReizigerDAO;

import java.sql.Connection;

public class AdresDAOPsql {
    public Connection connection;
    public ReizigerDAO rdao;

    public AdresDAOPsql(Connection connection) {
        this.connection = connection;
    }

    public boolean save(Adres adres)
}
