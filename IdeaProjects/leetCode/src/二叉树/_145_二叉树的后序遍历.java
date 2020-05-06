package 二叉树;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _145_二叉树的后序遍历 {

    // 非递归
    public List<Integer> postorderTraversal(TreeNode root) {

        ArrayList <Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) return list;

        boolean isRight = false;
        stack.push(root);
        TreeNode pre = null;
        while (!stack.isEmpty()){
            TreeNode node = stack.peek();
            if ((node.left == null && node.right == null)|| (pre != null && (pre == node.left || pre == node.right))) {
                // 叶子节点 或者 上一个访问的节点是此节点的子节点时 出栈
                list.add(node.val);
                pre = node;
                stack.pop();
            }else{
                // 右子节点不为空
                if (node.right != null) stack.push(node.right);
                // 左子节点不为空
                if (node.left != null) stack.push(node.left);
            }
        }

        return list;
    }

    // 递归
    ArrayList <Integer> list = new ArrayList<>();
    public List<Integer> postorderTraversalRecuisive(TreeNode root) {

        if (root == null) return list;
        postorderTraversalRecuisive(root.left);
        postorderTraversalRecuisive(root.right);

        list.add(root.val);

        return list;
    }
}


//            if (node.left != null){
//                    if (node.left.left == null && node.left.right == null){
//                    // left  叶子节点
//                    list.add(node.left.val);
//                    node.left = null;
//                    }else{
//                    // left  非叶子节点
//                    node = node.left;
//                    stack.push(node);
//                    }
//
//                    }else if (node.right != null){
//                    if (node.right.left == null && node.right.right == null){
//                    // right 叶子节点
//                    list.add(node.right.val);
//                    node.right = null;
//                    }else{
//                    // right 非叶子节点
//                    node = node.right;
//                    stack.push(node);
//                    }
//                    }else{
//                    // 根节点
//                    list.add(node.val);
//                    node = null;
//                    }