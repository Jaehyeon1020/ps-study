import sys

input = lambda: sys.stdin.readline().rstrip()

N, M = map(int, input().split())
x, y, d = map(int, input().split())

room = [list(map(int, input().split())) for _ in range(N)]

clock = {
    0: 3,
    1: 0,
    2: 1,
    3: 2
}

def get_back_pos(x, y, d):
    if d == 0:
        return x + 1, y
    if d == 1:
        return x, y - 1
    if d == 2:
        return x - 1, y
    if d == 3:
        return x, y + 1
    
def get_forward_pos(x, y, d):
    if d == 0:
        return x - 1, y
    if d == 1:
        return x, y + 1
    if d == 2:
        return x + 1, y
    if d == 3:
        return x, y - 1

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

blocks = 0

while True:
    if room[x][y] == 0:
        room[x][y] = -1
        blocks += 1

    block_exists = False
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if room[nx][ny] == 0:
            block_exists = True
    
    if not block_exists:
        nx, ny = get_back_pos(x, y, d)
        if room[nx][ny] != 1: # 벽만 아니면 됨
            x, y = nx, ny
        else:
            break
    else:
        d = clock[d]
        nx, ny = get_forward_pos(x, y, d)
        if room[nx][ny] == 0:
            x, y = nx, ny

print(blocks)