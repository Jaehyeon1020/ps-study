def solution(n, m, section):
    # n = 벽, m = 롤러
    
    count = 0
    filled = 0
    wall = [1 for i in range(n + 1)]
    
    for s in section:
        wall[s] = 0
    
    for i in range(1, n + 1):
        end = i + m - 1
        
        if wall[i] == 0 and end <= n:
            count += 1
            for j in range(i, end + 1):
                wall[j] = 1
                i = end
                
    for i in range(n, 0, -1):
        start = n - (m - 1)
        
        if wall[i] == 0 and start >= 1:
            count += 1
            for j in range(start, i + 1):
                wall[j] = 1
                i = start
    
    return count