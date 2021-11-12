public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        if (subRoot ==null) {
            return true;
        }
        if (isSame(root, subRoot)) {
            return true;
        }
        return (isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot));
    }
    
    public boolean isSame(TreeNode root, TreeNode root2) {
        if (root == null && root2 == null) {
            return true;
        }
        if (root == null || root2 == null || root.val != root2.val) {
            return false;
        }
        return isSame(root.left, root2.left) && isSame(root.right, root2.right);
    }
}