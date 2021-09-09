package Practica.P1;

import java.sql.*;
import java.util.Properties;

public class main {

    public static void main(String[] args) {
        try {
            String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=Appelcake10";
            Connection conn = DriverManager.getConnection(url);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM reiziger");

            System.out.println("alle reizigers: ");
            while (rs.next()) {
                String nummer = rs.getString(1);
                String voorletter = rs.getString(2);
                String achternaam = rs.getString(4);
                String geboorteDatum = rs.getString(5);
                System.out.println(nummer + " " + voorletter + "." + achternaam + " " + geboorteDatum);
            }
            rs.close();
            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
