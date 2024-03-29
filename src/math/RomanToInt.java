package math;

import java.util.HashMap;
import java.util.Map;

/**罗马数字转整数
 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 字符          数值
 I             1
 V             5
 X             10
 L             50
 C             100
 D             500
 M             1000
 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。
 27 写做  XXVII, 即为 XX + V + II 。

 通常情况下，罗马数字中小的数字在大的数字的右边。
 但也存在特例，例如 4 不写做 IIII，而是 IV。
 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
 同样地，数字 9 表示为 IX。
 这个特殊的规则只适用于以下六种情况：
 I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 * Created by lll on 19/8/11.
 */
public class RomanToInt {
    /*
    常规方法
    还有hashmap\减法两种方法,下次写出来
     */
    public int romanToInt1(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        for (int i=0; i<chars.length; i++){
            switch (chars[i]){
                case 'M':
                    res += 1000;
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'C':
                    if (i+1 < chars.length && chars[i+1] == 'D'){
                        i++;
                        res += 400;
                    } else if (i+1 < chars.length && chars[i+1] == 'M') {
                        i++;
                        res += 900;
                    } else {
                        res += 100;
                    }
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'X':
                    if (i+1 < chars.length && chars[i+1] == 'L'){
                        i++;
                        res += 40;
                    } else if (i+1 < chars.length && chars[i+1] == 'C') {
                        i++;
                        res += 90;
                    } else {
                        res += 10;
                    }
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'I':
                    if (i+1 < chars.length && chars[i+1] == 'V'){
                        i++;
                        res += 4;
                    } else if (i+1 < chars.length && chars[i+1] == 'X') {
                        i++;
                        res += 9;
                    } else {
                        res += 1;
                    }
                    break;
            }
        }
        return res;
    }

    /*
    map
     */
    public int romanToInt2(String s) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int res = 0;
        for (int i=0; i<s.length(); i++){
            if (i+1 < s.length() && map.containsKey(s.substring(i, i+2))){
                res += map.get(s.substring(i, i+2));
                i++;
            } else {
                res += map.get(s.substring(i, i+1));
            }
        }
        return res;
    }

    /*
    特殊情况做减法
     */
    public int romanToInt3(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        //从后往前循环
        for (int i=s.length()-1, last = 0; i>=0; i--){
            int n = romanCharToInt(chars[i]);
            if (n >= last){
                res += n;
            } else {//当前数如果小于后面的数,做减法
                res -= n;
            }
            last = n;
        }
        return res;
    }
    private int romanCharToInt(char c){
        switch (c) {
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X':
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
        }
        return 0;
    }
}
