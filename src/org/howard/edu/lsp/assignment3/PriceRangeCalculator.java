package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Computes the price range category for a product.
 */
public class PriceRangeCalculator {
    public static String compute(BigDecimal price) {
        if (price.compareTo(new BigDecimal("0.00")) >= 0 && price.compareTo(new BigDecimal("10.00")) <= 0) {
            return "Low";
        } else if (price.compareTo(new BigDecimal("10.00")) > 0 && price.compareTo(new BigDecimal("100.00")) <= 0) {
            return "Medium";
        } else if (price.compareTo(new BigDecimal("100.00")) > 0 && price.compareTo(new BigDecimal("500.00")) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }
}
