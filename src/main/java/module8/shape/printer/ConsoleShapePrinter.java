package module8.shape.printer;

import module8.shape.Shape;

public class ConsoleShapePrinter implements ShapePrinter{
    @Override
    public void print(Shape shape) {
        System.out.println(shape.getName());
    }
}
