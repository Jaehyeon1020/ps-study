def solution(s):
    answer = []
    
    dict = {}
    for i in range(len(s)):
        cur = s[i]
        
        try:
            answer.append(i - dict[cur])
        except:
            answer.append(-1)
            
        dict[cur] = i
    
    return answer