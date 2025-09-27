package org.howard.edu.lsp.assignment3;

/**
 * Entry point for the ETL pipeline application.
 */
public class ETLPipelineApp {
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline();
        pipeline.run();
    }
}
