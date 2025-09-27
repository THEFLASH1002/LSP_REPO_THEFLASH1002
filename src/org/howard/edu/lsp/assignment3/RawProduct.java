package org.howard.edu.lsp.assignment3;

/**
 * Represents a raw product read from CSV before transformation.
 */
public class RawProduct {
    public final String id;
    public final String name;
    public final String price;
    public final String category;

    public RawProduct(String id, String name, String price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
