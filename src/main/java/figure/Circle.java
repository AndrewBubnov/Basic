package figure;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Circle extends Figure {

    int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    public int getSide() {
        return radius;
    }

    public double getArea() {
        double s = Math.PI*this.radius*this.radius;
        s = new BigDecimal(s).setScale(3, RoundingMode.UP).doubleValue();
        return s;
    }
    @Override
    public String toString(){
      return "Circle radius = " + radius;
    }
}
