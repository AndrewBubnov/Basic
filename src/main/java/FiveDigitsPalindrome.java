import java.util.ArrayList;
import java.util.List;

public class FiveDigitsPalindrome {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<Long> list = new ArrayList<>();
        for (long i = 99999; i >= 10000; i--) {
            if (isSimpleImproved(i)) {
                list.add(i);
                for (int j = list.size() - 1; j >= 0; j--) {
                    long n = i * list.get(j);
                    if (isPalindrome(n)) {
                        System.out.println("Biggest FiveDigitsPalindrome is: " + n);
                        System.out.println("factor#1 = " + i + "; factor#2 = " + list.get(j));
                        long end = System.currentTimeMillis();
                        System.out.println("Execution time is: " + (end - start) + "ms");
                        return;
                    }
                }
            }
        }

    }

    private static boolean isPalindrome(long n){
        char[] arr = (n + "").toCharArray();
        boolean f = true;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if (arr[i] != arr[len - 1 - i]) {
                f = false;
                break;
            }
        }
        return f;
    }


    private static boolean isSimpleImproved(long n) {
        long i = 3;
        if ((n / 2) * 2 != n) {
            for (i = 3; i < n / 2 + 1; i = i + 2) {
                if ((n / i) * i == n) {
                    break;
                }
            }
        }
        return i == n / 2 + 1;
    }
}
