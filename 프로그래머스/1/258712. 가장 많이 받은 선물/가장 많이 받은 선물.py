def solution(friends, gifts):    
    give = {} # A가 B에게 줌
    get = {} # A가 B에게 받음
    gift = {}
    
    for f in friends:
        give[f] = []
        get[f] = []
        gift[f] = 0
    
    for g in gifts:
        giver, getter = g.split()
        give[giver].append(getter)
        get[getter].append(giver)
        
    checked = []
        
    for a in friends:
        for b in friends:
            if a == b:
                continue
            elif [a, b] in checked or [b, a] in checked:
                continue
            
            checked.append([a, b])
            
            a_to_b = give[a].count(b)
            b_to_a = give[b].count(a)
            
            if a_to_b > b_to_a:
                gift[a] += 1
            elif a_to_b < b_to_a:
                gift[b] += 1
            else:
                a_gift_score = len(give[a]) - len(get[a])
                b_gift_score = len(give[b]) - len(get[b])
                if a_gift_score > b_gift_score:
                    gift[a] += 1
                elif a_gift_score < b_gift_score:
                    gift[b] += 1
    
    score = []
    for f in friends:
        score.append([f, gift[f]])
    
    return max([x[1] for x in score])