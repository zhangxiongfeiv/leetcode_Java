package 动态规划;

/*
给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

        示例:

        输入: 3
        输出: 5
        解释:
        给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

        1         3     3      2      1
        \       /     /      / \      \
        3     2     1      1   3      2
        /     /       \                 \
        2     1         2                 3

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/unique-binary-search-trees
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _96_不同的二叉搜索树 {

    // 假设n个节点存在二叉搜索书的个数是 dp(n) . 令f(i)为以i为跟的二叉搜索树的个数
    // dp(n) = f(1) + f(2) + f(3) + ... + f(n);

    // 当i为根节点时,其左子树节点个数为 i-1。 右子树节点为 n - i。
    // f(i) = dp(i - 1) * dp(n - i);

    // 综合以上两个，可以得到 卡特兰数 公式
    // dp(n) = dp(0) * dp(n - 1) + dp(1) * dp(n-2) + ... + dp(n-1) * dp(0);

    public int numTrees(int n) {
        if (n == 0) return 1;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }

        return dp[n];
    }
}
