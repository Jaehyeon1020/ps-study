from collections import deque

def solution(cacheSize, cities):
    cache_check = {}
    cache = deque()
    run_time = 0
    
    for city in cities:
        city_name = city.lower()
        
        # cache에 이미 city가 존재하는 경우
        if city_name in cache_check:
            run_time += 1
            for idx, name in enumerate(cache):
                if name == city_name:
                    del cache[idx]
                    break
            cache.append(city_name)
            
        # cache에 city가 존재하지 않는 경우
        else:
            run_time += 5
            cache.append(city_name)
            cache_check[city_name] = ''
            
            if len(cache) > cacheSize:
                name = cache.popleft()
                del cache_check[name]
                
    return run_time