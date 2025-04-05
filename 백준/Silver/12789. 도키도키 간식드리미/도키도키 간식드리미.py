import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()

N = int(input())
line = list(map(int, input().split()))

def move():
    cur = 1
    stack = []
    q = deque()

    for n in line:
        q.append(n)

    while q:
        if q[0] == cur:
            q.popleft()
            cur += 1
        elif stack and stack[-1] == cur:
            stack.pop()
            cur += 1
        else:
            stack.append(q.popleft())
    
    while stack:
        if cur != stack.pop():
            return "Sad"
        cur += 1
    
    return "Nice"

print(move())