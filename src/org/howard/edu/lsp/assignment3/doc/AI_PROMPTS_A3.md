# AI Prompts – Assignment 3

Below are examples of prompts I used when redesigning my ETL pipeline using a generative AI assistant, along with summaries of the responses.

---

## Prompt 1:
**"How can I redesign a monolithic ETL Java program into an object-oriented structure?"**

**AI Response:**
> You can separate the pipeline into multiple classes: one for reading input, one for transforming data, and one for writing output. Use a main controller to coordinate the steps.

**How I used it:**
I created `ProductReader`, `ProductTransformer`, `ProductWriter`, and `ETLPipeline` classes, each with clear responsibilities as suggested.

---

## Prompt 2:
**"Generate a Java class that transforms raw product data, applying discounts and computing price range."**

**AI Response (Excerpt):**
> Here's a class called `ProductTransformer` that receives raw product strings, parses them, applies a discount if the category is Electronics, and assigns a price range.

**How I used it:**
I used the structure provided to build the `ProductTransformer` class and made sure it aligned with the original transformation logic in Assignment 2.

---

## Prompt 3:
**"What's a good way to handle price range computation in Java?"**

**AI Response (Excerpt):**
> Use a static utility class with a method that takes a BigDecimal and returns a price range string based on defined thresholds.

**How I used it:**
I implemented the `PriceRangeCalculator` as a static utility class to separate that concern from transformation logic.

---

## Prompt 4:
**"How should I structure Javadoc for a class like ProductWriter in Java?"**

**AI Response (Excerpt):**
> Include class-level documentation and Javadoc comments on each public method explaining what it does, parameters it takes, and any exceptions it might throw.

**How I used it:**
I used this advice to document all public methods and classes in my final codebase.

---

## Prompt 5:
**"How can I test that my refactored Java ETL still produces the same output?"**

**AI Response (Excerpt):**
> Run the same input file through both versions and compare the output files line by line. Also test malformed and empty cases.

**How I used it:**
I confirmed correctness by using the same `products.csv` input from Assignment 2 and comparing the generated `transformed_products.csv` file exactly.

---
