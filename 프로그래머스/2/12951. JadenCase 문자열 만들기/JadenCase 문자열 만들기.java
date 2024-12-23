import java.util.*;

class Solution {
    public String solution(String s) {
        ArrayList<Character> cArr = new ArrayList<>();
        for (char c: s.toCharArray()) {
            cArr.add(c);
        }
        
        for (int i=0; i < cArr.size(); i++) {
            if (i == 0 && Character.isLetter(cArr.get(i))) {
                cArr.set(i, Character.toUpperCase(cArr.get(i)));
            }
            else if (i > 0 && cArr.get(i - 1) == ' ' && Character.isLetter(cArr.get(i))) {
                cArr.set(i, Character.toUpperCase(cArr.get(i)));
            } else if (cArr.get(i) != ' ') {
                cArr.set(i, Character.toLowerCase(cArr.get(i)));
            }
        }
        
        char[] newCArr = new char[cArr.size()];
        for (int i = 0; i < cArr.size(); i++) {
            newCArr[i] = cArr.get(i);
        }
        return new String(newCArr);
    }
}