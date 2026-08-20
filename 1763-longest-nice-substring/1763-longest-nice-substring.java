class Solution {
    public String longestNiceSubstring(String s) {
        int n = s.length();
        String ans = "";
        for(int i=0; i<n; i++) {
            HashSet<Character> set = new HashSet<>();
            for(int j=i; j<n; j++) {
                set.add(s.charAt(j));

                boolean nice = true;
                for(char ch : set) {
                    if(!set.contains(Character.toLowerCase(ch)) || !set.contains(Character.toUpperCase(ch))) {
                        nice = false;
                        break;
                    }
                }

                if(nice && j-i+1>ans.length()) {
                    ans = s.substring(i,j+1);
                }
            }
        }

        return ans;
    }
}