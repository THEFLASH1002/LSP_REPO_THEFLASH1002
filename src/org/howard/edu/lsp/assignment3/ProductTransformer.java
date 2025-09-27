package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Applies transformation logic to raw products.
 */
public class ProductTransformer {
    public List<Product> transformAll(List<RawProduct> rawProducts) {
        List<Product> result = new ArrayList<>();

        for (RawProduct raw : rawProducts) {
            try {
                int id = Integer.parseInt(raw.id);
                BigDecimal price = new BigDecimal(raw.price);
                String name = raw.name.toUpperCase();

                boolean isElectronics = raw.category.equalsIgnoreCase("Electronics");
                if (isElectronics) {
                    price = price.multiply(new BigDecimal("0.9")).setScale(2, RoundingMode.HALF_UP);
                } else {
                    price = price.setScale(2, RoundingMode.HALF_UP);
                }

                String category = (isElectronics && price.compareTo(new BigDecimal("500.00")) > 0)
                        ? "Premium Electronics"
                        : raw.category;

                String priceRange = PriceRangeCalculator.compute(price);

                result.add(new Product(id, name, price, category, priceRange));
            } catch (Exception e) {
                System.err.println("Warning: Skipping malformed input - " + e.getMessage());
            }
        }

        return result;
    }
}
