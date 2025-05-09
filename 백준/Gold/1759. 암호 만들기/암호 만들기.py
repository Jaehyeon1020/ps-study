import sys

input = lambda: sys.stdin.readline().rstrip()

mo = ['a', 'e', 'i', 'o', 'u']

L, C = map(int, input().split())
letters = list(input().split())
letters.sort()
passwords = []

def valid(pw):
    mcount = 0
    ccount = 0

    for l in pw:
        if l in mo: mcount += 1
        else: ccount += 1
    
    if mcount >= 1 and ccount >= 2:
        return True
    return False

def dfs(cur_idx, pw):
    if len(pw) == L and valid(pw):
        passwords.append(pw)
        return
    
    # 입력해야 할 글자가 남은 글자 수보다 많은 경우 취소
    if L - len(pw) > len(letters) - (cur_idx + 1):
        return
    
    for i in range(cur_idx + 1, len(letters)):
        dfs(i, pw + letters[i])

dfs(-1, "")

for p in passwords:
    print(p)