package ovchip.ovchipkaart;

import ovchip.reiziger.Reiziger;

import java.sql.Date;

public class OVChipkaart {
    public int kaart_nummer;
    public Date geldig_tot;
    public int klasse;
    public double saldo;
    public int reiziger_id;

    public OVChipkaart(int kaart_nummer, Date geldig_tot, int klasse, double saldo, int reiziger_id) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger_id = reiziger_id;
    }

    public OVChipkaart(Date geldig_tot, int klasse, double saldo) {
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
    }

    public String toString() {
        String s = "";
        s += kaart_nummer + " " + geldig_tot + " " + klasse + " " + saldo + " " + reiziger_id;
        return s;
    }
}
