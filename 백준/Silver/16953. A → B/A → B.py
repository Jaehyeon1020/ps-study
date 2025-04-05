import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()

A, B = map(int, input().split())

def bfs():
    visited = set()
    q = deque()
    q.append((A, 0))
    visited.add(A)

    while q:
        cur, op = q.popleft()

        if cur == B:
            return op + 1

        case1 = cur * 2
        case2 = int(str(cur) + "1")

        if case1 not in visited and case1 <= B:
            q.append((case1, op + 1))
            visited.add(case1)
        if case2 not in visited and case2 <= B:
            q.append((case2, op + 1))
            visited.add(case2)
    
    return -1

print(bfs())