package Interview;

/*
你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。

        返回的长度需要从小到大排列。

        示例：

        输入：
        shorter = 1
        longer = 2
        k = 3
        输出： {3,4,5,6}
        提示：

        0 < shorter <= longer
        0 <= k <= 100000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/diving-board-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class _面试题_16_11_跳水板 {

    /**

     看到这道题，最先想到的是 等差数列
     从 shorter * k开始 到 longer * k结束，每次 递增 (longer - shorter) 的 等差数列
     把 每一位都添加到数组中， 再返回数组即可

     特殊情况:
     1, k == 0, return 空数组
     2, shoter == longer 时，只会有一种可能。 shoter * k

     */
    public int[] divingBoard1(int shorter, int longer, int k) {
        if (k == 0) return new int[0];
        if (shorter == longer) {
            int[] rtn = new int[1];
            rtn[0] = shorter * k;
            return rtn;
        }

        int begain = shorter * k;
        int diff = longer - shorter;

        int [] res = new int[k + 1];
        int i = 0;
        while (i <= k){
            res[i] = begain + diff * i ++;
        }

        return res;
    }
}
