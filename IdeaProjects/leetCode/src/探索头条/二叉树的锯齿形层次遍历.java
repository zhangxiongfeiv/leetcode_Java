package 探索头条;

import javax.management.QueryEval;
import java.util.*;

/*
给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

        例如：
        给定二叉树 [3,9,20,null,null,15,7],

        3
        / \
        9  20
        /  \
        15   7
        返回锯齿形层次遍历如下：

        [
        [3],
        [20,9],
        [15,7]
        ]
 */

public class 二叉树的锯齿形层次遍历 {

    /**
     *
     * 最浅显易懂的方法
     * 基本跟层序遍历一样
     * 记录一个变量
     * 在此 flag 为true 时，添加数组时，反转list
     *
     * */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new LinkedList<>();
        if (root == null) return lists;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        boolean flag = true;
        while (!queue.isEmpty()){

            List<Integer> list = new LinkedList<>();
            int size = queue.size();
            while (size-- > 0){
                TreeNode node = queue.poll();

                list.add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }


            if(flag == true) Collections.reverse(list);
            flag = !flag;
            lists.add(list);
        }

        return lists;
    }

}
