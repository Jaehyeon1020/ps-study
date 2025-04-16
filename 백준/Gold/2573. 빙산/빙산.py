import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()

N, M = map(int, input().split())
ocean = [list(map(int, input().split())) for _ in range(N)]

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def valid(x, y):
    return 0 <= x < N and 0 <= y < M

def bfs(x, y, visited):
    q = deque()
    q.append((x, y))
    visited[x][y] = True

    while q:
        curx, cury = q.popleft()
        
        for i in range(4):
            nx, ny = curx + dx[i], cury + dy[i]

            if valid(nx, ny) and not visited[nx][ny] and ocean[nx][ny] != 0:
                q.append((nx, ny))
                visited[nx][ny] = True

def count_ice():
    visited = [[False] * M for _ in range(N)]

    count = 0
    for i in range(N):
        for j in range(M):
            if ocean[i][j] != 0 and not visited[i][j]:
                count += 1
                bfs(i, j, visited)
    
    return count

def get_melt_plan(x, y):
    amount = 0

    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        
        if valid(nx, ny) and ocean[nx][ny] == 0:
            amount += 1
    
    return (x, y, amount)

def melt():
    melt_plans = []

    for i in range(N):
        for j in range(M):
            if ocean[i][j] > 0:
                melt_plans.append(get_melt_plan(i, j))
    
    for x, y, amount in melt_plans:
        ocean[x][y] = max(ocean[x][y] - amount, 0)


year = 0
count = -1
while True:
    count = count_ice()
    if count != 1:
        break

    melt()
    year += 1

if count == 0:
    print(0)
    exit()

print(year)