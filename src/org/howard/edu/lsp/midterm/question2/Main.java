package org.howard.edu.lsp.midterm.question2;

public class Main {

    // HOW TO RUN:
    //  cd into "C:\Users\kgreg\HW webpage1\LSP_REPO_THEFLASH1002\src"
    //  then run  javac org/howard/edu/lsp/midterm/question2/*.java
    //  then run  java org.howard.edu.lsp.midterm.question2.Main

    /*
    * Yes, the methods in AreaCalculator are an example of method overloading.
    * Each area() method has the same name but uses different parameter types and counts 
    * (double radius, double width & height, int base & height, int side).
    * This allows one method name to handle multiple shapes instead of creating separate methods 
    * like circleArea() or rectangleArea(), making the code cleaner and more consistent.
    */


    public static void main(String[] args) {
        // Correct calls
        System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));

        // Demonstrate exception handling
        try {
            System.out.println("Circle radius -2.0 → area = " + AreaCalculator.area(-2.0));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        
    }
}
