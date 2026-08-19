class Solution {
    public String minWindow(String s, String t) {

        if(s.length() < t.length()) return "";
        HashMap<Character, Integer> map = new HashMap<>();
        

        for(char ch:t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch,0) + 1);
        }

        int left=0; 
        int required = t.length();
        int start = 0;
        int minlength = Integer.MAX_VALUE;

        for(int right = 0; right<s.length(); right++) {
            char ch = s.charAt(right);

            if(map.containsKey(ch)) {
                if(map.get(ch) > 0) {
                    required--;
                }

                map.put(ch, map.get(ch)-1);
            }
            while(required==0) {
                if(right-left+1<minlength) {
                    minlength = right-left+1;
                    start = left;
                }

                char leftindx = s.charAt(left);
                if(map.containsKey(leftindx)) {
                    map.put(leftindx, map.get(leftindx) + 1);

                    if(map.get(leftindx) > 0) {
                        required++;
                    }
                }

                left++;
            }
        }
        if(minlength == Integer.MAX_VALUE) return ""; 
        return s.substring(start,start+minlength);
    }
}