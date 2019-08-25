package string;

import org.junit.Test;

/**
 * 报数
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 * 注意：整数顺序将表示为一个字符串。
 * Created by lll on 19/8/25.
 */
public class CountAndSay {

    /*
    一开始理解错了,以为只有1和2
     */
    public String countAndSay(int n) {
        String pre = null, cur = "1";
        for (int i = 2; i <= n; i++) {//大圈循环,根据pre生成cur
            pre = cur;
            char p = pre.charAt(0);
            int count = 1;
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j < pre.length(); j++) {//小圈循环根据p和c是否相同计数count,拼接cur
                char c = pre.charAt(j);
                if (c == p){//字符相同时,count累加
                    count++;
                } else {//字符不同时,重新计数
                    sb.append(count).append(p);
                    p = c;
                    count = 1;
                }
            }
            sb.append(count).append(p);
            cur = sb.toString();
        }
        return cur;
    }

    @Test
    public void test(){
        System.out.println(countAndSay(5));
        System.out.println(countAndSay(6));
    }
}
