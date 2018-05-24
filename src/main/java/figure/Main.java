package figure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        FigureList figureList = new FigureList();
        figureList.addFigure(new Circle(3));
        figureList.addFigure(new Circle(4));
        figureList.addFigure(new Square(4));
        XMLSerializer.createNewFile();

        XMLSerializer xmlSerializator = new XMLSerializer();

        xmlSerializator.serialize(figureList);

        xmlSerializator.serialize(new Circle(3));
        xmlSerializator.serialize(new Square(3));

        File file = new File("d:\\serialize.xml");
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\\Z");
        System.out.println(scanner.next());
    }
}
