import sys

input = lambda: sys.stdin.readline().rstrip()

N = int(input())
consultings = []
for _ in range(N):
    consultings.append(list(map(int, input().split())))

dp = [0 for _ in range(N)]
end_price = {}

init_t, init_p = consultings[0]
if init_t == 1:
    dp[0] = init_p
else:
    end_price[init_t - 1] = [init_p]

for i in range(1, N):   
    t, p = consultings[i]

    # 끝나는 시점 가격 계산
    if t > 1:
        end = i + t - 1
        if end not in end_price:
            end_price[end] = []
        end_price[end].append(p + dp[i - 1])
    
    # dp[i] 계산
    comp = [dp[i - 1]] if t > 1 else [dp[i - 1] + p]
    if i in end_price:
        while end_price[i]:
            comp.append(end_price[i].pop())
    
    dp[i] = max(comp)

print(dp[N - 1])