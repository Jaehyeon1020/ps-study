import sys

input = lambda: sys.stdin.readline().rstrip()

R, C, T = list(map(int, input().split()))
room = [list(map(int, input().split())) for _ in range(R)]

ac_upper = -1 # 에어컨 윗부분 위치 (ac_upper, 0)
ac_lower = -1 # 에어컨 아랫부분 위치 (ac_lower, 0)

for i in range(R):
    if room[i][0] == -1:
        if ac_upper == -1: ac_upper = i
        else: ac_lower = i

def is_valid(x, y):
    return (0 <= x < R and 0 <= y < C) and room[x][y] != -1

def flush_dust():
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]

    next_dust = [[0] * C for _ in range(R)] # 확산될 먼지 양
    for i in range(R):
        for j in range(C):
            if room[i][j] <= 0: continue

            dust = room[i][j] // 5
            directions = 0
            for d in range(4):
                nx = i + dx[d]
                ny = j + dy[d]
                
                if is_valid(nx, ny):
                    next_dust[nx][ny] += dust
                    directions += 1

            room[i][j] -= dust * directions

    for i in range(R):
        for j in range(C):
            room[i][j] += next_dust[i][j]

def run_ac_upper():
    # 위에서 아래로
    for i in range(ac_upper - 2, -1, -1):
        room[i + 1][0] = room[i][0]

    # 오른쪽에서 왼쪽으로
    for i in range(1, C):
        room[0][i - 1] = room[0][i]

    # 아래에서 위로
    for i in range(1, ac_upper + 1):
        room[i - 1][C - 1] = room[i][C - 1]

    # 왼쪽에서 오른쪽으로
    for i in range(C - 2, 0, -1):
        room[ac_upper][i + 1] = room[ac_upper][i]
    room[ac_upper][1] = 0

def run_ac_lower():
    # 아래에서 위로
    for i in range(ac_lower + 2, R):
        room[i - 1][0] = room[i][0]

    # 오른쪽에서 왼쪽으로
    for i in range(1, C):
        room[R - 1][i - 1]= room[R - 1][i]

    # 위에서 아래로
    for i in range(R - 2, ac_lower - 1, -1):
        room[i + 1][C - 1] = room[i][C - 1]

    # 왼쪽에서 오른쪽으로
    for i in range(C - 2, 0, -1):
        room[ac_lower][i + 1] = room[ac_lower][i]
    room[ac_lower][1] = 0

def run_ac():
    run_ac_upper()
    run_ac_lower()

def get_dust_amount():
    total = 0
    for col in room:
        for dust in col:
            if dust == -1: continue
            total += dust
    return total


for t in range(T):
    flush_dust()
    run_ac()

dusts = get_dust_amount()
print(dusts)