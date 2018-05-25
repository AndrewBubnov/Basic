package figure;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class XMLSerializer implements Serializer {

    @Override
    public void serialize(Figure figure) {
        if ( figure instanceof FigureList) {
            printFigureListHeader((FigureList) figure);
        } else if ( figure instanceof Figure) {
            printFigure(figure);
        } else System.out.println("Given object can not be serialized by XMLSerializator");
    }

    private void printFigure(Figure figure) {
        try (RandomAccessFile file = new RandomAccessFile("d:\\serialize.xml", "rw")) {

                StringBuilder sb = new StringBuilder();
                String size = figure.toString().split(" ")[1];
                sb.append("<" + figure.getClass().getName().substring(7) + ">");
                sb.append("<" + size+ " = " + "\"" + figure.getSide() + "\" " + "area = " + "\"" + figure.getArea() + "\">");
                sb.append("</" + figure.getClass().getName().substring(7) + ">");

            sb.append("\n</xml>");

            xmlTagRemove(file);

            file.write(sb.toString().getBytes("UTF8"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printFigureListHeader(FigureList figureList) {
        try (RandomAccessFile file = new RandomAccessFile("d:\\serialize.xml", "rw")) {
            file.seek(file.length());
            xmlTagRemove(file);
            String s = "<FigureList total area = " + "\"" + figureList.getArea() + "\">\n";
            file.write(s.getBytes("UTF8"));
            printFigureList(figureList, 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printFigureListFooter() {
        try (RandomAccessFile file = new RandomAccessFile("d:\\serialize.xml", "rw")) {
            file.seek(file.length());
            xmlTagRemove(file);
            file.write("</FigureList>\n</xml>".getBytes("UTF8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printFigureList(FigureList figureList, int index){
        if (index == figureList.getFigureList().size()){
            printFigureListFooter();
            return;
        }
        printFigure(figureList.getFigureList().get(index));
            printFigureList(figureList, index + 1);
        }

    private RandomAccessFile xmlTagRemove(RandomAccessFile file){
        try {
            file.seek(file.length() - 6);
            int data = file.read();
            String s = "";
            while (data != -1){
                s += (char)data;
                data = file.read();
            }
            if (s.equals("</xml>")) {
                file.seek(file.length() - 7);
                file.write("\n".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void createNewFile() {
        try {
            Files.write(Paths.get("d:\\serialize.xml"),
                    Collections.singleton("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
