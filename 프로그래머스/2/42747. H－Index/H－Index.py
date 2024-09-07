def solution(citations):
    citations.sort(reverse = True)
    citation_max = citations[0]
    
    while citation_max >= 0:
        count = 0
        for citation in citations:
            if citation >= citation_max:
                count += 1
            if citation < citation_max:
                break
        
        if count >= citation_max:
            return citation_max
        
        citation_max -= 1