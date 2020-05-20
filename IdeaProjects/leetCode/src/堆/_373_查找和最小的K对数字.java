package 堆;

/*
给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。

        定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。

        找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。

        示例 1:

        输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
        输出: [1,2],[1,4],[1,6]
        解释: 返回序列中的前 3 对数：
        [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
        示例 2:

        输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
        输出: [1,1],[1,1]
        解释: 返回序列中的前 2 对数：
             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
        示例 3:

        输入: nums1 = [1,2], nums2 = [3], k = 3
        输出: [1,3],[2,3]
        解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]


        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.*;

public class _373_查找和最小的K对数字 {

    // 暴力解法
    // 看到 top k 最小，即想到 最大堆
    // 用最大堆保存前 K 堆最小的元素
    // 时间复杂度 : O(N ^ 2 * log K)

    // 优化
    // 剪枝
    // 当前两个数之和超过了堆顶元素，由于数据已经排序，后边的元素只会更大，因此无需继续遍历
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List> priorityQueue = new PriorityQueue<>(new Comparator<List>() {
            @Override
            public int compare(List o1, List o2) {
                return ((int)o2.get(0) + (int)o2.get(1) - ((int)o1.get(0) + (int)o1.get(1)));
            }
        });

        for (int i = 0; i < nums1.length; i++) {
            int num1 = nums1[i];
            for (int j = 0; j < nums2.length; j++) {

                // 剪枝，如果当前的两个数之和超过了堆顶元素，由于数组已经排序，后面的元素只会更大，因此无需继续遍历
                if(priorityQueue.size() == k && nums1[i]+nums2[j] > ((int)priorityQueue.peek().get(0) + (int)priorityQueue.peek().get(1))){
                    break;
                }

                List list = new ArrayList();
                list.add(num1);
                list.add(nums2[j]);

                priorityQueue.add(list);
                if (priorityQueue.size() > k){
                    priorityQueue.poll();
                }
            }
        }

        List<List<Integer>> lists = new LinkedList<>();
        while (!priorityQueue.isEmpty()){
            lists.add(0,priorityQueue.poll());
        }
        return lists;
    }

    public static void main(String[] args){

        int[] nums1 = {1,1,2};
        int[] nums2 = {1,2,3};
        int k = 2;

        _373_查找和最小的K对数字 test = new _373_查找和最小的K对数字();

        List list = test.kSmallestPairs(nums1,nums2,k);
        System.out.println(list);
    }
}
