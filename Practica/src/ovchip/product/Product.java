package ovchip.product;

import ovchip.ovchipkaart.OVChipkaart;

import java.util.ArrayList;
import java.util.List;

public class Product {
    public int product_nummer;
    public String naam;
    public String beschrijving;
    public double prijs;
    public List<OVChipkaart> ovChipkaartList;

    public Product(String naam, String beschrijving, double prijs) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.ovChipkaartList = new ArrayList<>();
    }

    public String toString() {
        return naam + ", " + beschrijving + ", " + prijs;
    }
}
