package figure;

public class Square extends Figure {
    int side;

    public Square(int side) {
        this.side = side;
    }

    public int getSide() {
        return side;
    }

    public double getArea() {
        return side*side;
    }

    @Override
    public String toString(){
        return "Square side = " + side;
    }
}
