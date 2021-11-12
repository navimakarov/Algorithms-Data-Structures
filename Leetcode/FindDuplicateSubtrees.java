public class FindDuplicateSubtrees {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        HashMap<String,Integer> map = new HashMap<>();
        List<TreeNode> res = new ArrayList<>();
        count(root,map,res);
        return res;
    }
    private String count(TreeNode root, HashMap<String,Integer> map, List<TreeNode> res){
        if(root == null) return "X";
        String key = root.val+"l"+count(root.left,map,res)+"r"+count(root.right,map,res);
        map.put(key,map.getOrDefault(key,0)+1);
        if(map.get(key) == 2) res.add(root);
        return key;
    }
}