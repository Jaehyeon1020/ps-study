import heapq

def solution(genres, plays):
    genre_count = {} # 장르별 총 플레이 수
    number_play = {} # 몇번 곡이 얼마나 플레이? - 번호:횟수 짝
    number_genre = {} # 몇번이 무슨 장르인지 - 번호:장르 짝
    
    genre_heap = []
    
    for i in range(len(genres)):
        cur_genre, cur_play = genres[i], plays[i]
        if cur_genre not in genre_count:
            genre_count[cur_genre] = cur_play
        else:
            genre_count[cur_genre] += cur_play
        number_play[i] = cur_play
        number_genre[i] = cur_genre
        
    for genre in genre_count:
        heapq.heappush(genre_heap, (-genre_count[genre], genre))
        
    answer = []
    while genre_heap:
        rank = []
        cur_genre = heapq.heappop(genre_heap)[1]
        for i in range(len(genres)):
            if number_genre[i] == cur_genre:
                rank.append((number_play[i], i))
        rank.sort(key=lambda x: (-x[0], x[1]))
        
        if len(rank) >= 2:
            answer.extend([rank[0][1], rank[1][1]])
        else:
            answer.append(rank[0][1])
                
    return answer