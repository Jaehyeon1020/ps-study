import heapq

def solution(scoville, K):
    answer = 0
    
    heapq.heapify(scoville)
    
    while len(scoville) >= 2:
        first = heapq.heappop(scoville)
        if first >= K:
            return answer
        
        second = heapq.heappop(scoville)
        new_scoville = first + (second * 2)
        heapq.heappush(scoville, new_scoville)
        answer += 1
        
    return answer if scoville[0] >= K else -1