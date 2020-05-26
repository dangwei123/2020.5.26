在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
public class Solution {
    public boolean Find(int target, int [][] array) {
        int row=array.length;
        if(row==0) return false;
        int col=array[0].length;
        int i=0;
        int j=col-1;
        while(i<row&&j>=0){
            if(array[i][j]==target){
                return true;
            }else if(array[i][j]>target){
                j--;
            }else{
                i++;
            }
        }
        return false;
    }
}

请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
public class Solution {
    public String replaceSpace(StringBuffer str) {
         StringBuilder sb=new StringBuilder();
        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if(c==' '){
                sb.append("%20");
            }else{
                sb.append(c);
            }
        }
        return new String(sb);
    }
}

输入一个链表，按链表从尾到头的顺序返回一个ArrayList。

import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list=new ArrayList<>();
        print(list,listNode);
        return list;
    }
    private void print(ArrayList<Integer> list,ListNode head){
        if(head==null) return;
        print(list,head.next);
        list.add(head.val);
    }
}

输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
import java.util.*;
public class Solution {
    private int index;
    private Map<Integer,Integer> map;
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        map=new HashMap<>();
        for(int i=0;i<in.length;i++){
            map.put(in[i],i);
        }
        return build(pre,in,0,pre.length-1);
    }
    private TreeNode build(int[] pre,int[] in,int left,int right){
        if(left>right||index>=pre.length) return null;
        TreeNode root=new TreeNode(pre[index]);
        int in_in=map.get(pre[index++]);
        root.left=build(pre,in,left,in_in-1);
        root.right=build(pre,in,in_in+1,right);
        return root;
    }
}


用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。

import java.util.Stack;

public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
        if(!stack2.isEmpty()){
            return stack2.pop();
        }
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
}


把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
import java.util.ArrayList;
public class Solution {
    public int minNumberInRotateArray(int [] array) {
        int n=array.length;
        if(n==0) return 0;
        int left=0;
        int right=n-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(array[mid]==array[right]){
                right--;
                continue;
            }
            if(array[mid]>array[right]){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        return array[left];
    }
}

一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。

public class Solution {
    public int JumpFloorII(int target) {
        if(target<3) return target;
        int[] dp=new int[target+1];
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=target;i++){
            for(int j=1;j<i;j++){
                dp[i]+=dp[j];
            }
            dp[i]+=1;
        }
        return dp[target];
    }
}

我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
public class Solution {
    public int RectCover(int target) {
        if(target<=3) return target;
        return RectCover(target-1)+RectCover(target-2);
    }
}

给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。

保证base和exponent不同时为0

public class Solution {
    public double Power(double base, int exponent) {
        double res=1.0;
        int n=Math.abs(exponent);
        while(n!=0){
            if((n&1)!=0){
                res*=base;
            }
            base*=base;
            n>>>=1;
        }
        return exponent>0?res:1/res;
  }
}


输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
public class Solution {
    public void reOrderArray(int [] array) {
        boolean isSwap=true;
        for(int i=0;i<array.length-1;i++){
            isSwap=false;
            for(int j=0;j<array.length-1-i;j++){
                if((array[j]&1)==0&&(array[j+1]&1)==1){
                    int tmp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                    isSwap=true;
                }
            }
            if(!isSwap) break;
        }
    }
}

