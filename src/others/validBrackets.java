package others;

import java.util.Stack;

/**有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 有效字符串需满足：
     左括号必须用相同类型的右括号闭合。
     左括号必须以正确的顺序闭合。
 注意空字符串可被认为是有效字符串。
 * Created by lll on 19/8/17.
 */
public class ValidBrackets {
    /*
    先进后出用stack
    一开始打算用map为不同类型的括号分别维护一个单独的计数器
    然后发现 [{] 这种情况没办法判断
    还是要用stack
     */
    public boolean isValid(String s) {
        if (s == "" || s == null)   return true;
        Stack<Character> stack = new Stack<Character>();
        char[] cs = s.toCharArray();
        for (char c : cs) {
            switch (c) {
                case '(':
                case '{':
                case '[':
                    stack.push(c);
                    break;
                case ')':
                    if (!stack.empty() && stack.peek() == '(') {
                        stack.pop();
                        break;
                    } else  return false;
                case '}':
                    if (!stack.empty() && stack.peek() == '{') {
                        stack.pop();
                        break;
                    } else  return false;
                case ']':
                    if (!stack.empty() && stack.peek() == '[') {
                        stack.pop();
                        break;
                    } else  return false;
            }
        }
        return stack.empty();
    }
}
