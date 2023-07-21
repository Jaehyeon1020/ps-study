def solution(players, callings):
    rank_dic = {}
    
    rank = 0
    for player in players:
        rank_dic[player] = rank
        rank += 1
    
    for name in callings:
        cur_rank = rank_dic[name] # 현재 이름의 랭크
        prev_name = players[cur_rank - 1] # 앞사람 이름
        
        players[cur_rank], players[cur_rank - 1] = players[cur_rank - 1], players[cur_rank]
        
        rank_dic[name] -= 1
        rank_dic[prev_name] += 1
        
    return players