def solution(s):
    answer = ''
    
    idx = 0
    for ss in s:
        if ss == ' ':
            idx = 0
            answer += ss
            continue
        
        if idx % 2 == 0:
            answer += ss.capitalize()
        else:
            answer += ss.lower()
        
        idx += 1
    
    return answer