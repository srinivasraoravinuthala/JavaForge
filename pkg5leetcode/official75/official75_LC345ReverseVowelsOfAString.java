package pkg5leetcode.official75;

/*
 * Reverse Vowels of a String | LC 345
 * APPROACH: Two pointers swap vowels from both ends.
 * COMPLEXITY: Time O(n), Space O(n)
 */
public class official75_LC345ReverseVowelsOfAString {
    static boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    static String reverseVowels(String s) {
        char[] a = s.toCharArray();
        int l = 0, r = a.length - 1;
        while (l < r) {
            while (l < r && !isVowel(a[l])) l++;
            while (l < r && !isVowel(a[r])) r--;
            char t = a[l]; a[l] = a[r]; a[r] = t;
            l++; r--;
        }
        return new String(a);
    }

    public static void main(String[] args) {
        check("AceCreIm".equals(reverseVowels("IceCreAm")), "case1");
        check("leotcede".equals(reverseVowels("leetcode")), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
