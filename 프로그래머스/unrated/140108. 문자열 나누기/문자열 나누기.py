def solution(s):
    answer = 0
    
    same = 0
    diff = 0
    x = None
    tmp = ""
    
    for ss in s:
        if not x:
            x = ss
            same = 1
            tmp += ss
        elif x == ss:
            same += 1
            tmp += ss
        else:
            diff += 1
            tmp += ss
        
        if same == diff:
            answer +=1
            same = 0
            diff = 0
            x = None
            tmp = ""
        
    if tmp:
        answer += 1
    
    return answer