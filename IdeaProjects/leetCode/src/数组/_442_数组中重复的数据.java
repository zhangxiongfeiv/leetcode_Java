package 数组;

import java.util.ArrayList;
import java.util.List;

/**
给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
        找到所有出现两次的元素。
        你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？

        示例：
        输入:
        [4,3,2,7,8,2,3,1]

        输出:
        [2,3]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/find-all-duplicates-in-an-array
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _442_数组中重复的数据 {

    /**
     * 兑换元素
     *
     * 将 i位置的num 换到 num+1 的位置
     *
     * 交换过程中，如果 num = nums[nums[i] - 1]，则说明重复了，加入结果数组
     * 并且把 i 位置置为 -1 用来标识已经记录过重复
     *
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(1)
     *
     * */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1){
                if (nums[i] == -1) break;
                if (nums[i] == nums[nums[i] - 1]){
                    res.add(nums[i]);
                    nums[i] = -1;
                    break;
                }
                swap(nums, i, nums[i] - 1);
            }
        }
        return res;
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {

        _442_数组中重复的数据 cls = new _442_数组中重复的数据();
        int[] nums = {7,3,2,4,8,2,3,1};
        List<Integer>list = cls.findDuplicates(nums);
        System.out.println(list);
    }
}
