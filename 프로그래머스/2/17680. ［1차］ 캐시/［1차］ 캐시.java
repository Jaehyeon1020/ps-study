import java.util.*;

class Solution {
    class CacheElement {
        public String city;
        public int time;
        
        public CacheElement(String city, int time) {
            this.city = city;
            this.time = time;
        }
    };
        
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return cities.length * 5;
        
        int answer = 0;
        
        var cache = new ArrayList<CacheElement>();
        for (var city: cities) {
            city = city.toLowerCase();
            for (var element: cache) element.time += 1; // 시간 증가
            
            var cityFound = false;
            
            // 캐시에 해당 도시가 있는지 확인
            for (var element: cache) {
                if (element.city.equals(city)) {
                    answer += 1;
                    element.time = 0;
                    cityFound = true;
                    break;
                }
            }
            
            // 캐시에 없으면 추가
            if (!cityFound) {
                if (cache.size() < cacheSize) {
                    cache.add(new CacheElement(city, 0));
                } else {
                    int maxIdx = 0;
                    int maxTime = 0;
                    for (int i = 0; i < cache.size(); i++) {
                        if (cache.get(i).time > maxTime) {
                            maxTime = cache.get(i).time;
                            maxIdx = i;
                        }
                    }
                    
                    cache.remove(maxIdx);
                    cache.add(new CacheElement(city, 0));
                }
                
                answer += 5;
            }
        }
        
        return answer;
    }
}