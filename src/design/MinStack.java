package design;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 最小栈(先进后出)
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * Created by lll on 19/8/10.
 */
/*
自己想的方法
使用LinkedList
但是效率低,getMin方法也不符合常数时间的条件
 */
class MinStack0 {
    LinkedList<Integer> list;
    public MinStack0() {
        list = new LinkedList();
    }
    public void push(int x) {
        list.offerFirst(x);
    }
    public void pop() {
        list.pollFirst();
    }
    public int top() {
        return list.peekFirst();
    }
    public int getMin() {
        int min = top();
        for (int i : list) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }
}

/*
使用两个Stack
借用一个辅助栈min_stack，用于存储stack中最小值：
min_stack的作用是对stack中的元素做标记，
标记的原则是min_stack中元素一定是降序的（栈底最大栈顶最小）。
换个角度理解，min_stack等价于遍历stack所有元素，
把升序的数字都删除掉，留下一个从栈底到栈顶降序的栈。
本题要求获取最小值的复杂度是O(1)，因此须构建辅助栈，
在push与pop的过程中始终保持辅助栈为一个降序栈。
 */
class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> min_stack;
    public MinStack() {
        stack = new Stack<Integer>();
        min_stack = new Stack<Integer>();
    }
    //push:每当push新值进来时，如果“小于等于”min_stack栈顶值，则一起push到min_stack，即更新了最小值；
    public void push(int x) {
        stack.push(x);
        if (min_stack.isEmpty() || x <= min_stack.peek()){//注意“小于等于”保留重复的,因为pop会不断移除
            min_stack.push(x);
        }
    }
    //pop:判断pop出去的元素值是否是min_stack栈顶元素值（即最小值），
    // 如果是则将min_stack栈顶元素一起pop，
    // 这样可以保证min_stack栈顶元素始终是stack中的最小值。
    public void pop() {
        if (stack.pop() == min_stack.peek()){
            min_stack.pop();
        }
    }
    public int top() {
        return stack.peek();
    }
    public int getMin() {
        return min_stack.peek();
    }
}
