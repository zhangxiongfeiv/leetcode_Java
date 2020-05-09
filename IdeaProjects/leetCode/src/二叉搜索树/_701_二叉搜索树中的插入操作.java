package 二叉搜索树;

/*
给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。

        注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。

        例如, 

        给定二叉搜索树:

        4
        / \
        2   7
        / \
        1   3

        和 插入的值: 5
        你可以返回这个二叉搜索树:

        4
        /   \
        2     7
        / \   /
        1   3 5
        或者这个树也是有效的:

        5
        /   \
        2     7
        / \
        1   3
        \
        4

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _701_二叉搜索树中的插入操作 {

    // 递归
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        }else {
            root.left = insertIntoBST(root.left,val);
        }
        return root;
    }

    // 迭代
    public TreeNode insertIntoBST1(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        TreeNode node = root;
        while (node != null){
            if (val > node.val){
                if (node.right == null){
                    node.right = new TreeNode(val);
                    return root;
                }else {
                    node = node.right;
                }
            }else {
                if (node.left == null){
                    node.left = new TreeNode(val);
                    return root;
                }else {
                    node = node.left;
                }
            }
        }
        return new TreeNode(val);
    }
}


