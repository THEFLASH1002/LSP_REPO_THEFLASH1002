package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

/**
 * Writes transformed product data to CSV.
 */
public class ProductWriter {
    private static final Path OUTPUT_PATH = Paths.get("data", "transformed_products.csv");
    private static final String HEADER = "ProductID,Name,Price,Category,PriceRange";

    public void write(List<Product> products) {
        try {
            if (OUTPUT_PATH.getParent() != null) {
                Files.createDirectories(OUTPUT_PATH.getParent());
            }

            try (BufferedWriter writer = Files.newBufferedWriter(OUTPUT_PATH, StandardCharsets.UTF_8)) {
                writer.write(HEADER);
                writer.newLine();
                for (Product product : products) {
                    writer.write(product.toCsvLine());
                    writer.newLine();
                }
            }

            System.out.println("Output written to " + OUTPUT_PATH.toString());
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
        }
    }
}
