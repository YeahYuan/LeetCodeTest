package design;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**Shuffle an Array
 * 打乱一个没有重复元素的数组。
 * // 以数字集合 1, 2 和 3 初始化数组。
     int[] nums = {1,2,3};
     Solution solution = new Solution(nums);

     // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
     solution.shuffle();

     // 重设数组到它的初始状态[1,2,3]。
     solution.reset();

     // 随机返回数组[1,2,3]打乱后的结果。
     solution.shuffle();
 * Created by lll on 19/8/10.
 */
public class ShuffleArray {

    @Test
    public void test(){
        int[] nums = {1,2,3};
        Solution obj = new Solution(nums);
        System.out.println(Arrays.toString(obj.shuffle()));
        System.out.println(Arrays.toString(obj.reset()));
        System.out.println(Arrays.toString(obj.shuffle()));
    }
}

/*
自己想的方法
思路一:转成集合,随机从集合中取出数组成打乱的数组(官方法一:暴力)
思路二:不想借助集合.让数组中的每一个数随机交换达到随机目的(官方法二： Fisher-Yates 洗牌算法)
代码如下,也提交成功了。但!这不是真的随机!
    > 每一个数随意交换并不是,每一个数只能和后面的数(及自身)交换才是!
    > 比如3个数,前者有27种结果,后者有6种结果, 27 不能被 6 整除，所以一定有某些情况被「偏袒」了。
 */
class Solution0 {
    private final int[] nums;//初始
    private int[] temp;//可变

    public Solution0(int[] nums) {
        this.nums = nums;
        this.temp = nums.clone();//注意要克隆,否则会改变nums
    }

    public int[] reset() {
        return nums;
    }

    /*
    遍历数组,对每一个int选择随机位置交换
     */
    public int[] shuffle() {
        if (temp == null || temp.length == 0)
            return null;
        if (temp.length == 1) {
            return temp;
        }
        Random ran = new Random();
        for (int i =0; i<temp.length; i++){
            int r = ran.nextInt(temp.length);
            //用异或算法交换会出现0(i==r时),要增加判断
            int aaa = temp[i];
            temp[i] = temp[r];
            temp[r] = aaa;
        }
        return temp;
    }
}

/*
官方法二： Fisher-Yates 洗牌算法
 */
class Solution {
    private final int[] original;//初始
    private int[] array;//可变

    public Solution(int[] nums) {
        this.array = nums;
        this.original = nums.clone();
    }

    public int[] reset() {
        return original;
    }

    public int[] shuffle() {
        Random rand = new Random();
        for (int i =0; i<array.length; i++){
            int r = rand.nextInt(array.length-i) + i;
            int temp = array[i];
            array[i] = array[r];
            array[r] = temp;
        }
        return array;
    }
}


