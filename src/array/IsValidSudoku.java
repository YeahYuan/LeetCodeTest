package array;

import java.util.Set;

/**
 * 有效的数独
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * 1.数字 1-9 在每一行只能出现一次。
 * 2.数字 1-9 在每一列只能出现一次。
 * 3.数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * 说明:
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的。
 * Created by lll on 19/8/31.
 */
public class IsValidSudoku {
    /*
    两轮循环 9*9+3*9*9
     */
    public boolean isValidSudoku(char[][] board) {
        int[][][] count = new int[3][9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.'){
                    int temp = board[i][j] -'0' -1;//char 转 int 并减一(1-9)>(0-8)
                    count[0][i][temp]++;//行计数
                    count[1][j][temp]++;//列计数
                    count[2][(i/3)*3+(j/3)][temp]++;//3*3计数
                }
            }
        }
        for (int[][] a : count){
            for (int[] b : a) {
                for (int c : b){
                    if (c>1) return false;
                }
            }
        }
        return true;
    }

    /*
    优化
    > 易读性
    > 一遍循环 9*9
     */
    public boolean isValidSudoku0(char[][] board) {
        int[][][] count = new int[3][9][9];
        int row = 0;//count[0]表示行计数
        int col = 1;//count[1]表示列计数
        int block = 2;//count[2]表示3*3计数
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.'){
                    int num = board[i][j] -'1';//char 转 int 并减一(1-9)>(0-8)
                    int block_index = (i/3)*3+(j/3);//block序号
                    if (++count[row][i][num]>1 //行计数
                            || ++count[col][j][num]>1 //列计数
                            || ++count[block][block_index][num]>1) //3*3计数
                        return false;
                }
            }
        }
        return true;
    }
}
