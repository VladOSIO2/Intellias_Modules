package module8;

import module8.shape.*;
import module8.shape.printer.ConsoleShapePrinter;
import module8.shape.printer.ShapePrinter;

public class Main {
    public static void main(String[] args) {
        //creating shape instances
        Circle circle = new Circle();
        Ellipse ellipse = new Ellipse();
        Square square = new Square();
        Trapezoid trapezoid = new Trapezoid();
        Triangle triangle = new Triangle();

        //we can create instances in this way
        Shape circleAsShape = new Circle();

        //anonymous class shape
        Shape strangeShape = new Shape() {
            @Override
            public String getName() {
                return "strangeShape";
            }
        };

        //shape printer creation
        ShapePrinter printer = new ConsoleShapePrinter();

        //printing shapes
        printer.print(circle);          // circle
        printer.print(ellipse);         // ellipse
        printer.print(square);          // square
        printer.print(trapezoid);       // trapezoid
        printer.print(triangle);        // triangle
        printer.print(circleAsShape);   // circle
        printer.print(strangeShape);    // strangeShape
    }
}