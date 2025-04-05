import sys

sys.setrecursionlimit(10**6)
input = lambda: sys.stdin.readline().rstrip()

A, B = map(int, input().split())

def backtrack(cur, op):
    if cur == B:
        return op + 1
    if cur > B:
        return -1
    
    case1 = backtrack(cur * 2, op + 1)
    case2 = backtrack(int(str(cur) + "1"), op + 1)

    if case1 != -1 and case2 != -1:
        return min(case1, case2)
    elif case1 == -1 and case2 == -1:
        return -1
    elif case1 == -1:
        return case2
    elif case2 == -1:
        return case1

print(backtrack(A, 0))