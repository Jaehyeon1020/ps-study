def solution(prices):
    answer = [0 for x in range(len(prices))]
    
    for i, price in enumerate(prices):
        for j in range(i + 1, len(prices)):
            if prices[j] < price:
                answer[i] = j - i
                break
            else:
                answer[i] += 1
    
    return answer
    
    
    
        
        