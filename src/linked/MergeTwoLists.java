package linked;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。

 示例：

 输入：1->2->4, 1->3->4
 输出：1->1->2->3->4->4

 * 要区分指针和节点!!!

 * Created by lll on 19/6/22.
 */
public class MergeTwoLists {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.set(3);
        ListNode l2 = new ListNode(1);
        l2.set(2);

        mergeTwoLists2(l1, l2).print();
    }

    /*
    法一:迭代法
    我们假设 l1 元素严格比 l2元素少，我们可以将 l2 中的元素逐一插入 l1 中正确的位置。
    算法
    首先，我们设定一个哨兵节点 "prehead" ，这可以在最后让我们比较容易地返回合并后的链表。
    我们维护一个 prev 指针，我们需要做的是调整它的 next 指针。
    然后，我们重复以下过程，直到 l1 或者 l2 指向了 null ：
    如果 l1 当前位置的值小于等于 l2 ，我们就把 l1 的值接在 prev 节点的后面同时将 l1 指针往后移一个。
    否则，我们对 l2 做同样的操作。不管我们将哪一个元素接在了后面，我们都把 prev 向后移一个元素。

    在循环终止的时候， l1 和 l2 至多有一个是非空的。
    由于输入的两个链表都是有序的，所以不管哪个链表是非空的，它包含的所有元素都比前面已经合并链表中的所有元素都要大。
    这意味着我们只需要简单地将非空链表接在合并链表的后面，并返回合并链表。
     */
    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);//哨兵节点prehead,在最后让我们比较容易地返回合并后的链表
        ListNode ln = res;//指针
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                ln.next = l1;
                ln = ln.next;
                l1 = l1.next;
            } else {
                ln.next = l2;
                ln = ln.next;
                l2 = l2.next;
            }
        }

        //剩下的(不为null)直接补上
        ln.next = (l1 == null) ? l2 : l1;
        return res.next;
    }

    /*
    法二:递归法
    也就是说，两个链表头部较小的一个与剩下元素的 merge 操作结果合并。
    算法
    我们直接将以上递归过程建模，首先考虑边界情况。
    特殊的，如果 l1 或者 l2 一开始就是 null ，那么没有任何操作需要合并，所以我们只需要返回非空链表。
    否则，我们要判断 l1 和 l2 哪一个的头元素更小，然后递归地决定下一个添加到结果里的值。
    如果两个链表都是空的，那么过程终止，所以递归过程最终一定会终止。
     */
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {

        if(l1 == null || l2 == null){
            return (l1 == null) ? l2 : l1;
        } else {
            if (l1.val < l2.val){
                l1.next = mergeTwoLists2(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists2(l1, l2.next);
                return l2;
            }
        }
    }
}






