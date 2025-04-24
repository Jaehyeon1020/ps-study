import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()

N, T = map(int, input().split())

d = [-2, -1, 0, 1, 2]

graph = {}
graph["0 0"] = []
for _ in range(N):
    pos_str = input()
    x, y = map(int, pos_str.split())

    if pos_str not in graph:
        graph[pos_str] = []
    
    for dx in d:
        for dy in d:
            if dx == dy == 0: continue

            nx, ny = x + dx, y + dy
            key = f"{nx} {ny}"

            if key in graph:
                graph[pos_str].append((nx, ny))
                graph[key].append((x, y))

def bfs():
    visited = set()
    q = deque()

    q.append((0, 0, 0)) # x, y, dist
    visited.add("0 0")

    while q:
        x, y, dist = q.popleft()
        key = f"{x} {y}"

        if y == T:
            return dist

        for nx, ny in graph[key]:
            nkey = f"{nx} {ny}"
            if nkey not in visited:
                q.append((nx, ny, dist + 1))
                visited.add(nkey)
    
    return -1

print(bfs())