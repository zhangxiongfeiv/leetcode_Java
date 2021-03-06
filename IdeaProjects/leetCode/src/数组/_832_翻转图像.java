package 数组;

/**
给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。

        水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。

        反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。

         
        示例 1：
        输入：[[1,1,0],[1,0,1],[0,0,0]]
        输出：[[1,0,0],[0,1,0],[1,1,1]]
        解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
        然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]

        示例 2：
        输入：[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
        输出：[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
        解释：首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
        然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
         

        提示：

        1 <= A.length = A[0].length <= 20
        0 <= A[i][j] <= 1

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/flipping-an-image
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _832_翻转图像 {

    public int[][] flipAndInvertImage(int[][] A) {
        // 先反转每一行
        // 再反转图像
        int rows = A.length;
        int cols = A[0].length;
        for (int i = 0; i < rows; i++) {
            revert(A[i]);
            for (int j = 0; j < cols; j++) {
                A[i][j] ^= 1;
            }
        }
        return A;
    }

    public void revert(int[] A){
        int i = 0, j = A.length - 1;
        while (i < j){
            int tmp = A[i];
            A[i++] = A[j];
            A[j--] = tmp;
        }
    }

    public static void main(String[] args) {
        int[][] A = {{1,1,0},{1,0,1},{0,0,0}};
        _832_翻转图像 cls = new _832_翻转图像();
        int[][]res = cls.flipAndInvertImage(A);
        System.out.println(res);
    }
}
