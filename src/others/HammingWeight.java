package others;

import org.junit.Test;

/**位1的个数
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 * 提示：
     请注意，在某些语言（如 Java）中，没有无符号整数类型。
    在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，
    因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
     在 Java 中，编译器使用二进制补码记法来表示有符号整数。
    因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 进阶:
    如果多次调用这个函数，你将如何优化你的算法？
 * Created by lll on 19/8/14.
 */
public class HammingWeight {
    // you need to treat n as an unsigned value

    @Test
    public void test(){
        System.out.println(hammingWeight1(00000000000000000000000000001011));
    }

    /*
    常规方法,还有两种位运算的下次写
     */
    public int hammingWeight1(int n) {
        char[] cs = Integer.toBinaryString(n).toCharArray();
        int res = 0;
        for (int i=0; i<cs.length; i++){
            if (cs[i] == '1'){
                res++;
            }
        }
        return res;
    }

    /*循环和位移动
    位运算方法1
    依次与掩码进行与运算
    掩码是1不断左移
     */
    public int hammingWeight2(int n) {
        int res = 0;
        int mask = 1;//掩码
        for (int i =0; i<32; i++){
            if ((n & mask) != 0){//掩码和n进行&运算,==0说明当前位是0,!=0说明当前位是1
                res++;
            }
            mask <<= 1;
        }
        return res;
    }
    /*位操作的小技巧
    位运算方法2
    n&(n-1)使最末尾的1变成0,直到整个数都为0
     */
    public int hammingWeight3(int n) {
        int res = 0;
        while (n != 0){
            n &= (n-1);
            res++;
        }
        return res;
    }
}
