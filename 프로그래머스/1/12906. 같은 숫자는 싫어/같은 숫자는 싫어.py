def solution(arr):
    stack = []
    
    for num in arr:
        if not stack:
            stack.append(num)
            continue
        if stack[-1] != num:
            stack.append(num)
            
    return stack
            