import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()

def get_time(building, start, end, L, R, C):
    def valid(x, y):
        return 0 <= x < R and 0 <= y < C

    # print(start)
    # print(end)

    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]
    df = [1, -1]

    q = deque()
    visited = []
    for _ in range(L):
        visited.append([[False] * C for _ in range(R)])
    
    q.append((start[0], start[1], start[2], 0)) # f, x, y, dist
    visited[start[0]][start[1]][start[2]] = True

    while q:
        f, x, y, dist = q.popleft()

        # print(f, x, y, dist)

        if f == end[0] and x == end[1] and y == end[2]:
            return dist
        
        # 같은 층 내 이동
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if valid(nx, ny) and not visited[f][nx][ny] and building[f][nx][ny] != '#':
                q.append((f, nx, ny, dist + 1))
                visited[f][nx][ny] = True
        
        # 위아래 이동
        for i in range(2):
            nf = f + df[i]
            if 0 <= nf < L and not visited[nf][x][y] and building[nf][x][y] != '#':
                q.append((nf, x, y, dist + 1))
                visited[nf][x][y] = True

    return -1


while True:
    L, R, C = map(int, input().split())

    if L == R == C == 0:
        break

    building = []
    start = (0, 0, 0)
    end = (0, 0, 0)

    for f in range(L):
        floor = []

        for i in range(R):
            line = list(input())
            for j in range(C):
                if line[j] == 'S':
                    start = (f, i, j)
                elif line[j] == 'E':
                    end = (f, i, j)
            floor.append(line)

        building.append(floor)
        input()

    t = get_time(building, start, end, L, R, C)
    if t == -1:
        print("Trapped!")
    else:
        print(f"Escaped in {t} minute(s).")

