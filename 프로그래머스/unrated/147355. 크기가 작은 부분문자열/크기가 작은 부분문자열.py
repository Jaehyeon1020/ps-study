def solution(t, p):
    answer = 0
    
    num = int(p)
    numLength = len(p)
    
    for i in range(len(t) - (numLength - 1)):
        if int(t[i:i+numLength]) <= num:
            answer += 1
    
    return answer