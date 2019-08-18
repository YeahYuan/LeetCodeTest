package linked;

import java.util.ArrayList;
import java.util.List;

/**回文链表
 * 请判断一个链表是否为回文链表。
 * 进阶：
 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * Created by lll on 19/8/18.
 */
public class IsPalindrome {
    //超出时间限制
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<Integer>();
        ListNode node = head;
        while (node != null){
            list.add(node.val);
        }
        for (int start=0, end=list.size()-1; start<end; start++,end--){
            if (list.get(start) != list.get(end)){
                return false;
            }
        }
        return true;
    }

    /*
    1.快慢指针找中点
    2.同时翻转前半段链表
    3.一一验证
     */
    public boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode fast = head.next, slow = head, pre = null, curr = null;
        while (fast != null && fast.next != null){
            curr = slow;
            slow = slow.next;//当前指针跟随slow前进1
            fast = fast.next.next;
            curr.next = pre;//翻转
            pre = curr;//前一指针前进1
        }//slow指向中间数或前一半的最后一个;
         //fast指向null或后一半的最后一个;
        ListNode n1 = slow.next;//后半段
        slow.next = pre;//完成前半段的翻转
        ListNode n2 = (fast == null) ? pre : slow;//前半段.奇数则为中间节点的前一个/下一个 : 偶数则为中间节点

        while (n1 != null && n2 != null){
            if (n1.val != n2.val) return false;
            n1 = n1.next;
            n2 = n2.next;
        }
        return true;
    }
}
