import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RandomStringsGenerator {
    public static void main(String[] args) {
        //System.out.println(get());
        Stream.generate(new Supplier<String>(){
            @Override
            public String get() {
                return IntStream
                        .generate(() -> 'A' + (int)(26*Math.random()))
                        .limit(16)
                        .mapToObj(value -> String.valueOf((char) value))
                        .collect(Collectors.joining(""));
            }
        })
                .limit(35)
                .forEach(System.out::println);
    }

    public static String get(){
        return IntStream
                .generate(() -> 'A' + (int)(26*Math.random()))
                .limit(16)
                .mapToObj(value -> String.valueOf((char) value))
                .collect(Collectors.joining());
    }
}
