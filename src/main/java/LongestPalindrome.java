public class LongestPalindrome {
    public static void main(String[] args) {
        String s = "ljhabbavbhaabbbaat";
        long start = System.currentTimeMillis();
        String out = "";
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            StringBuilder current = new StringBuilder();
            current.append(s.charAt(i));
            for (int j = i + 1; j < s.length(); j++ ) {
                current.append(s.charAt(j));
                if (!current.toString().equals(current.reverse().toString())){
                    current.reverse();
                } else {
                    if (current.length() >= maxLength){
                        out = current.reverse().toString();
                        maxLength = current.length();
                    }
                }
            }
        }
        long end = System.currentTimeMillis();
        if (out.length() > 0)System.out.println("The longest palindrome is:" + " \"" + out + "\"." +
                " It's length = " + maxLength + " symbols.");
        else System.out.println("Palindroms not found");
        System.out.println("Execution time is: " + (end - start) + " ms.");
    }
}
