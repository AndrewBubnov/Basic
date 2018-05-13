package figure;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Circle implements Figure, Serializable{
    public int radius;

    public Circle() {
        this.radius = 0;
    }

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        double s = Math.PI*this.radius*this.radius;
        s = new BigDecimal(s).setScale(3, RoundingMode.UP).doubleValue();
        return s;
    }

    public static void main(String[] args) {
        Circle circle = new Circle(3);
        System.out.println(circle.getArea());
    }
}
