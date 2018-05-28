import java.util.ArrayList;
import java.util.List;

public class FiveDigitsPalindrome {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<Long> list = new ArrayList<>();
        for (long first = 99999; first >= 10000; first--) {
            if ((first >> 1) << 1 != first) {
                if (isSimpleImproved(first)) {
                    list.add(first);
                    for (int second = list.size() - 1; second >= 0; second--) {
                        long n = first * list.get(second);
                        if (isPalindrome(n)) {
                            System.out.println("Biggest FiveDigitsPalindrome is: " + n);
                            System.out.println("factor#1 = " + first + "; factor#2 = " + list.get(second));
                            long end = System.currentTimeMillis();
                            System.out.println("Execution time is: " + (end - start) + "ms");
                            return;
                        }
                    }
                }
            }
        }
    }

    private static boolean isPalindrome(long n){
        char[] arr = String.valueOf(n).toCharArray();
        int len = arr.length;
        int i;
        for (i = 0; i < len; i++) {
            if (arr[i] != arr[len - 1 - i]) {
                break;
            }
        }
        return i == len;
    }


    private static boolean isSimpleImproved(long number) {
        long i;
        int finish = (int)(number>>1) + 1;
            for (i = 3; i < finish; i = i + 2) {
                if ((number / i) * i == number) {
                    break;
                }
            }
        return i >= finish;
    }
}
