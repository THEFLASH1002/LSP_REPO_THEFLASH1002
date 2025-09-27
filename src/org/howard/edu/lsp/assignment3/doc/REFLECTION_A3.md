# Reflection – Assignment 3

## Design Comparison: Assignment 2 vs. Assignment 3

### Assignment 2
In Assignment 2, I implemented the ETL pipeline entirely within a single `ETLPipeline` class. All logic was centralized:
- Reading from the input file
- Parsing and validating data
- Transforming product information
- Writing the output file
- Handling errors and reporting

While functional, the code violated many object-oriented principles. It lacked modularity, was hard to test, and changes in one part could affect unrelated logic.

### Assignment 3
In Assignment 3, I redesigned the code using object-oriented principles. The system is now broken down into dedicated classes with specific responsibilities:
- `ETLPipelineApp` for the entry point
- `ETLPipeline` to coordinate the ETL steps
- `ProductReader` for reading raw data
- `ProductTransformer` for data transformation logic
- `ProductWriter` for writing the final output
- `PriceRangeCalculator` for computing the price range
- `RawProduct` and `Product` to represent data

This separation of concerns made the code cleaner, easier to maintain, and testable.

## Object-Oriented Concepts Used

- **Object and Class:** Each logical part of the ETL pipeline was modeled as a class. Products are modeled as objects (`Product`, `RawProduct`).
- **Encapsulation:** Data and operations are encapsulated within each class. For example, transformation logic is contained in `ProductTransformer`.
- **Polymorphism:** Not explicitly used, but the design could easily be extended with polymorphism (e.g., different transformer classes for product types).
- **Inheritance:** Not used in this version, but the separation allows adding inheritance if needed (e.g., subclassing `ProductTransformer`).
- **Abstraction:** Each class exposes a minimal interface, hiding internal implementation (e.g., file operations in `ProductReader`).

## Testing and Verification

To ensure the new version behaves identically to Assignment 2:
- I used the same input file: `data/products.csv`
- I compared the output: `data/transformed_products.csv` line by line
- I tested edge cases:
  - Missing or empty input file
  - Malformed rows (wrong number of columns or invalid price format)
  - Empty rows

The results matched exactly. Output formatting, row counts, and error messages remained consistent.

## Conclusion

The redesign improved structure, clarity, and maintainability without changing functionality. Using object-oriented design principles allowed the ETL pipeline to be more modular and easier to extend or test in the future.
