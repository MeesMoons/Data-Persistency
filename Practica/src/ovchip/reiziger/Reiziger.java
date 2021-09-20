package ovchip.reiziger;

//import java.util.Date;
import ovchip.adres.Adres;
import ovchip.ovchipkaart.OVChipkaart;

import java.sql.Date;

public class Reiziger {
    public int id;
    public String voorletters;
    public String tussenvoegsel;
    public String achternaam;
    public Date geboortedatum;
    public Adres adres;
    public OVChipkaart ovChipkaart;

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

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
        String string = id + " " + voorletters + "." + achternaam + " " + geboortedatum;
        return string;
    }
}
