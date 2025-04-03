import sys

input = lambda: sys.stdin.readline().rstrip()

src = input()
comp = input()

def get_max_len(s1, s2):
    max_len = 0
    
    is_updating = False
    cur_len = 0
    for i in range(len(s1)):
        c1, c2 = s1[i], s2[i]
        if c1 == c2:
            if is_updating:
                cur_len += 1
            else:
                cur_len = 1
                is_updating = True
            
            max_len = max(max_len, cur_len)
        else:
            cur_len = 0
            is_updating = False
        
    return max_len

# 항상 src가 더 짧거나 같게
if len(src) > len(comp):
    src, comp = comp, src

max_len = 0
src_start = 0
comp_start = len(comp) - 1
while not (src_start == len(src) - 1 and comp_start == 0):
    # 겹치는 길이 구하기
    shadow = min(len(src) - src_start, len(comp) - comp_start)

    # 비교할 조각 구하기
    substr1 = src[src_start: src_start + shadow]
    substr2 = comp[comp_start: comp_start + shadow]

    # 두 문자열에서 최장 공통 문자열 길이 추출
    max_len = max(max_len, get_max_len(substr1, substr2))

    # 포인터 이동
    if comp_start > src_start:
        comp_start -= 1
    else:
        src_start += 1

print(max_len)