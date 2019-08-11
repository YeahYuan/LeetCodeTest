package math;

import java.util.ArrayList;
import java.util.List;

/**Fizz Buzz
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 1. 如果 n 是3的倍数，输出“Fizz”；
 2. 如果 n 是5的倍数，输出“Buzz”；
 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 * Created by lll on 19/8/11.
 */
public class FizzBuzz {

    /*
    非常简单的题
     */
    public List<String> fizzBuzz(int n) {
        List<String> s = new ArrayList<String>();
        for (int i=1; i<=n; i++){
            if(i%3==0 && i%5==0)
                s.add("FizzBuzz");
            else if(i%3==0)
                s.add("Fizz");
            else if(i%5==0)
                s.add("Buzz");
            else
                s.add(i+"");
        }
        return s;
    }

    /*
    如果规则变得更加复杂
    使用sb拼接字符串来处理既能被3整除又能被5整除的情况
     */
    public List<String> fizzBuzz1(int n) {
        List<String> s = new ArrayList<String>();
        for (int i=1; i<=n; i++){
            StringBuilder sb = new StringBuilder();
            if(i%3==0)
                sb.append("Fizz");
            if(i%5==0)
                sb.append("Buzz");
            if (sb.length() == 0)
                sb.append(i);
            s.add(sb.toString());
        }
        return s;
    }
}
