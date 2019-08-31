package array;


import org.junit.Test;

/**
 * 旋转图像
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * Created by lll on 19/8/31.
 */
public class Rotate {
    /*
    使用另一个矩阵来旋转图像(不符合要求)
    ****数组的clone():
    * 一维数组：深克隆；（重新分配空间，并将元素复制过去）
    * 二维数组：浅克隆。（只传递引用）
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] copy = matrix.clone();
        for (int i = 0; i < n; i++) {
            copy[i] = matrix[i].clone();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = copy[n-1-j][i];
            }
        }
    }

    /*
    方法一:
    转置+翻转
     */
    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        //xy轴转置
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //沿纵向中心轴翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = temp;
            }
        }
    }


    /*
    方法二:
    旋转四个矩形
    (想到了这个思路,但写不出代码)
    两个难点:
    1.循环的边界判断:i<n/2;i<=j<n-i-1;
    2.四个交换数字的索引:
    任意一个(i,j) , (j, n-i-1), (n-i-1, n-j-1), (n -j-1, i)就是这四个索引号上的数交换.
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n/2; i++) {
            for (int j = i; j < n-i-1 ; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n -j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = temp;
            }
        }
    }

    @Test
    public void test(){
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        rotate(matrix);
        for (int[] a:matrix){
            for (int b:a)
                System.out.print(b);
        }
    }
}
