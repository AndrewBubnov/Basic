public class SeparateNumbers {
// https://www.hackerrank.com/challenges/separate-the-numbers/problem
    public static void main(String[] args) {
        String s = "1234";
        long answer = 0;
        boolean f = false;
        for (int i = 0; i < s.length()/2; i++) {
            StringBuilder sb = new StringBuilder();
            long temp = Long.parseLong(s.substring(0, i + 1));
            answer  = temp;
            sb.append(temp);
            while (sb.length() != s.length()) {

                if (sb.length() + Long.toString(temp + 1).length() <= s.length()) {
                    sb.append(temp + 1);
                } else break;
                temp++;
            }

            if (s.equals(sb.toString())){
                f = true;
                break;
            }
        }
        if (f) System.out.println("YES" + " " + answer);
        else System.out.println("NO");
    }
}
