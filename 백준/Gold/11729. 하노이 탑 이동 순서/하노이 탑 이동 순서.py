import sys

sys.setrecursionlimit(10**6)

input = lambda: sys.stdin.readline().rstrip()
print = sys.stdout.write

N = int(input())

route = []

def one_to_three(N):
    if N == 1:
        route.append("1 3\n")
        return

    one_to_two(N - 1)
    route.append("1 3\n")
    two_to_three(N - 1)

def one_to_two(N):
    if N == 1:
        route.append("1 2\n")
        return
    
    one_to_three(N - 1)
    route.append("1 2\n")
    three_to_two(N - 1)

def two_to_three(N):
    if N == 1:
        route.append("2 3\n")
        return
    
    two_to_one(N - 1)
    route.append("2 3\n")
    one_to_three(N - 1)

def two_to_one(N):
    if N == 1:
        route.append("2 1\n")
        return
    
    two_to_three(N - 1)
    route.append("2 1\n")
    three_to_one(N - 1)

def three_to_one(N):
    if N == 1:
        route.append("3 1\n")
        return
    
    three_to_two(N - 1)
    route.append("3 1\n")
    two_to_one(N - 1)

def three_to_two(N):
    if N == 1:
        route.append("3 2\n")
        return
    
    three_to_one(N - 1)
    route.append("3 2\n")
    one_to_two(N - 1)

one_to_three(N)
print(str(len(route)) + "\n")
for r in route:
    print(r)