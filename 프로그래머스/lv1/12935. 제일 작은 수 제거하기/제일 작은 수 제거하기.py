def solution(arr):
    answer = []
    
    minVal = min(arr)
    for n in arr:
        if n != minVal:
            answer.append(n)
        
    return answer if len(answer) != 0 else [-1]