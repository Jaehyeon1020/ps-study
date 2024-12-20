import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c: s.toCharArray()) {
            if (stack.size() == 0) {
                stack.push(c);
            } else {
                if (stack.peek() == '(' && c == ')') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        
        if (stack.size() > 0) {
            return false;
        }
        return true;
    }
}