package array;

import java.util.Arrays;

/**
 * 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * Created by lll on 19/8/31.
 */
public class MoveZeroes {
    /*
    双指针
    慢指针记录非0位置+1
    快指针用来遍历
    最后把剩余位置补0
     */
    public void moveZeroes1(int[] nums) {
        int i = 0;//记录非0的位置+1
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0){//不为零
                nums[i] = nums[j];
                i++;
            }
        }
        for (; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /*
    双指针优化
    上面的方法最后写0操作较多,可优化
    > 慢指针（lastnonzerofoundat）之前的所有元素都是非零的。
    > 当前指针和慢速指针之间的所有元素都是零。
    因此，当我们遇到一个非零元素时，我们需要交换当前指针和慢速指针指向的元素，然后前进两个指针。
    如果它是零元素，我们只前进当前指针。
     */
    public void moveZeroes2(int[] nums) {
        for (int i = 0, j = 0; j < nums.length; j++) {
            if (nums[j] != 0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
    }
}
