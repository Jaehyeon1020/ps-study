def solution(s):
    stack = []
    
    for bracket in s:
        if stack and stack[-1] == '(' and bracket == ')':
            stack.pop()
        else:
            stack.append(bracket)
    
    return True if not stack else False
        
            
            
    