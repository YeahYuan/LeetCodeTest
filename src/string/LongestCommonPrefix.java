package string;

import org.junit.Test;

/**
 * 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 * Created by lll on 19/8/25.
 */
public class LongestCommonPrefix {
    /*
    方法一:双层循环
    外循环为每个字符
    内循环为每个字符串
     */
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) return "";
        for (int i=0; i<strs[0].length(); i++){//i控制第几个字符
            char c =strs[0].charAt(i);
            for (int j=1; j<strs.length; j++){//j控制第几个字符串
                if (i >= strs[j].length() || strs[j].charAt(i) != c){//字符串长度不够或字符不相同
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /*
    方法二:水平扫描
    两两string找公共前缀
    每次去掉末尾字符
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i=1; i<strs.length; i++){
            if (prefix.isEmpty()) return "";
            while (strs[i].indexOf(prefix) != 0){//即prefix不是其前缀
                prefix = prefix.substring(0, prefix.length()-1);
            }
        }
        return prefix;
    }

    /*
    方法三:递归(分治)
     */
    public String longestCommonPrefix3(String[] strs) {
        if (strs.length == 0) return "";
        return longestCommonPrefix(strs, 0, strs.length-1);
    }
    //把字符串数组拆成单个字符串,再两两比较
    private String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {//只有一个
            return strs[l];
        } else {
            int mid = (l+r)/2;
            String left = longestCommonPrefix(strs, l, mid);
            String right = longestCommonPrefix(strs, mid+1, r);
            return commonPrefix(left, right);
        }
    }
    //比较两个字符串的公共前缀
    private String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for (int i=0; i<min; i++){
            if (left.charAt(i) != right.charAt(i)){
                return left.substring(0,i);
            }
        }
        return left.substring(0,min);
    }

    /*
    方法四:二分查找法
     */
    public String longestCommonPrefix4(String[] strs) {
        if (strs.length == 0) return "";
        int min = Integer.MAX_VALUE;
        for (String str : strs){
            min = Math.min(min, str.length());
        }//找出所有字符串的最短长度
        int low = 0, high = min;
        while (low <= high){
            int mid = (low+high)/2;
            if (isCommonPrefix(strs, strs[0].substring(0, mid))){//如果是所有串的公共前缀,舍弃前一半查找空间
                low = mid + 1;
            } else {//如果不是,舍弃后一半查找空间
                high = mid - 1;
            }
        }
        return strs[0].substring(0, (low+high)/2);
    }

    //判断是否为所有串的公共前缀
    private boolean isCommonPrefix(String[] strs, String prefix) {
        if (prefix.isEmpty()) return true;
        for (int i=0; i<strs.length; i++){
            if (!strs[i].startsWith(prefix)) return false;
        }
        return true;
    }

    // TODO: 19/8/28  更进一步--字典树
    @Test
    public void test(){
        longestCommonPrefix3(new String[]{"flower","flow","flight"});
    }

}
