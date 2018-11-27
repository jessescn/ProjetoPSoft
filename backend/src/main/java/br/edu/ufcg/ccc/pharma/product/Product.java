package br.edu.ufcg.ccc.pharma.product;

import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private String barcode;
    private String producer;
    private String situation;

    public Product(int id, String name, String barcode, String producer, String situation) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.producer = producer;
        this.situation = situation;
    }

    public Product() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getProducer() {
        return producer;
    }

    public String getSituation() {
        return situation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
