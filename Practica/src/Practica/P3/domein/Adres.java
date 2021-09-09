package Practica.P3.domein;

public class Adres {
    public int adres_id;
    public String postcode;
    public String huisnummer;
    public String straat;
    public String woonplaats;
    public int reiziger_id;

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String toString() {
        String str = adres_id + " " + postcode + " " + huisnummer + " " + straat + " " + woonplaats + " " + reiziger_id;
        return str;
    }
}
