package Practica.P2.domein;

//import java.util.Date;
import java.sql.Date;

public class Reiziger {
    public int id;
    public String voorletters;
    public String tussenvoegsel;
    public String achternaam;
    public Date geboortedatum;

    public Reiziger() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String toString() {
        String string = "";
        return string;
    }
}
