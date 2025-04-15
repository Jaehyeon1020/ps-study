import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()

N, M = map(int, input().split())
maze = [list(map(int, list(input()))) for _ in range(N)]

def is_valid(x, y):
    return 0 <= x < N and 0 <= y < M

def bfs():
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]
    visited = [[False] * M for _ in range(N)]
    q = deque()

    q.append((0, 0, 1))
    visited[0][0] = True

    while q:
        x, y, dist = q.popleft()

        if x == N - 1 and y == M - 1:
            return dist
        
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if is_valid(nx, ny) and not visited[nx][ny] and maze[nx][ny] == 1:
                q.append((nx, ny, dist + 1))
                visited[nx][ny] = True
    
    return -1

print(bfs())