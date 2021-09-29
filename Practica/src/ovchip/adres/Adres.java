package ovchip.adres;

public class Adres {
    public int adres_id;
    public String postcode;
    public String huisnummer;
    public String straat;
    public String woonplaats;
    public int reiziger_id;

    public Adres(String postcode, String huisnummer, String straat, String woonplaats, int reiziger_id) {
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
        this.reiziger_id = reiziger_id;
    }


    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String toString() {
        String str = postcode + " " + huisnummer + " " + straat + " " + woonplaats + " " + reiziger_id;
        return str;
    }
}
