package string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串转换整数 (atoi)
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，
 * 则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。
 * 如果数值超过这个范围，qing返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 *
 * 总结注意点:
 * 1\空判断
 * 2\"+-+"的格式判断
 * 3\仅正负号的判断
 * 4\数据溢出的判断
 * 即:第一个非空字符只能是数字或正负号,第二个非空字符只能是数字!
 * Created by lll on 19/6/16.
 */
public class MyAtoi {

    public static void main(String[] args) {
        String str = "  ";

        System.out.println("[" + myAtoi3(str) + "]");
    }

    /*
    法一:常规方法
    trim\空判断\首字符判断\结束判断\截取子字符串\判断仅正负号的情况
    以上排除了所有格式错误,然后parseInt还抛异常只能是溢出范围了

    但是好像不能用trim?parseInt?
     */
    public static int myAtoi1(String str) {
        str = str.trim();
        if (str.isEmpty()) {
            return 0;
        }
        String s1 = "0123456789+-";
        String s2 = "0123456789";
        if (s1.contains("" + str.charAt(0))) {
            int i = 1;
            //从第二个字符开始判断,注意不能是+-了,只能是数字
            for (; i < str.length(); i++) {
                if (!s2.contains(str.toCharArray()[i] + "")) {
                    break;
                }
            }
            str = str.substring(0, i);
            System.out.println(str);
            if (str.equals("+") || str.equals("-")) {
                return 0;
            }

            try {
                int res = Integer.parseInt(str);
                return res;
            } catch (NumberFormatException e) {
                if (str.startsWith("-")) {
                    return -2 << 30;
                } else
                    return (2 << 30) - 1;
            }
        }
        return 0;
    }


    /*
    法二:正则表达式解法
    和法一思路相似,只是用正则替代contains判断
    也用了trim和parseInt

    trim()函数去除收尾空格
    使用Java正则表达式匹配到合适的字符串
    未匹配到则返回0
    匹配到字符串后判断是否只是一个+或-，这种情况同样返回0（例如只输入+或-）
    以上判断都通过则使用Integer.parseInt()将字符串转成数字，并根据异常判定是否溢出
    溢出的返回值可以由字符串第一个字符是否是-来判定
    作者：godk-1
     */
    public static int myAtoi2(String str) {

        str = str.trim();

        String pattern = "^[\\+\\-\\d]\\d*";//正则表达式，表示以正号或负号或数字开头，且后面是0个或多个数字
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);

        String res;
        if (m.find()) {//能匹配到
            res = str.substring(m.start(), m.end());
        } else {//不能匹配到
            return 0;
        }

        //能匹配到但只有一个+-号，也返回0
        if (res.equals("+") || res.equals("-")) {
            return 0;
        }

        try {
            int r = Integer.parseInt(res);
            return r;
        } catch (Exception e) {
            return res.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

    }

    /*
    法三:
    可能是最符合题意的解法?(没有用trim和parseInt)
    保存数字串的开始及结束位置，然后根据题目要求判断溢出及非法情况。

    第一遍循环判断起始位置i,negative
    第二遍循环判断结束位置j
    第三遍循环转换字符串为int

    速度快的方法貌似都把str转成char[]
     */
    public static int myAtoi3(String str) {

        int i, j, len = str.length();
        boolean negative = false;//默认为正数
        String s1 = "+-";
        String s2 = "0123456789";

        for (i = 0; i< len; i++){
            String c = str.charAt(i) + "";
            if (s1.contains(c)){
                negative = c.equals("-");//负数为true
                i++;
                break;
            } else if (s2.contains(c)){
                break;
            } else if (!c.equals(" ")){
                return 0;
            }
        }

        for (j = i; j<len; j++){
            String c = str.charAt(j) + "";
            if (!s2.contains(c)){
                break;
            }
        }

        //结果是纯数字,正负存储在negative中
        str = str.substring(i,j);
        System.out.println(str);

        int res = 0;
        for (int x = 0; x < str.length(); x++){
            int num = str.charAt(x) - '0';

            //不需要判断边界1时的三元运算符写法
//            res = negative ? res*10-num : res*10 +num;
            if (negative){//负数
                //判断溢出(要记一下!!!)
                if(res < Integer.MIN_VALUE / 10|| (res == Integer.MIN_VALUE / 10 && num > 8)){
                    return Integer.MIN_VALUE;
                }
                res = res*10-num;
            } else {//正数
                //判断溢出
                if (res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE/10 && num > 7)){
                    return Integer.MAX_VALUE;
                }
                res =res*10 +num;
            }
        }
        return res;
    }

}
