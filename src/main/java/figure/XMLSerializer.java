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

    public void printFigure(Figure figure) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("d:\\serialize.xml", "rw")) {

                StringBuilder sb = new StringBuilder();
                String size = figure.toString().split(" ")[1];
                sb.append("<" + figure.getClass().getName().substring(7) + ">");
                sb.append("<" + size+ " = " + "\"" + figure.getSide() + "\" " + "area = " + "\"" + figure.getArea() + "\">");
                sb.append("</" + figure.getClass().getName().substring(7) + ">");

            sb.append("\n</xml>");

            xmlTagRemove(randomAccessFile);

            randomAccessFile.write(sb.toString().getBytes("UTF8"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printFigureListHeader(FigureList figureList) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("d:\\serialize.xml", "rw")) {
            randomAccessFile.seek(randomAccessFile.length());
            xmlTagRemove(randomAccessFile);
            String s = "<FigureList area = " + "\"" + figureList.getArea() + "\">\n";
            randomAccessFile.write(s.getBytes("UTF8"));
            printFigureList(figureList, 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printFigureListFooter() {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("d:\\serialize.xml", "rw")) {
            randomAccessFile.seek(randomAccessFile.length());
            xmlTagRemove(randomAccessFile);
            randomAccessFile.write("</FigureList>\n</xml>".getBytes("UTF8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printFigureList(FigureList figureList, int index){
        if (index == figureList.getFigureList().size()){
            printFigureListFooter();
            return;
        }
        printFigure(figureList.getFigureList().get(index));
            printFigureList(figureList, index + 1);
        }

    private RandomAccessFile xmlTagRemove(RandomAccessFile randomAccessFile){
        try {
            randomAccessFile.seek(randomAccessFile.length() - 6);
            int data = randomAccessFile.read();
            String s = "";
            while (data != -1){
                s += (char)data;
                data = randomAccessFile.read();
            }
            if (s.equals("</xml>")) {
                randomAccessFile.seek(randomAccessFile.length() - 7);
                randomAccessFile.write("\n".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return randomAccessFile;
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
