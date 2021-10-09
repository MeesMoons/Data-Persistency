package ovchip.ovchipkaart;

import ovchip.product.Product;
import ovchip.reiziger.Reiziger;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaart {
    public int kaart_nummer;
    public Date geldig_tot;
    public int klasse;
    public double saldo;
    public int reiziger_id;
    public List<Product> products;

    public OVChipkaart(int kaart_nummer, Date geldig_tot, int klasse, double saldo, int reiziger_id) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger_id = reiziger_id;
        this.products = new ArrayList<>();
    }

    public OVChipkaart(Date geldig_tot, int klasse, double saldo) {
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public String toString() {
        String s = "";
        s += kaart_nummer + " " + geldig_tot + " " + klasse + " " + saldo + " " + reiziger_id;
        return s;
    }
}
