import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()
print = sys.stdout.write

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def is_valid(x, y, w, h, building):
    return (0 <= x < h and 0 <= y < w) and building[x][y] != '*' and building[x][y] != '#'

def go_fire(fires, building, w, h):
    new_fires = deque()
    while fires:
        x, y = fires.popleft()

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if is_valid(nx, ny, w, h, building):
                building[nx][ny] = '*'
                new_fires.append((nx, ny))
    
    while new_fires:
        fires.append(new_fires.popleft())

def get_time(w, h, building):
    fires = deque()
    start_x = 0
    start_y = 0
    for i in range(h):
        for j in range(w):
            if building[i][j] == '*':
                fires.append((i, j))
            elif building[i][j] == '@':
                start_x = i
                start_y = j

    q = deque()
    visited = [[False] * w for _ in range(h)]

    q.append((start_x, start_y, 0))
    visited[start_x][start_y] = True

    prev_dist = -1
    while q:        
        x, y, dist = q.popleft()

        if dist != prev_dist:
            prev_dist = dist
            go_fire(fires, building, w, h)

        if x == 0 or x == h - 1 or y == 0 or y == w - 1:
            return dist + 1

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if is_valid(nx, ny, w, h, building) and not visited[nx][ny]:
                q.append((nx, ny, dist + 1))
                visited[nx][ny] = True

    return -1


TC = int(input())
for _ in range(TC):
    w, h = map(int,input().split())
    building = [list(input()) for _ in range(h)]
    result = get_time(w, h, building)
    if result == -1:
        print("IMPOSSIBLE\n")
    else:
        print(str(result) + "\n")