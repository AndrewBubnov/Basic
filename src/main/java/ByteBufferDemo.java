import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class ByteBufferDemo {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        String s;
        FileChannel writeChannel = new FileOutputStream("d:\\test.txt\\", true).getChannel();
        ByteBuffer buffer;
        for (int i = 0; i < 10; i++) {
            s = "Andrew Bubnov. This is " + i + " channel\n";
            buffer = ByteBuffer.wrap(s.getBytes());
            writeChannel.write(buffer);
        }
        writeChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("Execution time is: " + (end - start) + " ms");
    }
}
