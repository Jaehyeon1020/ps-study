import sys
input = lambda: sys.stdin.readline().rstrip()

N = int(input())
interviews = [list(map(int, input().split())) for _ in range(N)]

max_profit = 0
def calc_max_profit(start, cur_profit):
    global max_profit

    if start >= N:
        return
    
    for day in range(start, N):
        time, price = interviews[day]

        if day + time <= N:
            max_profit = max(max_profit, cur_profit + price)

        calc_max_profit(day + time, cur_profit + price)

calc_max_profit(0, 0)
print(max_profit)