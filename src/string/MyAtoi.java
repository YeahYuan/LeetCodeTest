package string;

/**
 * 字符串转换整数 (atoi)
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，qing返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * Created by lll on 19/6/16.
 */
public class MyAtoi {

    public static void main(String[] args) {
        String str = "+--";

        String a = "123";
        String b = "123";
        System.out.println(a==b);

        System.out.println("["+myAtoi1(str)+"]");
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
            for (; i<str.length(); i++) {
                if (!s2.contains(str.toCharArray()[i] + "")) {
                    break;
                }
            }
            str = str.substring(0, i);
            System.out.println(str);
            if(str.equals("+") || str.equals("-")){
                return 0;
            }

            try {
                int res = Integer.parseInt(str);
                return res;
            } catch (NumberFormatException e){
                if (str.startsWith("-")){
                    return -2 << 30;
                } else
                    return (2<<30)-1;
            }
        }
        return 0;
    }


    public static int myAtoi2(String str) {

        int i = 0;
        for ( ; i<str.length(); i++){

        }

        return 0;
    }
}
