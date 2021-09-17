// Smallest value in BST
public int findSmallestValue(Node current) {
    return current.left == null ? 
           current.val : 
           findSmallestValue(current.left);
}

/**
 * Check if tree is balanced
 */
boolean balanced = true;
public boolean isBalanced(Node root) {
    helper(root);
    return root == null || balanced;
} 
public int helper(Node node){
    if(node == null) return 0;
    int heightLeft = helper(node.left);
    int heightRight = helper(node.right);
    balanced &= Math.abs(heightLeft - heightRight) <= 1;
    return 1 + Math.max(heightLeft, heightRight);
}

/**
 * Check if BST is valid
 */
public boolean isValidBST(Node tree) {
    return valid(tree, null, null);
}
private boolean valid(Node tree, Integer low, Integer high) {
    if(tree == null)
        return true;
    if((low != null && tree.val <= low) || 
       (high != null && tree.val >= high))
        return false;
    return valid(tree.left, low, root.val) && 
           valid(tree.right, root.val, high);
}

// Tree Height
public int height(BinaryTree tree) {
    return tree == null ? 
            0 : 
            Math.max(height(tree.left), height(tree.right)) + 1;
}

/**
 * Node depths(sum of all node depths), root depths is 0
 */
public int nodeDepths(BinaryTree root) {
    return dfs(root, 0);
}

public int dfs(BinaryTree node, int depth) {
    if(node == null)
        return 0;  
    return depth + dfs(node.left, depth+1) + 
           dfs(node.right, depth+1);
}

// height
public int height(BinaryTree node){
    return node == null ? 
           0 : 
           Math.max(height(node.left),height(node.right))+1;
}