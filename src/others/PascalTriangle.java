package others;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**帕斯卡三角形
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * Created by lll on 19/8/17.
 */
public class PascalTriangle {
    @Test
    public void test(){
        System.out.println(generate(5));
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        for (int i=0; i< numRows; i++){
            List<Integer> list = new ArrayList<Integer>(i+1);
            for (int j=0; j<i+1; j++){
                if (j == 0 || j==i){
                    list.add(1);
                } else {
                    list.add(lists.get(i-1).get(j-1)+lists.get(i-1).get(j));
                }
            }
            lists.add(list);
        }
        return lists;
    }
}
