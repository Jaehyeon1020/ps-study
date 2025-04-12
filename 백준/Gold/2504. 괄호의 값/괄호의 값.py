import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()

p = input()

def check():
    stack = []

    for token in p:
        stack.append(token)

        while len(stack) >= 2 and (stack[-1] == ')' or stack[-1] == ']'):
            right, left = stack[-1], stack[-2]
            if (left == '(' and right == ')') or (left == '[' and right == ']'):
                stack.pop()
                stack.pop()
            else:
                break
                
    return True if not stack else False

def get_pair_i(p, idx):
    left = 1
    right = 0

    if p[idx] == '(':
        for i in range(idx + 1, len(p)):
            if p[i] == '(':
                left += 1
            elif p[i] == ')':
                right += 1
            
            if left == right:
                return i
    elif p[idx] == '[':
        for i in range(idx + 1, len(p)):
            if p[i] == '[':
                left += 1
            elif p[i] == ']':
                right += 1
            
            if left == right:
                return i

def calculate(p):
    total = 0

    i = 0
    while i < len(p):
        token = p[i]

        if token == '(':
            i2 = get_pair_i(p, i)
            
            if i2 == i + 1:
                total += 2
            else:
                total += 2 * calculate(p[i + 1: i2])
            
            i = i2 + 1
        elif token == '[':
            i2 = get_pair_i(p, i)
            
            if i2 == i + 1:
                total += 3
            else:
                total += 3 * calculate(p[i + 1: i2])
            
            i = i2 + 1
        else:
            i += 1
    
    return total

if not check():
    print(0)
    exit()

print(calculate(p))