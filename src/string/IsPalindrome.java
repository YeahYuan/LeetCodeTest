package string;

import java.util.ArrayList;
import java.util.List;

/**验证回文字符串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 说明：本题中，我们将空字符串定义为有效的回文串。
 * Created by lll on 19/8/25.
 */
public class IsPalindrome {

    /*
    方法一:转为ArrayList+双指针
     */
    public boolean isPalindrome1(String s) {
        s = s.trim().toLowerCase();
        if (s == "") return true;
        List<Character> list = new ArrayList<Character>();
        for (int i=0; i<s.length(); i++){
            Character c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')){
                list.add(c);
            }
        }//把所有有效字符转为arrayList

        int start = 0, end = list.size()-1;
        while (start < end) {
            if (list.get(start) != list.get(end)) {
                return false;
            } else {
                start++;
                end--;
            }
        }
        return true;
    }

    /*
    方法二:双指针
     */
    public boolean isPalindrome2(String s) {
        s = s.trim().toLowerCase();
        if (s == null || s.length()==1) return true;

        int left = 0, right = s.length()-1;
        while (left <right){
            while (!check(s.charAt(left))){
                left++;
                if (left == s.length()) return true;
            }
            while (!check(s.charAt(right))){
                right--;
            }
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    private boolean check(char c){
        if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) return true;
        else return false;
    }


}
