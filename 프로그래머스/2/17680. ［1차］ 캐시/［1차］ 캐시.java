import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return cities.length * 5;
        
        int answer = 0;
        
        var cache = new ArrayList<String>();
        for (int i = 0; i < cities.length; i++) {
            var city = cities[i].toLowerCase();
            
            if (cache.contains(city)) {
                cache.remove(city);
                cache.add(city);
                answer += 1;
            } else {
                if (cache.size() == cacheSize) cache.remove(0);
                cache.add(city);
                answer += 5;
            }
        }
        
        return answer;
    }
}