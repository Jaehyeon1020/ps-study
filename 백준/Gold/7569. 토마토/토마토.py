import sys

input = lambda: sys.stdin.readline().rstrip()

M, N, H = map(int, input().split())
boxes = [[[0] * M for _ in range(N)] for _ in range(H)]

for i in range(H):
    for j in range(N):
        boxes[i][j] = list(map(int, input().split()))

done = [] # h, x, y
for h in range(H):
    for x in range(N):
        for y in range(M):
            if boxes[h][x][y] == 1:
                done.append((h, x, y))

def valid(h, x, y):
    return 0 <= h < H and 0 <= x < N and 0 <= y < M

def oneday():
    global done

    dx = [-1, 1, 0, 0]
    dy = [0, 0, 1, -1]
    dh = [1, -1]

    new_done = []

    for h, x, y in done:
        # 동서남북
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if valid(h, nx, ny) and boxes[h][nx][ny] == 0:
                boxes[h][nx][ny] = 1
                new_done.append((h, nx, ny))
        # 상하
        for i in range(2):
            nh = h + dh[i]
            if valid(nh, x, y) and boxes[nh][x][y] == 0:
                boxes[nh][x][y] = 1
                new_done.append((nh, x, y))
    
    done = new_done


def get_days():
    days = -1
    while done:
        oneday()
        days += 1
    return days

days = get_days()

tomato_left = False
for h in range(H):
    for x in range(N):
        for y in range(M):
            if boxes[h][x][y] == 0:
                tomato_left = True
                break

print(days if not tomato_left else -1)