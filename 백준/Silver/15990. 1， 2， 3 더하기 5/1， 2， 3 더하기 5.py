import sys

input = lambda: sys.stdin.readline().rstrip()
print = sys.stdout.write

MOD = 1_000_000_009
T = int(input())

start1 = [0 for _ in range(100_001)] # 1로 시작하는 경우의 수
start2 = [0 for _ in range(100_001)] # 2로 시작하는 경우의 수
start3 = [0 for _ in range(100_001)] # 3으로 시작하는 경우의 수

def getCases(n):
    return (start1[n] + start2[n] + start3[n]) % MOD

def calculate():
    # n = 1
    start1[1] = 1

    # n = 2
    start2[2] = 1

    # n = 3
    start3[3] = 1
    start2[3] = 1
    start1[3] = 1

    for n in range(4, 100_001):
        # 3으로 시작하는 경우 계산
        # n - 3의 경우의 수에서 3으로 시작하는 경우의 수만 제외
        start3[n] = (start1[n - 3] + start2[n - 3]) % MOD

        # 2로 시작하는 경우
        start2[n] = (start1[n - 2] + start3[n - 2]) % MOD

        # 1로 시작하는 경우
        start1[n] = (start2[n - 1] + start3[n - 1]) % MOD


calculate()
for _ in range(T):
    tc = int(input())
    print(str(getCases(tc)) + '\n')