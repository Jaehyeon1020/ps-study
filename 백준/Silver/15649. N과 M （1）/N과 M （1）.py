n, m = map(int, input().split())
permutations = []

case = []
visited = [False] * (n + 1)
def solution():
    if len(case) == m:
        permutations.append(case.copy())
    
    for i in range(1, len(visited)):
        if visited[i]:
            continue
        
        visited[i] = True
        case.append(i)
        solution()
        case.pop()
        visited[i] = False

solution()

for case in permutations:
    for num in case:
        print(num, end=' ')
    print()