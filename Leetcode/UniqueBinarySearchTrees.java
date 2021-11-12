public class UniqueBinarySearchTrees {
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }
    
    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();
        if(start > end) {
            trees.add(null);
            return trees;
        }
        
        for(int i = start; i <= end; i++) {
            List<TreeNode> left = generateTrees(start, i-1);
            List<TreeNode> right = generateTrees(i+1, end);
            
            for(TreeNode l : left) {
                for(TreeNode r : right) {
                    TreeNode curr = new TreeNode(i, l, r);
                    trees.add(curr);
                }
            }
        }
        return trees;
    }
}

/*
    Number of Unique Binary Search Trees can be found with Catalan numbers
    (2n)! / ((n + 1)!n!)
*/