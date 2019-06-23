package linked;

import java.util.HashSet;
import java.util.Set;

/**环形链表
 * 给定一个链表，判断链表中是否有环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 * Created by lll on 19/6/23.
 */
public class HasCycle {

    /*
    法一:快慢指针
    但是没有用到pos呀
     */
    public boolean hasCycle1(ListNode head) {
        if(head == null || head.next == null)
            return false;
        ListNode fast = head.next;
        ListNode low = head;

        while (fast != null && fast.next != null){
            if(fast == low){
                return true;
            }
            fast = fast.next.next;
            low = low.next;
        }
        return false;
    }

    /*
    法二:哈希表
    效率不高

    思路
    我们可以通过检查一个结点此前是否被访问过来判断链表是否为环形链表。
    常用的方法是使用哈希表。

    算法
    我们遍历所有结点并在哈希表中存储每个结点的引用（或内存地址）。
    如果当前结点为空结点 null（即已检测到链表尾部的下一个结点），
    那么我们已经遍历完整个链表，并且该链表不是环形链表。
    如果当前结点的引用已经存在于哈希表中，那么返回 true（即该链表为环形链表）。
    */
    public boolean hasCycle2(ListNode head) {
        if(head == null)
            return false;
        Set<ListNode> set = new HashSet<ListNode>();
        while(head.next != null){
            if (set.contains(head)){
                return true;
            } else{
                set.add(head);
                head = head.next;
            }
        }
        return false;
    }
}
