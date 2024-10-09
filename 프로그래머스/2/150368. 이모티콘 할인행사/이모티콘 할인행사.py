from itertools import product

def solution(users, emoticons):
    answer = []
    cases = list(product([10, 20, 30, 40], repeat=len(emoticons)))
    
    # 할인율 경우의 수 별로 따지기
    for case in cases:
        result = [0, 0]
        
        # 유저 별 구매 or 구독 여부 결정
        for dc, max_price in users:
            emoticon_sum = 0
            
            for i in range(len(emoticons)):
                if case[i] >= dc:
                    emoticon_sum += emoticons[i] * (100 - case[i]) / 100
            
            # 구독
            if emoticon_sum >= max_price:
                    result[0] += 1
            # 구매
            else:
                 result[1] += emoticon_sum
        
        answer.append(result)
    
    answer.sort(key=lambda x: (x[0], x[1]), reverse=True)
    return answer[0]