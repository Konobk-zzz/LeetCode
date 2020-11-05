package problemsWarehouse.solution1_100;

import java.util.*;

/**
 * @Author: zja
 * @Description:
 * @Date: Created in 10:16 2020/9/16
 */
public class Solution11_20 {
    public static void main(String[] args) {
        Long start=System.currentTimeMillis();

        //第11题
//        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));

        //第12题
//        System.out.println(intToRoman(9));

        //第13题
//        System.out.println(romanToInt("MCMXCIV"));

        //第14题
//        System.out.println(longestCommonPrefix(new String[]{"","a"}));

        //第15题
//        System.out.println(threeSum1(new int[]{-1, 0, 1, 2, -1, -4}));

        //第16题
//        System.out.println(threeSumClosest1(new int[]{1,1,1,1},0));

        //第17题
//        System.out.println(letterCombinations("23"));

//        test2<Integer> testTtest2 = new test2<Integer>();
//        System.out.println(fourSum(new int[]{-1,0,-5,-2,-2,-4,0,1,-2},-9));

        aClass aClass = new aClass();
        System.out.println(Integer.MAX_VALUE);
        System.out.println(aClass.str.hashCode());
        System.out.println(aClass.a.hashCode());

        testMove();
        Long end=System.currentTimeMillis();
        System.out.println("耗时："+(end-start));
    }

    public static int maxArea(int[] height){
        if(height.length<2)return 0;
        int left=0;
        int right=height.length-1;
        int max=0;
        while(left<right){
            if(height[left]>height[right]){
                if((right-left)*height[right]>max)max=(right-left)*height[right];
                right--;
            }else{
                if((right-left)*height[left]>max)max=(right-left)*height[left];
                left++;
            }
        }
        return max;
    }

    public static String intToRoman(int num) {
        int[] val=new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] str=new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<val.length && num>=0;i++){
            while(val[i]<=num){
                num-=val[i];
                sb.append(str[i]);
            }
        }
        return sb.toString();
    }

    public static int romanToInt(String s) {
        int[] val=new int[]{1000,500,100,50,10,5,1};
        String[] str=new String[]{"M","D","C","L","X","V","I"};
        int result=0;
        int last=0;
        for(int i=0;i<s.length();i++){
            for(int j=0;j<str.length;j++){
                if(str[j].equals(s.substring(i,i+1))){
                   if(last<val[j])result-=2*last;
                   result+=val[j];
                   last=val[j];
                   break;
                }
            }
        }
        return result;
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length==0)return "";
        int min=Integer.MAX_VALUE;
        for(int i=0;i<strs.length;i++){
            if(strs[i].length()<min)min=strs[i].length();
        }
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<min;i++){
            char c = strs[0].charAt(i);
            for(int j=1;j<strs.length;j++){
                if(strs[j].charAt(i)!=c)return buffer.toString();
            }
            buffer.append(c);
        }
        return buffer.toString();
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static List<List<Integer>> threeSum1(int[] nums) {
        if(nums==null || nums.length<3)return new ArrayList<>();
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0)break;
            if(i>0 && nums[i]==nums[i-1])continue;

            int c=nums.length-1;
            int target=-nums[i];
            for(int j=i+1;j<nums.length;j++){
                if(j>i+1 && nums[j]==nums[j-1])continue;
                while(j<c && nums[j]+nums[c]>target){
                    c--;
                }
                if (j==c)break;
                if(nums[j]+nums[c]==target){
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[c]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        long start=System.currentTimeMillis();
        if(nums.length<3)return new ArrayList<>();
        insertSort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0;i<nums.length-2;i++){
            if(nums[i]>0)break;
            for(int j=i+1;j<nums.length-1;j++){
                if(twoFind(nums,-(nums[i]+nums[j]),j+1,nums.length-1)){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(-(nums[i]+nums[j]));
                    result.add(list);
                }
            }
        }
        long end=System.currentTimeMillis();
        System.out.println("耗时："+(end-start));
        return result;
    }

    public static boolean isExist(List<List<Integer>> list,int a,int b,int c){
        for (List<Integer> l1:list) {
            if (l1.get(0)==a)
                if(l1.get(1)==b)
                    if(l1.get(2)==c)
                        return true;
        }
        return false;
    }

    public static void insertSort(int[] nums){
        for (int i=1;i<nums.length;i++){
            int t=nums[i];
            int j;
            for(j=i;j>0;j--){
                if (nums[j-1]<t)break;
                nums[j]=nums[j-1];
            }
            if(j!=i)nums[j]=t;
        }
    }

    public static boolean twoFind(int[] a,int target,int l,int r){
        if(target < a[l] || target > a[r] || l > r){
            return false;
        }
        int mid=(l+r)/2;
        if(a[mid]<target){
            return twoFind(a,target,mid+1,r);
        }else if(a[mid]>target){
            return twoFind(a,target,l,mid-1);
        }else{
            return true;
        }
    }

    public static int threeSumClosest(int[] nums, int target) {
        if(nums==null || nums.length<3)return 0;
        Arrays.sort(nums);

        int min=Integer.MAX_VALUE;
        int ans=0;
        for(int i=0;i<nums.length;i++){
            for(int j=i;j<nums.length;j++){
                for(int k=j;k<nums.length;k++){
                    int t=Math.abs(target-(nums[i]+nums[j]+nums[k]));
                    if(t<min){
                        min=t;
                        ans=nums[i]+nums[j]+nums[k];
                    }
                }
            }
        }
        return ans;
    }

    //不重复解
    public static int threeSumClosest1(int[] nums, int target) {
        if(nums==null || nums.length<3)return 0;
        Arrays.sort(nums);

        int ans=nums[0] + nums[1] + nums[2];
        for(int i=0;i<nums.length-2;i++){

            int l=i+1;
            int r=nums.length-1;
            while(l<r){
                int sum = nums[i] + nums[l] + nums[r];
                if(sum>target){
                    r--;
                }else if(sum<target){
                    l++;
                }else {
                    return sum;
                }
                if(Math.abs(target-sum)<Math.abs(target-ans))ans=sum;
            }
        }
        return ans;
    }

    public static List<String> letterCombinations(String digits) {
        List<String> list=new ArrayList<>();
        if(digits==null || digits.equals(""))return list;

        HashMap<Character, String> map = new HashMap<Character, String>(){{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        backtrack(list,map,digits,0,new StringBuffer());

        return list;
    }

    public static void backtrack(List<String> list,Map<Character,String> map,String digits,int index,StringBuffer buffer){
        if(index==digits.length()){
            list.add(buffer.toString());
        }else {
            String s = map.get(digits.charAt(index));
            for(int i=0;i<s.length();i++){
                buffer.append(s.charAt(i));
                backtrack(list,map,digits,index+1,buffer);
                buffer.deleteCharAt(index);
            }
        }
    }

    public static String test(int num) {
        if(num<1 || num>3999)return null;
        int t=1000;

        boolean o=true;

        String result="";

        while(num!=0){
            int p=num/t;
            if(p==0){
                t/=10;
                continue;
            }

            String c=getChar(t);
            String c2=getChar(o?t*5:t);
            String h=getChar(t*10);
            if(p>4){
                if(p==9){
                    result+=c+h;
                }else{
                    result+=c2;
                    for(int i=0;i<p-5;i++){
                        result+=c;
                    }
                }
            }else{
                if(p==4){
                    result+=c+c2;
                }else{
                    for(int i=0;i<p;i++){
                        result+=c;
                    }
                }
            }

            num=num%(p*t);
            t/=10;
        }

        return result;
    }

    public static String getChar(int num){
        if(num==1000)
            return "M";
        else if(num==500)
            return "D";
        else if(num==100)
            return "C";
        else if(num==50)
            return "L";
        else if(num==10)
            return "X";
        else if(num==5)
            return "V";
        else if(num==1)
            return "I";
        else
            return "";
    }


    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result=new ArrayList<>();
        if(nums==null || nums.length<4)return result;
        Arrays.sort(nums);
        for(int i=0;i<nums.length-3;i++){
            if(nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target)break;
            if(i>0 && nums[i]==nums[i-1])continue;
            for(int j=i+1;j<nums.length-2;j++){
                if(j>i+1 && nums[j]==nums[j-1])continue;
                int p=j+1;
                int q=nums.length-1;
                while(p<q){
                    if(p>j+1 && nums[p]==nums[p-1]){
                        p++;
                        continue;
                    }
                    if(q<nums.length-1 && nums[q]==nums[q+1]){
                        q--;
                        continue;
                    }
                    int sum=nums[i]+nums[j]+nums[p]+nums[q];
                    if(target==sum){
                        List<Integer> list=new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[p]);
                        list.add(nums[q]);
                        result.add(list);
                        p++;
                        q--;
                    }else if(sum<target){
                        p++;
                    }else{
                        q--;
                    }
                }
            }
        }
        return result;
    }


    public static void testHash(){

    }

    private static class aClass{

        String str;

        Integer a;

        public aClass(){
            this.str="testtaadsaa";
            this.a=4646;
        }
    }

    private static void testMove(){
        System.out.println(2<<3);
        System.out.println(2>>3);
    }
}
