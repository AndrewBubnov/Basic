import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTransfer {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream("C:\\Users\\User\\Desktop\\half-life-2-1.png");
             FileOutputStream fos = new FileOutputStream("d:\\test1.png");){
             byte[] buffer = new byte[1024];
             int byteWritten = 0;
             int size;
             while ((size = fis.read(buffer)) > 0){
               fos.write(buffer, 0, size);
               byteWritten += size;
             }
             System.out.println("Total byte trasferred: " + byteWritten + " byte");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println("Execution time is: " + (end - start) + " ms");
    }


}
