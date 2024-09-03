def solution(prices):
    answer = [0 for x in range(len(prices))]
    
    stack = [[0, prices[0]]]
    
    time = 1
    while time < len(prices):
        while stack and prices[time] < stack[-1][1]:
            cur = stack.pop()
            answer[cur[0]] = time - cur[0]
        stack.append([time, prices[time]])
        time += 1
    
    while stack:
        cur = stack.pop()
        answer[cur[0]] = (len(prices) - 1) - cur[0]
    
    return answer
    
    
    
        
        