import sys
import heapq

input = lambda: sys.stdin.readline().rstrip()

N = int(input())
words = []
for _ in range(N):
    words.append(input())

trans = {} # 각 알파벳이 어느 숫자에 대응되는지 저장
weights = {} # 각 알파벳의 가중치 저장 (높은 자리수에서, 자주 나올수록 높은 가중치)

def process_words():
    # weights 만들기
    for word in words:
        l = len(word)

        for alpha in word:
            if alpha not in weights:
                weights[alpha] = 0
            weights[alpha] += 10 ** (l - 1)
            l -= 1
    
    # 가중치에 따른 알파벳 - 숫자 매핑
    pq = []
    for alpha in weights:
        w = weights[alpha]
        heapq.heappush(pq, (-w, alpha))
    
    num = 9
    while pq:
        _, alpha = heapq.heappop(pq)
        trans[alpha] = num
        num -= 1


def get_sum():
    s = 0

    for word in words:
        numstr = ''

        for alpha in word:
            numstr += str(trans[alpha])
        s += int(numstr)
        
    return s

process_words()
print(get_sum())