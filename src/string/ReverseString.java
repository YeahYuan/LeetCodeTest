package string;

import java.util.Arrays;

/**反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 * (很简单呀)
 * Created by lll on 19/6/16.
 */
public class ReverseString {

    public static void main(String[] args) {
        char[] s = {'h','e','l','l','o'};

        System.out.println(Arrays.toString(s));
        reverseString2(s);

        System.out.println(Arrays.toString(s));
    }


    /*
    法一:使用temp交换
     */
    public static void reverseString1(char[] s) {
        int x = 0, y = s.length-1;
        while (x < y){
            char temp = s[x];
            s[x++] = s[y];
            s[y--] = temp;
        }

    }

    /*
    法二:不适用额外变量
    用异或算法
     */
    public static void reverseString2(char[] s) {
        int x = 0, y = s.length-1;
        while (x < y){
            s[x] = (char) (s[x]^s[y]);
            s[y] = (char) (s[x]^s[y]);
            s[x] = (char) (s[x++]^s[y--]);
        }

    }

}
