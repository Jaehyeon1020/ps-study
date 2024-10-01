from collections import deque

def make_target(cards1, cards2, target):
    for card in cards1:
        if target - card in cards2:
            cards1.remove(card)
            cards2.remove(target - card)
            return True
    return False

def solution(coin, cards):
    answer = 1
    hand = set(cards[:len(cards) // 3])
    left_cards = deque(cards[len(cards) // 3:])
    passed = set()
    target = len(cards) + 1
    
    while left_cards:
        passed.add(left_cards.popleft())
        passed.add(left_cards.popleft())
        
        # 손에 있는 카드 두 장으로 target 만들기
        if make_target(hand, hand, target):
            answer += 1
            continue
        
        # 손에 있는 카드 1장 + 뽑았던 카드 중에 1장 (coin -= 1)
        elif coin >= 1 and make_target(hand, passed, target):
            coin -= 1
            answer += 1
            continue
        
        # 뽑았던 카드 중 2장 (coin -= 2)
        elif coin >= 2 and make_target(passed, passed, target):
            coin -= 2
            answer += 1
            continue
        
        # 위 모든 경우가 불가능하면 종료
        else:
            break
            
    return answer
    