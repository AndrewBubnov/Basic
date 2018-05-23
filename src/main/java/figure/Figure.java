package figure;

public abstract class Figure {
    public abstract double getArea();
    public abstract int getSide();

    @Override
    public String toString(){
        return getClass().getSimpleName()+ " with side = " + getSide() + " figure area = " + getArea();
    }
}
