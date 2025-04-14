import sys

input = lambda: sys.stdin.readline().rstrip()

N, M = map(int, input().split())
city = [list(map(int, input().split())) for _ in range(N)]
chickens = []
houses = []

min_chicken_dist = float('inf')

for i in range(N):
    for j in range(N):
        if city[i][j] == 2:
            chickens.append((i, j))
        elif city[i][j] == 1:
            houses.append((i, j))

def calc_dist(x1, y1, x2, y2):
    return abs(x1 - x2) + abs(y1 - y2)

def get_chicken_dist(indexes):
    selected = [chickens[i] for i in indexes]
    total_dist = 0

    # 각 집마다 가장 가까운 치킨집 거리 확인
    for x, y in houses:
        closest = float('inf')
        
        for x2, y2 in selected:
            closest = min(closest, calc_dist(x, y, x2, y2))

        total_dist += closest
    
    return total_dist

def calc_min_chicken_dist(indexes, prev_i):
    global min_chicken_dist

    if len(indexes) == M:
        min_chicken_dist = min(get_chicken_dist(indexes), min_chicken_dist)
        return
    
    for i in range(prev_i + 1, len(chickens)):
        indexes.append(i)
        calc_min_chicken_dist(indexes, i)
        indexes.pop()

# print(chickens)
calc_min_chicken_dist([], -1)
print(min_chicken_dist)