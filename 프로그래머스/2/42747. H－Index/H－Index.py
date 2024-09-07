def solution(citations):
    citation_max = max(citations)
    
    while citation_max >= 0:
        count = 0
        for citation in citations:
            if citation >= citation_max:
                count += 1
        
        if count >= citation_max:
            return citation_max
        
        citation_max -= 1