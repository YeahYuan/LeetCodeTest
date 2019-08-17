package others;

/**汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * Created by lll on 19/8/17.
 */
public class HammingDistance {
    /*
    思路:
    使用异或运算,相同false-0,不同true-1
    再用汉明重量求有多少个true-1
     */
    public int hammingDistance(int x, int y) {
        int n = x^y;
        int res = 0;
        while (n != 0){//说明见README位操作技巧
            n &= (n-1);
            res++;
        }
//        Integer.bitCount(x^y); //bitCount 数出整数二进制下 1 的个数
        return res;
    }


}
