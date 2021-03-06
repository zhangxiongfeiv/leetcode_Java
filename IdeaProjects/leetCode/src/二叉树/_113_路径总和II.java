package 二叉树;

/*
给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

        说明: 叶子节点是指没有子节点的节点。

        示例:
        给定如下二叉树，以及目标和 sum = 22，

            5
           / \
          4   8
         /   / \
        11  13  4
       /  \    /  \
      7    2  5    1
        返回:

        [
        [5,4,11,2],
        [5,8,4,5]
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/path-sum-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


import java.util.*;

public class _113_路径总和II {

    // 结果数组
    LinkedList<List<Integer>> res = new LinkedList<>();

    // 路径
    LinkedList<Integer> path = new LinkedList<>();

    public void dfs(TreeNode node, int sum){
        if (node == null) return;

        path.add(node.val);
        sum -= node.val;

        if (sum == 0 && node.left == null && node.right == null)
            res.add(new LinkedList<>(path));

        dfs(node.left, sum);
        dfs(node.right, sum);

        // 回溯
        path.removeLast();
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        dfs(root, sum);
        return res;
    }

    /**
     * 回溯 标准模板
     * */
    public List<List<Integer>> pathSum1(TreeNode root, int sum){
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<Integer>();

        dfs1(res, path, root, sum);

        return res;
    }

    private void dfs1(List<List<Integer>> res, Deque<Integer> path, TreeNode root, int sum){
        if (root == null) return;
        if (root.left == null && root.right == null && sum == root.val){
            List<Integer> list = new ArrayList<>(path);
            list.add(root.val);
            res.add(list);
            return;
        }

        path.add(root.val);
        dfs1(res, path, root.left, sum - root.val);
        dfs1(res, path, root.right, sum - root.val);
        path.removeLast();
    }
}
