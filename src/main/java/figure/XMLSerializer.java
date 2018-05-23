package figure;

import java.io.FileWriter;
import java.io.IOException;

public class XMLSerializer implements Serializer {

    @Override
    public void serialize(Figure figure) {
        if ( figure instanceof FigureList) {
            printFigureList((FigureList) figure, 0);
        } else if ( figure instanceof Figure) {
            printFigure(figure);
        } else System.out.println("Given object can not be serialized by XMLSerializator");
    }

    public void printFigure(Figure figure) {
        try (FileWriter fileWriter = new FileWriter("d:\\serialize.xml", true)){
            StringBuilder sb = new StringBuilder();
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                String size = figure.toString().split(" ")[1];
                sb.append("<" + figure.getClass().getName().substring(7) + ">");
                sb.append("<" + size+ " = " + "\"" + figure.getSide() + "\" " + "area = " + "\"" + figure.getArea() + "\">");
                sb.append("</" + figure.getClass().getName().substring(7) + ">\n");

            sb.append("</xml>\n\n");
            fileWriter.write(sb.toString());
            System.out.println(sb);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void printFigureList(FigureList figureList, int index){
        if (index == figureList.getFigureList().size()){
            return;
        }
        printFigure(figureList.getFigureList().get(index));
            printFigureList(figureList, index + 1);
        }
}
