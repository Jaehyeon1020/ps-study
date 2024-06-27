def getLowestCommonAncestor(node, p, q):
    if node is None:
        return None
    
    left = getLowestCommonAncestor(node.left, p, q)
    right = getLowestCommonAncestor(node.right, p, q)
    if node.val == p or node.val == q:
        return node.val
    elif not left and not right:
        return None
    elif left or right:
        return left or right
    else:
        return node.val