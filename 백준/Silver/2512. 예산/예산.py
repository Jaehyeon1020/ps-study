import sys

input = lambda: sys.stdin.readline().rstrip()

N = int(input())
money = list(map(int, input().split()))
M = int(input())

def can_split(limit):
    used = 0
    for m in money:
        if m <= limit:
            used += m
        else:
            used += limit
    
    if used <= M:
        return True
    return False

# 상한액 필요없는 경우
if sum(money) <= M:
    print(max(money))
    exit()

# 상한액 정하기
left = 1
right = M + 1

while left < right:
    mid = (left + right) // 2

    if not can_split(mid):
        right = mid
    else:
        left = mid + 1

print(right - 1)