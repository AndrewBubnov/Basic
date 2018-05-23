package figure;

public class Main {
    public static void main(String[] args) {

        FigureList figureList = new FigureList();
        figureList.addFigure(new Circle(3));
        figureList.addFigure(new Circle(4));
        figureList.addFigure(new Square(4));
        XMLSerializer xmlSerializator = new XMLSerializer();

        xmlSerializator.serialize(figureList);

//        xmlSerializator.serialize(new Circle(3));
//        xmlSerializator.serialize(new Square(3));
    }
}
