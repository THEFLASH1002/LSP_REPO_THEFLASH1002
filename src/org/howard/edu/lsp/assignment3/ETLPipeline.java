package org.howard.edu.lsp.assignment3;

import java.util.List;

/**
 * Coordinates the ETL steps: reading, transforming, and writing data.
 */
public class ETLPipeline {

    private final ProductReader reader = new ProductReader();
    private final ProductTransformer transformer = new ProductTransformer();
    private final ProductWriter writer = new ProductWriter();

    /**
     * Runs the complete ETL process.
     */
    public void run() {
        List<RawProduct> rawProducts = reader.read();
        List<Product> transformed = transformer.transformAll(rawProducts);
        writer.write(transformed);
    }
}
