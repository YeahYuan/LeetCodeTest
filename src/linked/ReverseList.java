package linked;

import java.util.LinkedList;

/**
 * 反转一个单链表。

 示例:

 输入: 1->2->3->4->5->NULL
 输出: 5->4->3->2->1->NULL
 进阶:
 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * Created by lll on 19/6/22.
 */
public class ReverseList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.set(4);

        ListNode res = reverseList3(head);
        res.print();
    }

    /*
    法一:笨方法
    转成LinkedList,用addFirst和remove
     */
    public static ListNode reverseList1(ListNode head) {

        if (head == null){
            return null;
        }

        LinkedList<Integer> ll = new LinkedList();
        nodeToList(ll, head);
        for(int i = 0; i<ll.size(); i++){
            ll.addFirst(ll.remove(i));
        }
        listToNode(ll, head);
        return head;
    }

    public static void nodeToList(LinkedList ll, ListNode head){
        if (head.next != null){
            ll.addLast(head.val);
            nodeToList(ll, head.next);
        }else
            ll.addLast(head.val);
    }

    public static void listToNode(LinkedList<Integer> ll, ListNode head){
        head.val = ll.remove(0);
        if (head.next != null){
            listToNode(ll, head.next);
        }
    }

    /*
    法二:官方迭代法(都好难懂啊啊啊)
    在遍历列表时，将当前节点的 next 指针改为指向前一个元素。由于节点没有引用其上一个节点，因此必须事先存储其前一个元素。
    在更改引用之前，还需要另一个指针来存储下一个节点。不要忘记在最后返回新的头引用！
     */
    public static ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;//关键:下一个指向前一个
            prev = curr;
            curr = nextTemp;
        }
        return prev;

    }
    //这个好懂一点
    public static ListNode reverseList22(ListNode head) {

        if (head == null) return head;
        ListNode tail = null;

        while (head.next != null) {
            ListNode temp = head.next;  //备份当前的下一个
            head.next = tail; //当前指向新尾
            tail = head;   //修改尾
            head = temp;
        }
        head.next = tail;
        return head;
    }


    /*
    法三:官方递归法(都好难懂啊啊啊)
    递归版本稍微复杂一些，其关键在于反向工作。
    假设列表的其余部分已经被反转，现在我该如何反转它前面的部分？

     */
    public static ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList3(head.next);//先从最后一个开始
        head.next.next = head;//关键:下一个的下一个指向自己(反转箭头
        head.next = null;
        return p;
    }
}
