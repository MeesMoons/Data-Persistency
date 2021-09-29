package ovchip.reiziger;

import java.sql.Date;

public class Reiziger {
    public int id;
    public String voorletters;
    public String tussenvoegsel;
    public String achternaam;
    public Date geboortedatum;

    public Reiziger( String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }
//
//    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
//        this.id = id;
//        this.voorletters = voorletters;
//        this.tussenvoegsel = tussenvoegsel;
//        this.achternaam = achternaam;
//        this.geboortedatum = geboortedatum;
//    }

    public String toString() {
        String string = id + " " + voorletters + "." + achternaam + " " + geboortedatum;
        return string;
    }
}
