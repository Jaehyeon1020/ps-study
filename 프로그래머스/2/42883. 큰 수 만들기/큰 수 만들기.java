import java.util.*;

class Solution {
    public String solution(String number, int k) {
        var stack = new ArrayDeque<Character>();
        var sb = new StringBuilder();
        
        for (char c: number.toCharArray()) {
            while (stack.size() > 0 && k > 0 && stack.peekLast() < c) {
                stack.removeLast();
                k -= 1;
            }
            stack.addLast(c);
        }
        
        while (stack.size() > 0) {
            sb.append(Character.toString(stack.removeFirst()));
        }
        
        var str = sb.toString();
        return str.length() > number.length() - k ? str.substring(0, number.length() - k) : str;
    }
}