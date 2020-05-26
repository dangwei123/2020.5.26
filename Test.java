给出一个n个数字的序列a_1,a_2,\dots a_na 
1
​	
 ,a 
2
​	
 ,…a 
n
​	
 ，你想知道所有长度大于等于k的连续子段中，子段数字和最大可以是多少。

连续子段指的是序列中一段连续的数字。子段数字和指的是子段中所有数字相加的和。

public class Solution {
    /**
     * k长连续子段和
     * @param n int整型 
     * @param k int整型 
     * @param a int整型一维数组 
     * @return long长整型
     */
    public long solve (int n, int k, int[] a) {
        // write code here
        long[] preSum=new long[n];
        preSum[0]=a[0];
        for(int i=1;i<n;i++){
            preSum[i]=preSum[i-1]+a[i];
        }
        
        long[] dp=new long[n];
        dp[k-1]=preSum[k-1];
        long max=dp[k-1];
        for(int i=k;i<n;i++){
           dp[i]=Math.max(dp[i-1]+a[i],preSum[i]-preSum[i-k]);
            max=Math.max(max,dp[i]);
        }
        return max;
    }
}



public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String a=sc.next();
            String b=sc.next();
            Map<Character,Integer> map=new HashMap<>();
            for(int i=0;i<a.length();i++){
                char c=a.charAt(i);
                map.put(c,map.getOrDefault(c,0)+1);
            }
            int j=0;
            for(;j<b.length();j++){
                char c=b.charAt(j);
                if(!map.containsKey(c)){
                    System.out.println("No");
                    break;
                }
                int count=map.get(c);
                if(count==0){
                    System.out.println("No");
                    break;
                }
                map.put(c,count-1);
            }
            if(j==b.length())
            System.out.println("Yes");
        }
    }
}




查找兄弟单词
import java.util.*;
public class Main{
    private  static boolean isBro(String a,String b){
        if(a.equals(b)||a.length()!=b.length()) return false;
        int[] needs=new int[26];
        for(int i=0;i<a.length();i++){
            needs[a.charAt(i)-'a']++;
        }
        for(int i=0;i<b.length();i++){
            if(--needs[b.charAt(i)-'a']<0) return false;
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            List<String> list=new ArrayList<>();
            for(int i=0;i<n;i++){
                list.add(sc.next());
            }
            String target=sc.next();
            int num=sc.nextInt();
            List<String> brothers=new ArrayList<>();
            for(String s:list){
                if(isBro(s,target)){
                    brothers.add(s);
                }
            }
            System.out.println(brothers.size());
            Collections.sort(brothers);
            if(num<=brothers.size())
            System.out.println(brothers.get(num-1));
        }
    }
}




import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String s=sc.next();
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<s.length();i++){
                char c=s.charAt(i);
                if(c=='_'){
                    ++i;
                    sb.append((char)(s.charAt(i)^32));
                }else{
                    sb.append(c);
                }
            }
            System.out.println(sb);
        }
    }
}



public class Main{
    private static boolean isValid(char c){
        return (c>='a'&&c<='z')||(c>='A'&&c<='Z');
    }
    private static void reverseSent(String str){
        StringBuilder sb=new StringBuilder();
        int i=0;
        while(i<str.length()){
            while(i<str.length()&&!isValid(str.charAt(i))){
                i++;
            }
            while(i<str.length()&&isValid(str.charAt(i))){
                sb.append(str.charAt(i++));
            }
            while(i<str.length()&&!isValid(str.charAt(i))){
                i++;
            }
            if(i!=str.length()){
                sb.append(" ");
            }
        }
        char[] chars=new String(sb).toCharArray();
        reverse(chars,0,chars.length-1);
        int left=0;
        for(int right=0;right<=chars.length;right++){
            if(right==chars.length||chars[right]==' '){
                reverse(chars,left,right-1);
                left=right+1;
            }
        }
        System.out.println(new String(chars));
    }
    private static void reverse(char[] chars,int left,int right){
        while(left<right){
            char c=chars[left];
            chars[left++]=chars[right];
            chars[right--]=c;
        }
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String sentence=sc.nextLine();
            reverseSent(sentence);
        }
    }
}