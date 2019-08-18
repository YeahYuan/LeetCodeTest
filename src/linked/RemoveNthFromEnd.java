package linked;

import java.util.ArrayList;
import java.util.List;

/**删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 说明：
    给定的 n 保证是有效的。

    进阶：
    你能尝试使用一趟扫描实现吗？
 * Created by lll on 19/8/18.
 */
public class RemoveNthFromEnd {

    /*
    两次循环
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        //第一遍遍历出数量
        int count = 0;
        ListNode node = head;
        while (node != null){
            count++;
            node = node.next;
        }

        if (count == n) return head.next;//如果要删除的是头节点

        //第二遍删除指定节点
        ListNode pre = null;
        node = head;
        for (int i = 0; i < count; i++){
            pre = node;
            node = node.next;
            if (i == count-n-1){
                pre.next = node.next;
            }
        }
        return head;
    }

    /*
    一次循环
    考虑用空间换时间
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;
        while (node != null){
            list.add(node);
            node = node.next;
        }

        if (list.size() == n) return head.next;

        node = list.get(list.size()-n);//要删除的节点
        ListNode pre = list.get(list.size()-n-1);
        pre.next = node.next;
        return head;
    }

    /*
    一次循环
    双指针+哑节点
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0);//哑节点避免删除head节点的情况
        dummy.next = head;
        ListNode first = dummy, second = dummy;
        for (int i=1; i<=n+1; i++){//first向前移动n+1,和second保持n的距离
            first = first.next;
        }
        while (first != null){//两个指针同时移动直到first指向末尾的null,此时second指向要删除的前一个结点
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}
