public class RomanToInteger {
     public int romanToInt(String s) {
        HashMap<Character, Integer> romanMap = new HashMap<Character, Integer>();
		romanMap.put('I',1);
		romanMap.put('V',5);
		romanMap.put('X',10);
		romanMap.put('L',50);
		romanMap.put('C',100);
		romanMap.put('D',500);
		romanMap.put('M',1000);
		int returnNumber = romanMap.get(s.charAt(s.length()-1));
		for(int i=0; i < s.length()-1; i++){
	        if(romanMap.get(s.charAt(i)) < romanMap.get(s.charAt(i+1))) {
	        	returnNumber -= romanMap.get(s.charAt(i));
	        } else {
	        	returnNumber += romanMap.get(s.charAt(i));
	        }
		}
        
		return returnNumber;
    }
}