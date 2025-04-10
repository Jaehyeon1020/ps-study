import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()

m = input()
ops = set(['+', '-', '*', '/'])

def transform(tokens):
    dq = deque()

    # 괄호 제거
    for token in tokens:
        dq.append(token)

        # 괄호가 닫혔을 때
        if dq[-1] == ')':
            dq.pop() # 닫는 괄호 제거

            inner_tokens = deque()
            while dq[-1] != '(':
                inner_tokens.appendleft(dq.pop())
            
            dq.pop() # 여는 괄호 제거
            dq.append(transform(inner_tokens))
        
    # 곱셈 / 나눗셈 계산
    dq2 = deque()
    while dq:
        token = dq.popleft()

        if dq2 and (dq2[-1] == '*' or dq2[-1] == '/'):
            op = dq2.pop()
            first = dq2.pop()
            dq2.append(first + token + op)
        else:
            dq2.append(token)
    
    # 덧셈 / 뺄셈 계산
    stack = []
    while dq2:
        token = dq2.popleft()

        if stack and stack[-1] in ops:
            op = stack.pop()
            first = stack.pop()
            stack.append(first + token + op)
        else:
            stack.append(token)
    
    return stack[0]

print(transform(list(m)))