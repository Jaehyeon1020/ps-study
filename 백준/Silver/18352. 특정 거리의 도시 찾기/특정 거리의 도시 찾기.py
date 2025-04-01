import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()

N, M, K, X = list(map(int, input().split()))

edges = [list(map(int, input().split())) for _ in range(M)]
graph = {}
for e in edges:
    src, dest = e

    if src not in graph:
        graph[src] = []
    if dest not in graph:
        graph[dest] = []

    graph[src].append(dest)

cities = []

def bfs():
    q = deque()
    visited = set()

    q.append((X, 0))
    visited.add(X)

    while q:
        cur_node, cur_dist = q.popleft()

        if cur_dist == K:
            cities.append(cur_node)

        for next in graph[cur_node]:
            if next not in visited:
                visited.add(next)
                q.append((next, cur_dist + 1))

bfs()
cities.sort()

if cities:
    for city in cities:
        print(city)
else:
    print(-1)