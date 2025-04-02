import sys
from collections import deque

sys.setrecursionlimit(10**6)
input = lambda: sys.stdin.readline().rstrip()
print = sys.stdout.write

N, R, Q = list(map(int, input().split()))

graph = {}
for _ in range(N - 1):
    U, V = list(map(int, input().split()))
    
    if U not in graph:
        graph[U] = []
    if V not in graph:
        graph[V] = []
    
    graph[U].append(V)
    graph[V].append(U)

subtree = [1 for _ in range(N + 1)]

def dfs(node, visited):
    childs = 1
    for child in graph[node]:
        if visited[child]: continue # 방문한 적 있음 -> 부모를 의미
        visited[child] = True
        childs += dfs(child, visited)
    
    subtree[node] = childs
    return childs

visited = [False for _ in range(N + 1)]
visited[R] = True
dfs(R, visited)

# print(str(subtree))

for _ in range(Q):
    U = int(input())
    print(str(subtree[U]) + "\n")