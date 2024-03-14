def solution(n):
    answer = 0
    
    sum_n = 0
    for i in range(1, n + 1):
        sum_n = i
        if i == n:
            answer += 1
            break
            
        for j in range(i + 1, n + 1):
            sum_n += j
            if sum_n == n:
                answer += 1
                break
            elif sum_n > n:
                break
        
    return answer