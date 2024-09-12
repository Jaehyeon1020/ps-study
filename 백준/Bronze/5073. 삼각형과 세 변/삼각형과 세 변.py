import sys

input = sys.stdin.readline

while True:
    a, b, c = sorted(list(map(int, input().split())), reverse=True)

    if a == b == c == 0:
        break

    
    if not a < b + c:
        print("Invalid")
    elif a == b == c:
        print("Equilateral")
    elif a == b or b == c:
        print("Isosceles")
    else:
        print("Scalene")