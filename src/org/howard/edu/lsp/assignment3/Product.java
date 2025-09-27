package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a fully transformed product.
 */
public class Product {
    private final int id;
    private final String name;
    private final BigDecimal price;
    private final String category;
    private final String priceRange;

    public Product(int id, String name, BigDecimal price, String category, String priceRange) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = priceRange;
    }

    public String toCsvLine() {
        return id + "," + name + "," + price.setScale(2, RoundingMode.HALF_UP).toPlainString() +
                "," + category + "," + priceRange;
    }
}
