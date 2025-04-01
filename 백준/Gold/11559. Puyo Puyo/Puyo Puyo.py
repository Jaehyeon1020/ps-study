import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()

game = [list(input()) for _ in range(12)]

def is_valid(x, y):
    return 0 <= x < 12 and 0 <= y < 6

def explode(x, y):
    result = False

    if game[x][y] == '.':
        return result

    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]

    q = deque()
    visited = [[False] * 6 for _ in range(12)]
    
    q.append((x, y))
    visited[x][y] = True

    same_color = 1 # 같은 색 몇개 모였는지 카운트
    explode_area = [(x, y)] # 터질 영역

    while q:
        cx, cy = q.popleft()
        
        for i in range(4):
            nx, ny = cx + dx[i], cy + dy[i]
            if is_valid(nx, ny) and not visited[nx][ny] and game[nx][ny] == game[x][y]:
                q.append((nx, ny))
                visited[nx][ny] = True
                same_color += 1
                explode_area.append((nx, ny))
    
    if same_color >= 4:
        result = True
        for area in explode_area:
            ex, ey = area
            game[ex][ey] = '.'
    
    return result

def clean():
    for j in range(6):
        for i in range(11, -1, -1):
                if game[i][j] != '.':
                    under = i
                    while under + 1 < 12 and game[under + 1][j] == '.':
                        under += 1
                    
                    if under != i:
                        game[under][j] = game[i][j]
                        game[i][j] = '.'

links = 0
while True:
    exists_explosion = False

    # 아랫줄부터 확인
    for i in range(11, -1, -1):
        for j in range(6):
            if(explode(i, j)):
                exists_explosion = True
    clean()

    # for line in game:
    #     print(line)
    # print()

    if exists_explosion:
        links += 1
    else:
        break

print(links)