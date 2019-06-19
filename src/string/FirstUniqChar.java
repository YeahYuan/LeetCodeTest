package string;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * Created by lll on 19/6/16.
 */
public class FirstUniqChar {

    public static void main(String[] args) {
        String s = "aaaaa";

//        String[] ss = s.split("a");
//        System.out.println(ss.length);


        System.out.println(firstUniqChar1(s));
    }

    /*
    想用spilt()分割字符串,根据分割出的个数判断出现的次数
    但是走不通啊
    用第一个字母分割,最前可分
    用最后一个字母分割,最后不可分
    aa用a分割,分出0
    崩溃!!!
     */
    public static int firstUniqChar0(String s) {
        if (s.length() == 1){
            return 0;
        }
        for (int i = 0; i<s.length()-1; i++){
            String regex = ""+s.charAt(i);
            String[] ss = s.split(regex);
            if (i == s.length()-1){
                return i;
            }
            if (ss.length == 2 && s.charAt(i)!=s.charAt(s.length()-1)){
                return i;
            }
        }
        return -1;
    }


    /*
    法一:
    分别从前后查找,角标都相同的话,说明只有一个
    每次indexof都有遍历,比较慢,但逻辑简单,代码量小

    很多方法都是用字母表遍历(这里直接用字符串字符遍历),前后查找,要注意找不到-1的情况(这里不会有这个问题)
    字符串短的话这样比较快,字符串>26用他们的方法比较快
     */
    public static int firstUniqChar1(String s) {
        for (int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if(s.indexOf(c) == s.lastIndexOf(c) ){
                return i;
            }
        }
        return -1;
    }

    /*
    法二:
    第一次遍历字符串求得各字符出现次数
    第二次遍历字符串找出第一个出现次数为1的字符

    两次遍历+数组,比1还快哎
     */
    public static int firstUniqChar2(String s) {

        int[] letter = new int[26];
        for (char c :s.toCharArray()){
            letter[c - 'a']++;
        }
        for (int i = 0; i<s.length(); i++){
            if (letter[s.charAt(i) - 'a'] ==1){
                return i;
            }
        }
        return -1;
    }

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
