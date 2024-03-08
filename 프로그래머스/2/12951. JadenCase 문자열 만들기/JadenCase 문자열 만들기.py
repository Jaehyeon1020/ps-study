def solution(s):
    answer = ''
    
    for i, c in enumerate(s):        
        prev = s[i - 1] if i > 0 else ''
        cur = c
        
        # 현재 글자가 알파벳
        if cur.isalpha():
            if prev == ' ' or i == 0:
                answer += cur.upper()
            else:
                answer += cur.lower()
        else:
            answer += cur
    
    return answer