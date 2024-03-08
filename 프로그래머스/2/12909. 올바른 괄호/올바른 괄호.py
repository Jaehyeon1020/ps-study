def solution(s):
    stack = []
    
    for p in s:
        if len(stack) > 0 and stack[-1] == '(' and p == ')':
            stack.pop()
        else:
            stack.append(p)
    
    return True if len(stack) == 0 else False
        
            
            
    