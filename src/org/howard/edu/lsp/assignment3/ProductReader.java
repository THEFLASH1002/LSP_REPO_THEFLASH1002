package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads raw product data from CSV.
 */
public class ProductReader {
    private static final Path INPUT_PATH = Paths.get("data", "products.csv");

    public List<RawProduct> read() {
        List<RawProduct> result = new ArrayList<>();

        if (!Files.exists(INPUT_PATH)) {
            System.err.println("Error: Input file not found at " + INPUT_PATH.toString());
            return result;
        }

        try {
            List<String> lines = Files.readAllLines(INPUT_PATH, StandardCharsets.UTF_8);
            for (int i = 1; i < lines.size(); i++) {
                String[] fields = lines.get(i).trim().split(",", -1);
                if (fields.length != 4) {
                    System.err.println("Warning: Skipping malformed line " + (i + 1));
                    continue;
                }
                result.add(new RawProduct(fields[0].trim(), fields[1].trim(), fields[2].trim(), fields[3].trim()));
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }

        return result;
    }
}
