import heapq

def dijkstra(graph, start, final):
    costs = {}
    priority_q = []
    heapq.heappush(priority_q, (0, start))

    while priority_q:
        cur_cost, cur_node = heapq.heappop(priority_q)
        if cur_node not in costs:
            costs[cur_node] = cur_cost
            for cost, node in graph[cur_node]:
                heapq.heappush(priority_q, (cost + cur_cost, node))

    return costs[final]

graph = {
    1: [(2, 2), (1, 4)],
    2: [(1, 3), (9, 5), (6, 6)],
    3: [(4, 6)],
    4: [(3, 3), (5, 7)],
    5: [(1, 8)],
    6: [(3, 5)],
    7: [(7, 6), (9, 8)],
    8: []
}

print(dijkstra(graph, 1, 8))






























