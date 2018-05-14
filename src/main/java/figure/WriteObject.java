package figure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class WriteObject {
     public static void main(String[] args) throws ParserConfigurationException, TransformerException, FileNotFoundException {

        String fileName = "serialize.txt";
        String fileNameXML = "test.xml";

        serializator(new Circle(3), fileName);
        serializator(new Circle(4), fileName);
        serializator(new Circle(5), fileName);
        xmlWrite(new Circle(3), fileNameXML);
        xmlWrite(new Circle(5), fileNameXML);
        gsonWrite(new Circle(3));
    }

    private static void gsonWrite(Circle obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(obj));
    }

    private static void xmlWrite(Circle obj, String fileName) throws ParserConfigurationException, TransformerException, FileNotFoundException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        org.w3c.dom.Document document = builder.newDocument();
        Element root = document.createElement("root");
        Element circle = document.createElement("Circle");
        circle.setAttribute("radius", Integer.toString(obj.getRadius()));
        Text text = document.createTextNode("First XML-written object");
        document.appendChild(root);
        root.appendChild(circle);
        circle.appendChild(text);
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(fileName)));
    }

    private static void serializator(Circle circle, String fileName) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
           oos.writeObject(circle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
