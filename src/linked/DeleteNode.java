package linked;

/**删除链表中的节点
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
 * 说明:

     链表至少包含两个节点。
     链表中所有节点的值都是唯一的。
     给定的节点为非末尾节点并且一定是链表中的一个有效节点。
     不要从你的函数中返回任何结果。
 * Created by lll on 19/8/18.
 */
public class DeleteNode {
    /*
    很奇妙的题目
    常规想法是把前一个节点的next指向下一个节点
    但这里无法获取前一个节点

    只能把当前节点值替换为next,然后指向next的next节点(即删除next节点)

    注意:
    不能直接给node = node.next
    这样只是把node(变量名,引用)指向next节点,并未改变链表结构
     */
    public void deleteNode(ListNode node) {
        ListNode next = node.next;//肯定存在(至少2节点,node非末尾节点)
        node.val = next.val;
        node.next = next.next;
    }
}
