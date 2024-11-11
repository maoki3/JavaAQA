package Lesson_5;

interface Shape {

    double area();
    double perimeter();

    default void displayInfo(String fillColor, String borderColor) {
        System.out.println("Периметр: " + perimeter());
        System.out.println("Площадь: " + area());
        System.out.println("Цвет заливки: " + fillColor);
        System.out.println("Цвет границы: " + borderColor);
    }
}

class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double perimeter() {
        return 2 * (width + height);
    }

    @Override
    public double area() {
        return width * height;
    }
}

class Triangle implements Shape {
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public double perimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public double area() {
        double s = perimeter() / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }
}

class Mainn {
    public static void main(String[] args) {
        Shape circle = new Circle(6);
        Shape rectangle = new Rectangle(5, 4);
        Shape triangle = new Triangle(3, 2, 1);

        System.out.println("Круг:");
        circle.displayInfo("Белый", "Белый");
        System.out.println();

        System.out.println("Прямоугольник:");
        rectangle.displayInfo("Синий", "Синий");
        System.out.println();

        System.out.println("Треугольник:");
        triangle.displayInfo("Красный", "Красный");
    }
}