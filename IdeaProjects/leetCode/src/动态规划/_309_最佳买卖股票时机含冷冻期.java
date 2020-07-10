package 动态规划;

/**
给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

        设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

        你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
        卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
        示例:

        输入: [1,2,3,0,2]
        输出: 3
        解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _309_最佳买卖股票时机含冷冻期 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int max = 0;
        int[] dp = new int[prices.length];

        for (int i = 1; i < prices.length; i++) {

        }
    }

}
