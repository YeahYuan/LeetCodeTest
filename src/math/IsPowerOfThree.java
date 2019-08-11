package math;

/**3的幂
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 * Created by lll on 19/8/11.
 */
public class IsPowerOfThree {
    /*
    方法一:递归
     */
    public boolean isPowerOfThree1(int n) {
        if (n==0) return false;
        if (n==1) return true;
        if (n%3 == 0){
            return isPowerOfThree1(n/3);
        } else
            return false;
    }

    /*
    方法二:循环迭代
    不断整除3,最后是1就是3的幂。
     */
    public boolean isPowerOfThree2(int n) {
        if (n<1) return false;
        while (n%3 == 0){
            n /= 3;
        }
        return n==1;
    }

    /*
    方法三:基准转换(不使用循环或者递归)
    转换为3进制+正则验证
     */
    public boolean isPowerOfThree3(int n) {
        return Integer.toString(n, 3).matches("1{1}0*");//"^10*$"
    }

    /*
    方法四:整数限制(不使用循环或者递归)
    我们可以看出 n 的类型是 int,最大值为 2147483647。
    推断出int类型最大的 3 的幂，是 1162261467。
    将 3^{19}除以 n。若余数为 0 意味着 n 是 3^{19}的除数，因此是 3 的幂。
     */
    public boolean isPowerOfThree4(int n) {
        return n>=1 && 1162261467%n == 0;
    }
}
