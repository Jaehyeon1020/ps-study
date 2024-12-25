import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        var answer = 1;
        
        var closet = new HashMap<String, ArrayList<String>>();
        for (var c: clothes) {
            var name = c[0]; var kind = c[1];
            
            if (!closet.containsKey(kind)) {
                var newWears = new ArrayList<String>();
                newWears.add(name);
                closet.put(kind, newWears);
            } else {
                var wears = closet.get(kind);
                wears.add(name);
            }
        }
        
        for (var kind: closet.keySet()) {
            var wears = closet.get(kind);
            answer *= wears.size() + 1;
        }
        answer -= 1;
        
        return answer;
    }
}