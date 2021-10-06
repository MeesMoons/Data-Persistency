package ovchip.reiziger;

import ovchip.ovchipkaart.OVChipkaart;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Reiziger {
    public int id;
    public String voorletters;
    public String tussenvoegsel;
    public String achternaam;
    public Date geboortedatum;
    public List<OVChipkaart> ovChipkaartList;

    public Reiziger( String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.ovChipkaartList = new ArrayList<>();
    }

    public void add(OVChipkaart ovChipkaart) {
        ovChipkaartList.add(ovChipkaart);
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
        String string = voorletters + "." + achternaam + " " + geboortedatum;
        return string;
    }
}
