import java.io.*;
import java.nio.charset.StandardCharsets;

public class InputOutput extends Thread{
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            InputOutput inputOutput = new InputOutput();
            inputOutput.start();
            String string = "Andrew Bubnov - " + i + " thread\n";
            InputStream stream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
            try (Writer writer = new FileWriter("D:\\test.txt\\", true)) {
                int c = stream.read();
                while (c != -1) {
                    writer.write(c);
                    c = stream.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName());
    }
}



