package 贪心;

import java.util.Arrays;
import java.util.Comparator;

/**
给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
        注意:
        可以认为区间的终点总是大于它的起点。
        区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
        示例 1:

        输入: [ [1,2], [2,3], [3,4], [1,3] ]
        输出: 1

        解释: 移除 [1,3] 后，剩下的区间没有重叠。
        示例 2:

        输入: [ [1,2], [1,2], [1,2] ]
        输出: 2

        解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
        示例 3:

        输入: [ [1,2], [2,3] ]
        输出: 0

        解释: 你不需要移除任何区间，因为它们已经是无重叠的了。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/non-overlapping-intervals
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _435_无重叠区间 {

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1) return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int res = 0;
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (prev[1] <= cur[0]){
                prev = cur;
                continue;
            }else {
                res ++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        _435_无重叠区间 cls = new _435_无重叠区间();
        int[][]intervals = {{1,2}, {2,3}, {3,4}, {1,3}};
        cls.eraseOverlapIntervals(intervals);
    }
}
