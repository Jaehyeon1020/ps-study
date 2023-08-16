def solution(arr):
    stack = []
    
    for n in arr:
        if len(stack) == 0:
            stack.append(n)
            continue  
        if n == stack[-1]:
            continue
        stack.append(n)
    
    return stack