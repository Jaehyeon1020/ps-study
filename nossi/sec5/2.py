from collections import deque

# def bfs(node):
#     queue = deque()
#     queue.append(node)
#     visited = []

#     while queue:
#         cur = queue.popleft()
#         visited.append(cur.val)

#         if cur.left:
#             queue.append(cur.left)
#         if cur.right:
#             queue.append(cur.right)
#     return visited

class TreeNode:
    def __init__(self, left=None, right=None, val=0):
        self.left = left
        self.right = right
        self.val = val

root = TreeNode(val=3)
root.left = TreeNode(val=9)
root.right = TreeNode(val=10)
root.right.left = TreeNode(val=3)
root.right.right = TreeNode(val=2)
root.right.right.right = TreeNode(val=14)


def getMaxDepthLevelOrder(node):
    q = deque()
    q.append((node, 1))
    max_depth = 1

    while q:
        cur, cur_depth = q.popleft()
        max_depth = max(max_depth, cur_depth)

        if cur.left:
            q.append((cur.left, cur_depth + 1))
        if cur.right:
            q.append((cur.right, cur_depth + 1))
    
    return max_depth

def getMaxDepthDFS(node, cur_depth = 1):
    if node is None:
        return 0

    left_result = getMaxDepthDFS(node.left, cur_depth + 1)
    right_result = getMaxDepthDFS(node.right, cur_depth + 1)

    if not left_result and not right_result:
        return cur_depth
    else:
        return max(left_result, right_result)
    
print(getMaxDepthDFS(root))