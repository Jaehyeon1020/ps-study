def solution(s, n):
    answer = ''
    low = "abcdefghijklmnopqrstuvwxyz" * 2
    cap = low.upper()
    
    for ss in s:
        if ss.islower():
            answer += low[low.index(ss) + n]
        elif ss.isupper():
            answer += cap[cap.index(ss) + n]
        else:
            answer += ' '
    
    return answer