
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String s1 = "SHINCHAN";
        String s2 = "NOHARAAA";
        int[][] array = new int[s1.length() + 1][s2.length() + 1];
         for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
              array[i + 1][j + 1] = s1.charAt(i) == s2.charAt(j) ? array[i][j] + 1 : Math.max(array[i][j + 1], array[i + 1][j]);
            }
        }
        int len = array[array.length - 1][array.length - 1];
        char[] lcs = new char[len];
        int index = len;
        int i = s1.length();
        int j = s2.length();
        while (i > 0 && j > 0){
            if (s1.charAt(i - 1) == s2.charAt(j - 1)){
                lcs[index - 1] = s1.charAt(i - 1);
                index--;
                i--;
                j--;
            } else if (array[i - 1][j] < array[i][j - 1]){
                j--;
            } else {
                i--;
            }
        }
        System.out.println("The longest common subsequence of strings:\n" + "\"" + s1 + "\"\n" + "and\n" + "\"" + s2 + "\"\n" + "is");
        for (int k = 0; k < len; k++) {
            System.out.printf("%c ", lcs[k]);
        }
    }
}





