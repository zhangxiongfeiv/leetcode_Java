package 堆;

/*
示例 1:

        输入: nums = [1,1,1,2,2,3], k = 2
        输出: [1,2]
        示例 2:

        输入: nums = [1], k = 1
        输出: [1]
        说明：

        你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
        你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。


        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/top-k-frequent-elements
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.*;
import java.util.Map.Entry;

public class _347_前K个高频元素 {

    // 优先级队列
    // 最小堆
    // 前K个高频元素 存在堆中
    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // 最小堆
        PriorityQueue<Entry<Integer,Integer>> queue = new PriorityQueue<>(new Comparator<Entry<Integer, Integer>>() {
            @Override
            public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });

        for (Entry<Integer, Integer> entry: map.entrySet()) {
            queue.add(entry);

            if (queue.size() > k){
                queue.poll();
            }
        }

        int[] list = new int[k];
        int i = 0;
        while (!queue.isEmpty()){
            list[i ++] = queue.poll().getKey();
        }
        return list;
    }

    // 全排序
    public List<Integer> topKFrequent1(int[] nums, int k) {

        Map<Integer,Integer> counts = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            counts.put(nums[i], counts.getOrDefault(nums[i],0) +1);
        }

        Map.Entry<Integer,Integer>[] entries = new Map.Entry[counts.size()];
        counts.entrySet().toArray(entries);

        Arrays.sort(entries,(Map.Entry<Integer ,Integer> e1, Map.Entry<Integer,Integer> e2) -> {
            return e2.getValue() - e1.getValue();
        });

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(entries[i].getKey());
        }

        return res;
    }

    public static void main(String[] args) {

        int [] nums = {1,1,1,2,2,3};

        _347_前K个高频元素 test = new _347_前K个高频元素();
        test.topKFrequent1(nums,2);
    }
}

