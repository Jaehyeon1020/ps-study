import sys

input = lambda: sys.stdin.readline().rstrip()

N = int(input())
eggs = [list(map(int, input().split())) for _ in range(N)]

max_broken = -1
def calculate_broken(cur):
    global max_broken

    broken_cnt = 0
    for egg in eggs:
        if egg[0] <= 0:
            broken_cnt += 1
    
    # 현재 계란 빼고 다 깨짐 or 모든 계란 다 확인함
    if cur == N or (broken_cnt == N - 1 and eggs[cur][0] > 0):
        max_broken = max(max_broken, broken_cnt)
        return
    
    # 지금 든 계란이 깨진 경우
    if eggs[cur][0] <= 0:
        calculate_broken(cur + 1)
    else:
        for i in range(N):
            if i == cur: continue

            # 치려는 계란이 깨지지 않은 경우에만 치기
            if eggs[i][0] > 0:
                eggs[cur][0] -= eggs[i][1]
                eggs[i][0] -= eggs[cur][1]
                calculate_broken(cur + 1)
                eggs[cur][0] += eggs[i][1]
                eggs[i][0] += eggs[cur][1]

calculate_broken(0)
print(max_broken)