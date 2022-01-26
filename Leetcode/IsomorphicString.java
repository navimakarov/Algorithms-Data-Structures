public class IsomorphicString {
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length())
            return false;
        Map<Character, Character> map = new HashMap<>();
        Set<Character> values = new HashSet<>();
        char c, c1;
        for(int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            c1 = t.charAt(i);
            if(map.containsKey(c) && map.get(c) != c1)
                return false;
            else if(!map.containsKey(c)) {
               if(values.contains(c1))
                   return false;
               map.put(c, c1); 
               values.add(c1);
            }
        }
        return true;
    }
}