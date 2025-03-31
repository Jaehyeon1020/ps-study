import sys
input = lambda: sys.stdin.readline().rstrip()

N = int(input())
board = [['' for _ in range(N)] for _ in range(N)]

for i in range(N):
    line = input()
    for j in range(N):
        board[i][j] = line[j]

def check(i, j):
    max_len = 1

    count = 1
    for k in range(1, N):
        if board[i][k] == board[i][k-1]:
            count += 1
            max_len = max(max_len, count)
        else:
            count = 1

    count = 1
    for k in range(1, N):
        if board[k][j] == board[k-1][j]:
            count += 1
            max_len = max(max_len, count)
        else:
            count = 1

    return max_len

def get_max_candy():
    max_candy = 0

    for i in range(N):
        for j in range(N): 
            # 오른쪽 사탕과 바꾸기
            if j + 1 < N:
                board[i][j], board[i][j+1] = board[i][j+1], board[i][j]
                max_candy = max(max_candy, check(i, j), check(i, j+1))
                board[i][j], board[i][j+1] = board[i][j+1], board[i][j]

            # 아래 사탕과 바꾸기
            if i + 1 < N:
                board[i][j], board[i+1][j] = board[i+1][j], board[i][j]
                max_candy = max(max_candy, check(i, j), check(i+1, j))
                board[i][j], board[i+1][j] = board[i+1][j], board[i][j]

    return max_candy

print(get_max_candy())
