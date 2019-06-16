package array;

import java.util.Arrays;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 注意进位问题!!!
 * Created by lll on 19/6/16.
 */
public class PlusOne {
    public static void main(String[] args) {
        int[] nums = {9,9,9};

        System.out.println(Arrays.toString(plusOne4(nums)));

    }

    /*
    法一:常规方法
    加一,然后判断进位
     */
    public static int[] plusOne1(int[] digits) {

        //加一
        digits[digits.length-1] += 1;

        //进位
        for (int i = digits.length-1; i > 0; i--){
            if (digits[i] == 10){
                digits[i] = 0;
                digits[i-1] ++;
            }
        }

        //首位进位
        if (digits[0] == 10){
            int[] res = new int[digits.length+1];
            res[0] = 1;
            res[1] = 0;
            for (int i = 1; i<digits.length; i++){
                res[i+1] = digits[i];
            }
            return  res;
        }

        return digits;
    }


    /*
    法二:
    通过数组和数字的转换
    注意数组首位是否为0;数字大小(不能用int,要用long)
    通不过测试用例,测试用例有超大的数!
     */
    public static int[] plusOne2(int[] digits) {

        long num = 0;
        for (int i = digits.length-1; i >= 0; i--){
            num += digits[i] * Math.pow(10, digits.length-i-1);
        }

        num++;
        System.out.println(num);

        int[] res = new int[digits.length+1];
        for (int i = 0; i<res.length; i++){
            res[i] = (int)(num/Math.pow(10, res.length-i-1));
            num -= res[i] * Math.pow(10, res.length-i-1);
        }

        //先new多一位的数组,再判断第一个是不是0截取
        if (res.length > 1 && res[0] == 0){
            return Arrays.copyOfRange(res, 1,res.length);
        }

        return res;
    }


    /*
    法三:
    别人的,也太精妙了!!!
     */
    public static int[] plusOne3(int[] digits) {
        for (int i = digits.length-1; i >= 0; i--){
            digits[i]++;
            digits[i] %= 10;
            if (digits[i] > 0){
                return digits;
            }
        }

        //这里!!非常精妙,如果前面没有return掉,说明都是0,只用首位加1!!!
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }


    /*
    法四:
    完全使用数学的进位规则
    跟法一思路类似,但更精妙
     */
    public static int[] plusOne4(int[] digits) {
        int up = 1;
        for (int i = digits.length-1; i >= 0; i--){
            int temp = digits[i];
            digits[i] = (digits[i] + up)%10;
            up = (temp + up)/10;
        }
        if (up == 1){
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            return res;
        } else
            return digits;
    }
}
