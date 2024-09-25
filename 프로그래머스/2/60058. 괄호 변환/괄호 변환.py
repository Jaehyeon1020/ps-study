def flip(single_p):
    if single_p == '(':
        return ')'
    return '('

def get_split_idx(p): 
    for i in range(2, len(p) + 1):
        if p[:i].count('(') == p[:i].count(')'):
            return i
    return 2

def is_correct(p):
    stack = []
    for single in p:
        if single == '(':
            stack.append(single)
        else:
            if stack and stack[-1] == '(':
                stack.pop()
            else:
                return False
    
    return False if stack else True
        

def correct(p):
    corrected = []
    
    if len(p) == 0:
        return []
    
    split_idx = get_split_idx(p)
    u = p[:split_idx]
    v = p[split_idx:]
    
    if is_correct(u):
        corrected.extend(u)
        corrected.extend(correct(v))
    else:
        corrected.append('(')
        corrected.extend(correct(v))
        corrected.append(')')
        for idx, single in enumerate(u):
            if idx == 0 or idx == len(u) - 1:
                continue
            corrected.append(flip(single))
            
    return corrected

def solution(p):
    p_list = list(p)
    
    if len(p_list) == 0:
        return ''
    if is_correct(p_list):
        return ''.join(p_list)
    
    return ''.join(correct(p_list))