class Solution {
    public String longestNiceSubstring(String s) {

        if (s.length() < 2) {
            return "";
        }

        Set<Character> set = new HashSet<>();

        for (char ch : s.toCharArray()) {
            set.add(ch);
        }

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (!set.contains(Character.toLowerCase(ch)) ||
                !set.contains(Character.toUpperCase(ch))) {

                String left = longestNiceSubstring(s.substring(0, i));
                String right = longestNiceSubstring(s.substring(i + 1));

                return left.length() >= right.length() ? left : right;
            }
        }

        return s;
    }
}