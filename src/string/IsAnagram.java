package string;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 * Created by lll on 19/8/24.
 */
public class IsAnagram {
    /*
    方法一:使用集合(也可以用map)
    很慢
     */
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) return false;
        LinkedList<Character> list = new LinkedList<Character>();
        for (int i = 0; i < s.length(); i++) {
            list.add(s.charAt(i));
        }
        for (int i = 0; i < t.length(); i++) {
            Character c = t.charAt(i);
            if (!list.remove(c)) {
                return false;
            }
        }
        return list.isEmpty();
    }

    /*
    方法二:转为字符数组,排序,比较
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        Arrays.sort(sChar);
        Arrays.sort(tChar);
        return Arrays.equals(sChar, tChar);
    }

    /*
    方法三:为每一个字母计数
     */
    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] charCount = new int[26];//26个小写字母计数
        for (int i = 0; i < t.length(); i++) {
            charCount[s.charAt(i)-'a']++;
            charCount[t.charAt(i)-'a']--;
        }
        for (int count : charCount){
            if (count != 0) return false;
        }
        return true;
    }
}
