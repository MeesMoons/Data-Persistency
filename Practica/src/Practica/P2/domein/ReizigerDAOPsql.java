package Practica.P2.domein;

import java.sql.Connection;

public class ReizigerDAOPsql {

    public Connection conn;

    public ReizigerDAOPsql(Connection conn) {
        this.conn = conn;
    }
}
