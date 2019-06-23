package linked;

/**
 * Created by lll on 19/6/22.
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x){
        val = x;
    }

    public void print(){
        System.out.print(val);
        if (next != null){
            next.print();
        }
    }

    //递增n个
    public void set(int i){
        if(i>0){
            next = new ListNode(val + 1 );
            next.set(--i);
        } else
            next = null;
    }
}
