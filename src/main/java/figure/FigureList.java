package figure;

import java.util.ArrayList;
import java.util.List;

public class FigureList extends Figure {
    private List<Figure> figureList = new ArrayList<>();

    public void addFigure (Figure figure){
        this.figureList.add(figure);
    }

    @Override
    public double getArea() {
        double area = 0;
        for (Figure figure : figureList){
            area += figure.getArea();
        }
        return area;
    }

    @Override
    public int getSide() {
        return 0;
    }

    public List<Figure> getFigureList() {
        return figureList;
    }
}
