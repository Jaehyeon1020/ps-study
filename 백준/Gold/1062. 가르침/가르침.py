import sys

input = lambda: sys.stdin.readline().rstrip()

N, K = map(int, input().split())
words = []
for _ in range(N):
    words.append(input())

# 종료 조건
if K == 26:
    print(N)
    exit()
elif K < 5:
    print(0)
    exit()

defaults = ['a', 'n', 't', 'i', 'c']
visited = [False] * 26
for l in defaults:
    visited[ord(l) - ord('a')] = True

max_readables = -1

def get_readable():
    count = 0

    for w in words:
        readable = True
        for l in w:
            if not visited[ord(l) - ord('a')]:
                readable = False
        
        if readable:
            count += 1
    
    return count

def calc_cases(start_index, count):
    global max_readables

    # 기본 글자를 포함해 K개가 선택되면 몇 개의 단어를 읽을 수 있는 지 계산
    if count == K - 5:
        max_readables = max(max_readables, get_readable())
        return
    
    # 이미 모든 단어를 읽을 수 있게 됐다면 중지
    if max_readables == N:
        return
    
    for i in range(start_index, 26):
        if visited[i]: continue

        visited[i] = True
        calc_cases(i + 1, count + 1)
        visited[i] = False

calc_cases(0, 0)
print(max_readables)