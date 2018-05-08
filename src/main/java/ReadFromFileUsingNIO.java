import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFromFileUsingNIO {
    public static void main(String[] args) throws IOException {
        //Files.lines(Paths.get("d:\\Java\\strings.txt")).forEach(line -> System.out.println(line));
        Files.lines(Paths.get("d:\\Java\\strings.txt")).forEach(System.out::println);
    }
}
