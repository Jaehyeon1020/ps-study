import sys

input = lambda: sys.stdin.readline().rstrip()
print = sys.stdout.write

N, M, B = list(map(int, input().split()))
ground = []

lowest = 256
highest = 0
for _ in range(N):
    line = list(map(int, input().split()))
    lowest = min(min(line), lowest)
    highest = max(max(line), highest)
    ground.append(line)

def make(h, blocks):
    removed = 0
    putted = 0
    needed_blocks = 0

    for i in range(N):
        for j in range(M):
            if ground[i][j] > h:
                removed += ground[i][j] - h
                blocks += ground[i][j] - h
            elif ground[i][j] < h:
                putted += h - ground[i][j]
                needed_blocks += h - ground[i][j]
    
    if blocks < needed_blocks:
        return -1, -1
    return removed, putted


# 전체 높이를 h로 만들 때의 시간 계산
min_time = 500 * 500 * 256 * 2 + 1
height_at_mintime = -1
for h in range (lowest, highest + 1):
    inventory = B
    
    # 블럭 제거, 생성 횟수 계산
    removed, putted = make(h, inventory)

    if putted == -1:
        break

    time = removed * 2 + putted
    if time <= min_time:
        min_time = time
        height_at_mintime = h

print(str(min_time) + " " + str(height_at_mintime))