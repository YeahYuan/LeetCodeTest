package array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * Created by lll on 19/9/13.
 */
public class LengthOfLongestSubstring {
    /*
    方法一:暴力法(比官方题解的暴力法好一些,时间复杂度2次方)
    依次以每个字符开头检查其不含重复字符的子串长度
    就是很慢
     */
    public int lengthOfLongestSubstring1(String s) {
        if (s.length() == 0) return 0;
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> set = new HashSet<Character>();
//            Set<Character> set = new HashSet<Character>(s.charAt(i));
            //带参数的构造函数不是添加字符,而是转化为int指定初始容量
            set.add(s.charAt(i));
            for (int j = i+1; j < s.length(); j++) {
                char c = s.charAt(j);
                if (!set.contains(c)){
                    set.add(c);
                } else {
                    break;
                }
            }
            count = Math.max(count, set.size());
        }
        return count;
    }

    /*
    方法二:滑动窗口法
    滑动窗口是数组/字符串问题中常用的抽象概念。
    窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合，即 [i, j)[i,j)（左闭，右开）。
    而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。
    例如，我们将 [i, j)[i,j) 向右滑动 11 个元素，则它将变为 [i+1, j+1)[i+1,j+1)（左闭，右开）。

    回到我们的问题，我们使用 HashSet 将字符存储在当前窗口 [i, j)[i,j)（最初 j = ij=i）中。
    然后我们向右侧滑动索引 jj，如果它不在 HashSet 中，我们会继续滑动 jj。
    直到 s[j] 已经存在于 HashSet 中。
    此时，我们找到的没有重复字符的最长子字符串将会以索引 ii 开头。
    如果我们对所有的 ii 这样做，就可以得到答案。
     */
    public int lengthOfLongestSubstring2(String s) {
        Set<Character> set = new HashSet<Character>();
        int len = s.length(), count=0, i=0, j=0;
        while (i<len && j<len){
            char c = s.charAt(j);
            if (!set.contains(c)) {
                set.add(c);
                j++;
                count = Math.max(count, set.size());
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return count;
    }

    /*
    方法三：优化的滑动窗口
    上述的方法最多需要执行 2n 个步骤。
    事实上，它可以被进一步优化为仅需要 n 个步骤。
    我们可以定义字符到索引的映射，而不是使用集合来判断一个字符是否存在。
    当我们找到重复的字符时，我们可以立即跳过该窗口。

    也就是说，如果 s[j] 在 [i, j) 范围内有与 j'重复的字符，我们不需要逐渐增加 i 。
    我们可以直接跳过 [i，j']范围内的所有元素，并将 i变为 j' + 1。

    （使用 HashMap）
     */
    public int lengthOfLongestSubstring31(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int len = s.length(), count=0;
        for (int i=0, j=0;  i<len && j<len ; j++) {
            char c = s.charAt(j);
            if (map.containsKey(c)){
                //出现重复时,改变i值为重复字符的下一个角标开始
                i = Math.max(i, map.get(c));
            }
            count = Math.max(count, j-i+1);
            map.put(c, j+1);
        }
        return count;
    }

    /*
    （假设字符集为 ASCII 128）
    以前的我们都没有对字符串 s 所使用的字符集进行假设。
    当我们知道该字符集比较小的时侯，我们可以用一个整数数组作为直接访问表来替换 Map。

    常用的表如下所示：
    int [26] 用于字母 ‘a’ - ‘z’ 或 ‘A’ - ‘Z’
    int [128] 用于ASCII码
    int [256] 用于扩展ASCII码
    */
    public int lengthOfLongestSubstring32(String s) {
        int len = s.length(), count=0;
        int[] index = new int[128];
        for (int i=0, j=0;  i<len && j<len ; j++) {
            char c = s.charAt(j);
            i = Math.max(i, index[c]);
            count = Math.max(count, j-i+1);
            index[c] = j+1;
        }
        return count;
    }
}
