// binary tree diameter
public int binaryTreeDiameterHelper(BinaryTree root) {
    if(root==null) return 0;

    int leftd = binaryTreeDiameterHelper(root.left); 
    int rightd = binaryTreeDiameterHelper(root.right);
    
    int lefth = height(root.left);
    int righth = height(root.right);
    
    return Math.max(Math.max(ld,rd),lh+rh);
}

/**
 * BST kth largest value
 */
int n;
int value;
public int findKthLargestValueInBst(BST tree, int k) {
    n = k;
    value = -1;
    traverse(tree);
    return value;
}
public void traverse(BST tree) {
    if(tree != null){
        traverse(tree.right);
        n--;
        if(n == 0)
            value = tree.value;
        traverse(tree.left);
    }
}

// MinHeight BST
public static BST minHeightBst(List<Integer> array) {
    if(array.size() == 0)
        return null;
	if(array.size() == 1)
		return new BST(array.get(0));
		
	int middle;
	if(array.size() % 2 == 1)
		middle = array.size() / 2;
	else
		middle = array.size() / 2 - 1;
		
	BST root = new BST(array.get(middle));
	root.left = minHeightBst(array.subList(0, middle));
	root.right = minHeightBst(array.subList(middle+1, array.size()));
	return root;
}
//is binary tree symetric
public boolean isSymmetric(TreeNode root) {
    return isMirror(root, root);
}
public boolean isMirror(TreeNode t1, TreeNode t2) {
    if (t1 == null && t2 == null) return true;
    if (t1 == null || t2 == null) return false;
    return (t1.val == t2.val)
        && isMirror(t1.right, t2.left)
        && isMirror(t1.left, t2.right);
}