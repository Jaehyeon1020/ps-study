def solution(d, budget):
    answer = 0
    
    d.sort()
    for cost in d:
        if budget - cost >= 0:
            answer += 1
            budget -= cost 
    
    return answer