package math;

import org.junit.Test;

/**
 * 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 * (质数是指在大于1的自然数中，除了1和它本身以外不再有其他因数的自然数。2357)
 *
 * 两个关键点
 * 1.厄拉多塞筛法(质数的倍数肯定不是质数)
 * 2.不需要考虑开方之后的数(对正整数 n ，如果用 2 到 √n 之间(包含边界)的所有整数去除，均无法整除，则 n 为质数。)
 * Created by lll on 19/8/11.
 */
public class CountPrimes {
    /*
    常规方法超出时间限制
    初步优化(x的开方之后的数1必有余数,偶数不用作为除数)
    后,还是超出时间限制
    考虑用空间换时间
     */
    public int countPrimes(int n) {
        int count = 0;
        //n从3开始,3以内count为0
        for (n -= 1; n > 1; n--) {
            if (isPrime(n)) {
                count++;
            }
        }
        return count;
    }

    private boolean isPrime(int x) {
        if (x <= 1) return false;
        if (x == 2) return true;
        if (x % 2 == 0) return false;
        for (int i = 3; i * i <= x; i += 2) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    /*
    厄拉多塞筛法
    介绍：改算法在寻找素数时，采用了一种与众不同的方法：
    先将 2－N 的各数放入表中，然后在 2 的上面画一个圆圈，
    然后划去 2 的其他倍数；第一个既未画圈又没有被划去的数是 3，
    将它画圈，再划去 3 的其他倍数；
    现在既未画圈又没有被划去的第一个数是 5，将它画圈，并划去5的其他倍数……
    依次类推，一直到所有小于或等于N的各数都画了圈或划去为止。
    这时，表中画了圈的以及未划去的那些数正好就是小于 N 的素数。
    普通方法会出现超时，所以只能以空间换时间，采用一个数组来进行标记是否是质数。
     */
    public int countPrimes1(int n) {
        int count = 0;
        //n为012时有0个质数
        if (n < 3) return count;
        //用数组记录是否是质数,默认为0--是质数
        int[] flag = new int[n];
        flag[0] = 1;
        flag[1] = 1;
        for (int i = 2; i*i<n; i++) {
            if (flag[i] == 0) {//i是质数,i的所有倍数都不是质数--1
                for (int j = i * i; j < n; j += i) {
                    flag[j] = 1;
                }
            }
        }
        for (int f: flag){
            count += (f == 0) ? 1:0;
        }
        return count;
    }

    @Test
    public void test() {
        System.out.println(isPrime(0));
        System.out.println(countPrimes1(499979));
    }
}
