package array;

import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 说明：
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶:
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * Created by lll on 19/6/15.
 */
public class Intersect {
    public static void main(String[] args) {
        int[] nums1 = {43, 85, 49, 2, 83, 2, 39, 99, 15, 70, 39, 27, 71, 3, 88, 5, 19, 5, 68, 34, 7, 41, 84, 2, 13, 85, 12, 54, 7, 9, 13, 19, 92};
        int[] nums2 = {10, 8, 53, 63, 58, 83, 26, 10, 58, 3, 61, 56, 55, 38, 81, 29, 69, 55, 86, 23, 91, 44, 9, 98, 41, 48, 41, 16, 42, 72, 6, 4, 2, 81, 42, 84, 4, 13};

        intersect4(nums1, nums2);

    }

    /*
    法一:
    极慢但原创
    常规方法,直接用数组操作
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        //保证第一个数组比较短
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        //排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        //排序之后出现相同的就从再后一个开始比较
        List<Integer> list = new ArrayList<Integer>();
        int index = 0;
        for (int x : nums1) {
            int i = index;
            System.out.println(i);
            for (; i < nums2.length; i++) {
                if (x == nums2[i]) {
                    list.add(x);
                    index = i + 1;
                    break;
                }
            }
        }

        //最终结果list转数组
        int[] ans = new int[list.size()];
        int z = 0;
        for (Integer y : list) {
            ans[z++] = y;
        }
        System.out.println(Arrays.toString(ans));
        return ans;
    }

    /*
    法二:
    用list集合实现
    我一开始也想到了转成list,但用Arrays.asList()直接转是把数组当成一整个对象保存在list中,而不是保存数组中的数据
    所以需要用循环手动转
     */
    public static int[] intersect2(int[] nums1, int[] nums2) {
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        for (int num1 : nums1) {
            list1.add(num1);
        }
        for (int num2 : nums2) {
            if (list1.contains(num2)) {
                list2.add(num2);
                //通过索引remove
                list1.remove(list1.indexOf(num2));
                //通过对象remove
//                list1.remove(Integer.valueOf(num2));
            }
        }

        //最终结果list转数组
        int[] res = new int[list2.size()];
        int i = 0;
        for (int num : list2) {
            res[i++] = num;
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    /*
    排序法
    最快的方法
     */
    public static int[] intersect3(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] res = new int[nums1.length];
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                res[k++] = nums1[i];
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOf(res, k)));
        //不用list转数组,直接剪切数组长度
        return Arrays.copyOf(res, k);
    }

    /*
    还有一个方法用map的键值对记录值和次数
    先用Hashmap记录第一个数组中的元素【放在key】，和出现的次数【放在value】。
    然后再遍历第二个数组，如果找到对用元素&&对应HashMap中的value不为0，
    则添加这个元素到list中等下放到数组中，同时，HashMap中的value值减一，表示已经找到一个相同的了。
    最后的话，将list中的值取出来，放到数组中返回
     */
    public static int[] intersect4(int[] nums1, int[] nums2) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])) {
                map.put(nums1[i], map.get(nums1[i]) + 1);
            } else
                map.put(nums1[i], 1);
        }

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i]) && map.get(nums2[i]) != 0){
                list.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i]) - 1);
            }
        }

        //最终结果list转数组
        int[] res = new int[list.size()];
        int i = 0;
        for (int num : list) {
            res[i++] = num;
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

}
