package array;

import org.junit.Test;

import java.util.*;

/**
 * 字谜分组
 * 给定一个字符串数组，将字母异位词组合在一起。
 * 字母异位词指字母相同，但排列不同的字符串。
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * Created by lll on 19/9/6.
 */
public class GroupAnagrams {

    /*
    超出时间限制
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<List<String>>();
        List<String> list = new ArrayList<String>();
        list.add(strs[0]);
        res.add(list);
        for (int i = 1; i < strs.length; i++) {//遍历strs
            boolean hasAnagram = false;
            for (int j = 0; j < res.size(); j++) {//遍历resList
                List<String> l1 = res.get(j);
                if (isAnagram(strs[i], l1.get(0))) {//是字母异动词,直接add到list上
                    l1.add(strs[i]);
                    hasAnagram = true;
                    break;
                }
            }
            if (!hasAnagram) {//均不是字母异位词,新建list
                List<String> l2 = new ArrayList<String>();
                res.add(l2);
                l2.add(strs[i]);
            }
        }
        return res;
    }

    //string包中有相应算法题,含多种实现方法
    private boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] != c2[i]) return false;
        }
        return true;
    }

    /*
    方法一：排序数组分类
    思路
    当且仅当它们的排序字符串相等时，两个字符串是字母异位词。
    算法
    维护一个映射 ans : {String -> List}，其中每个键 \K 是一个排序字符串，
    每个值是初始输入的字符串列表，排序后等于 K。

     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        if (strs.length == 0)
            return  new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String s : strs){
            char[] cs = s.toCharArray();
            Arrays.sort(cs);
//            String sortS = cs.toString(); //char[]的toString()是直接继承Object的地址值,不能用
            String sortS = String.valueOf(cs);
            if (!map.containsKey(sortS))
                map.put(sortS, new ArrayList<String>());
            map.get(sortS).add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }

    /*
    方法二：按计数分类
    思路
    当且仅当它们的字符计数（每个字符的出现次数）相同时，两个字符串是字母异位词。
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs.length == 0)
            return  new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        int[] count = new int[26];
        for (String s : strs){
            Arrays.fill(count, 0);//把数组初始化为0
            for (char c : s.toCharArray()){
                count[c-'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i : count){
                sb.append("#").append(i);
            }
            String key = sb.toString();
            if (!map.containsKey(key))
                map.put(key, new ArrayList<String>());
            map.get(key).add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }


    @Test
    public void test(){
        String s = "cat";
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        String sortS = cs.toString();
        System.out.println(sortS);
    }

}
