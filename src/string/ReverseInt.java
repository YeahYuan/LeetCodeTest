package string;


/**整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，
 * 则其数值范围为 [−231,  231 − 1]。
 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
 * Created by lll on 19/8/24.
 */
public class ReverseInt {

    //超出时间限制
    public int reverse(int x) {
        char[] s = Integer.toString(x).toCharArray();
        int start = 0, end =s.length-1;
        while (start < end){
            s[start] = (char)(s[start] ^ s[end]);
            s[end] = (char)(s[start] ^ s[end]);
            s[start] = (char)(s[start] ^ s[end]);
        }
        return Integer.parseInt(s.toString());
    }

    //要用数学方法
    public int reverse1(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;//取最后一位数
            x /= 10;
            if (res>Integer.MAX_VALUE/10 || (res==Integer.MAX_VALUE/10 && pop>Integer.MAX_VALUE%10)) return 0;
            if (res<Integer.MIN_VALUE/10 || (res==Integer.MIN_VALUE/10 && pop<Integer.MIN_VALUE%10)) return 0;
            res = res*10 +pop;
        }
        return res;
    }


}
