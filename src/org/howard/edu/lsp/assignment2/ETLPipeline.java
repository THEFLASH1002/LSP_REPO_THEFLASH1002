package src.org.howard.edu.lsp.assignment2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ETLPipeline {

    private static final Path INPUT_PATH = Paths.get("data", "products.csv");
    private static final Path OUTPUT_PATH = Paths.get("data", "transformed_products.csv");
    private static final String OUTPUT_HEADER = "ProductID,Name,Price,Category,PriceRange";

    // Main ETL pipeline entry point
    public static void main(String[] args) {
        int rowsRead = 0; // Count of input rows processed
        int transformedCount = 0; // Successfully transformed rows
        int skippedCount = 0; // Rows skipped due to errors/malformed data

        
        if (!Files.exists(INPUT_PATH)) {
            System.err.println("Error: Input file 'data/products.csv' not found. " +
                    "Please run the program from the project root and ensure data/products.csv exists.");
            return;
        }

        List<String> lines;
        try {
            lines = Files.readAllLines(INPUT_PATH, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return;
        }

        
        try {
            if (OUTPUT_PATH.getParent() != null) {
                Files.createDirectories(OUTPUT_PATH.getParent());
            }
        } catch (IOException e) {
            System.err.println("Error creating data directory: " + e.getMessage());
            return;
        }

        
        if (lines.isEmpty()) {
            writeHeaderOnlyOutput();
            System.out.println("Run summary:");
            System.out.println("Rows read: 0");
            System.out.println("Transformed: 0");
            System.out.println("Skipped: 0");
            System.out.println("Output written to: " + OUTPUT_PATH.toString());
            return;
        }

        
        List<Product> transformedProducts = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String raw = lines.get(i).trim();
            if (raw.isEmpty()) {
                skippedCount++;
                continue;
            }
            rowsRead++;

            String[] fields = raw.split(",", -1);
            if (fields.length != 4) {
                skippedCount++;
                System.err.println("Warning: skipping malformed line " + (i + 1) + ": wrong number of columns.");
                continue;
            }

            String idStr = fields[0].trim();
            String name = fields[1].trim();
            String priceStr = fields[2].trim();
            String category = fields[3].trim();

            try {
                int id = Integer.parseInt(idStr);
                BigDecimal price = new BigDecimal(priceStr);

                Product p = transform(id, name, price, category);
                transformedProducts.add(p);
                transformedCount++;
            } catch (Exception e) {
                skippedCount++;
                System.err.println("Warning: skipping malformed line " + (i + 1) + ": " + e.getMessage());
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(OUTPUT_PATH, StandardCharsets.UTF_8)) {
            writer.write(OUTPUT_HEADER);
            writer.newLine();
            for (Product p : transformedProducts) {
                writer.write(p.toCsvLine());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
            return;
        }

        
        System.out.println("Run summary:");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Transformed: " + transformedCount);
        System.out.println("Skipped: " + skippedCount);
        System.out.println("Output written to: " + OUTPUT_PATH.toString());
    }

    private static void writeHeaderOnlyOutput() {
        try (BufferedWriter writer = Files.newBufferedWriter(OUTPUT_PATH, StandardCharsets.UTF_8)) {
            writer.write(OUTPUT_HEADER);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing header-only output file: " + e.getMessage());
        }
    }

    // Apply transformations in required order:
    // 1) Uppercase name
    // 2) Apply discount if Electronics
    // 3) Re-categorize if needed
    // 4) Compute PriceRange
    private static Product transform(int id, String name, BigDecimal price, String originalCategory) {
        String nameUpper = name.toUpperCase();

        boolean wasElectronics = originalCategory.equalsIgnoreCase("Electronics");

        
        if (wasElectronics) {
            price = price.multiply(new BigDecimal("0.9"));
            price = price.setScale(2, RoundingMode.HALF_UP);
        } else {
            
            price = price.setScale(2, RoundingMode.HALF_UP);
        }

        String finalCategory = originalCategory;
        if (wasElectronics && price.compareTo(new BigDecimal("500.00")) > 0) {
            finalCategory = "Premium Electronics";
        }

        
        String priceRange = computePriceRange(price);

        return new Product(id, nameUpper, price, finalCategory, priceRange);
    }

    // Decide which PriceRange bucket the final price belongs to
    private static String computePriceRange(BigDecimal price) {
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


    
    private static class Product {
        private final int id;
        private final String name;
        private final BigDecimal price;
        private final String category;
        private final String priceRange;

        Product(int id, String name, BigDecimal price, String category, String priceRange) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.category = category;
            this.priceRange = priceRange;
        }

        String toCsvLine() {
            String priceStr = price.setScale(2, RoundingMode.HALF_UP).toPlainString();
            return id + "," + name + "," + priceStr + "," + category + "," + priceRange;
        }
    }
}
