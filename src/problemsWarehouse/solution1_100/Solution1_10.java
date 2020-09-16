package problemsWarehouse.solution1_100;

/**
 * @Author: zja
 * @Description:
 * @Date: Created in 16:22 2020/9/14
 */
public class Solution1_10 {
    public static void main(String[] args) {
        //第6题
//        System.out.println(convert("LEETCODEISHIRING",1));

        //第7题
//        System.out.println(reverse(Integer.MIN_VALUE));

        //第8题
//        System.out.println(myAtoiPlus("-2147483649"));

        //第9题
//        System.out.println(isPalindrome(10));
        //第10题
//        System.out.println(isMatch("","..*"));
        System.out.println(test("aa","ba*ab"));
        System.out.println(isMatch2("aa","ba*ab"));
    }


    public String convert(String s, int numRows) {
        if (s==null && s.equals("") || numRows==1)return s;
        int len=s.length();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<numRows;i++){
            int t=i;
            while (t<len){
                sb.append(s,t,t+1);
                t+=2*(numRows-t%(numRows-1)-1);
            }
        }
        return sb.toString();
    }

    /*
    * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
    * 注意:
      假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
    * */
    public int reverse(int x) {
        int result=0;
        while (x!=0){
            int t=x%10;
            long r1=(long)result*10;
            if((int)r1 != r1){
                return 0;
            }
            int r=result*10;
            result=r+t;
            if(((r ^ result) & (t ^ result)) < 0){
                return 0;
            }
            x/=10;
        }
        return result;
    }

    public int myAtoi(String str) {
        str=str.trim();
        int len=str.length();
        String sign=null;
        int result=0;
        String reg="[0-9]";
        boolean f=true;
        for(int i=0;i<len;i++){
            String s=str.substring(i,i+1);
            if(sign==null && f && (s.equals("+") || s.equals("-")))sign=s;
            else if(sign!=null && f && (s.equals("+") || s.equals("-"))) break;
            else if(s.matches(reg)){
                int t = Integer.parseInt(s);
                if((result*10+t)/10!=result){
                    if(sign!=null && sign.equals("-"))return Integer.MIN_VALUE;
                    else return Integer.MAX_VALUE;
                }
                result=result*10+t;
                f=false;
            }else{
                break;
            }
        }
        if(sign==null)sign="";
        return Integer.parseInt(sign+result);
    }


    public int myAtoiPlus(String str){
        str=str.trim();
        if(str.length()==0)return 0;
        char c = str.charAt(0);
        if(!Character.isDigit(c) && c!='-' && c!='+')return 0;
        boolean sign= c != '-';
        int result=0;
        int i = Character.isDigit(c)?0:1;
        while (i<str.length() && Character.isDigit(str.charAt(i))){
            int t=(result*10+(str.charAt(i)-'0'))/10;
            if(t!=result)return sign?Integer.MAX_VALUE:Integer.MIN_VALUE;
            result=result*10+(str.charAt(i++)-'0');
        }
        return sign?(int)result:(int)-result;
    }

    /*
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * */
    public static boolean isPalindrome(int x) {
        if(x < 0 || (x%10==0 && x!=0))return false;
        int t1=0;
        while(t1<x){
            t1=t1*10+x%10;
            x/=10;
        }
        return t1 == x || t1/10 == x;
    }

    //屎山
    public static boolean isMatch(String s, String p) {
        if(p.equals(".*"))return true;
        int index=s.length()-1;
        int i;
        for(i=p.length()-1;i>=0;i--){
            char c = p.charAt(i);
            if(c=='*'){
                if(i-1<0)return false;
                char c1 = p.charAt(i - 1);
                if(c1=='.'){
                    if(i-2<0)return true;

                    int t=i;
                    int count=0;
                    while (t-2>=0){
                        char c2 = p.charAt(t - 2);
                        if(c2=='*')t-=2;
                        else if(c2=='.'){
                            t--;
                            count++;
                        }else break;
                    }

                    if (t-2>=0){
                        char c2 = p.charAt(t - 2);
                        while(index>=0 && s.charAt(index)!=c2){
                            index--;
                        }
                        i--;
                    }else {
                        index=-1;
                        i--;
                    }
                    if(s.length()>0)index+=count;

                }else{
                    int t=i;
                    int ti=index;
                    while (index>=0 && t-2>=0 && s.charAt(index)==c1){
                        if(p.charAt(t-2)!='*' && p.charAt(t-2)!='.'){
                            break;
                        }
                        t-=2;
                    }
                    while(index>=0 && s.charAt(index)==c1){
                        index--;
                    }

                    if(i-2>=0 && (i-t)/2+1==ti-index){
                        if(t-2>=0 && (p.charAt(t-2)==c1 || p.charAt(t-2)=='.')){
                            index++;
                        }
                    }
                    i--;
                }
            }else if(c=='.'){
                if(index<0)return false;
                index--;
            }else {
                if(index<0 || c!=s.charAt(index))return false;
                index--;
            }
            if(index<0)break;
        }
        while (i>0){
            i--;
            char c = p.charAt(i);
            if(c=='*'){
                i--;
            }else {
                return false;
            }
        }
        return index<0;
    }

    public static boolean test(String s,String p){
        int m = s.length();
        int n = p.length();
        boolean[][] f = new boolean[m+1][n+1];
        f[0][0]=true;
        for(int i=0;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(p.charAt(j-1)!='*'){
                    if(matches(s,p,i,j)){
                        f[i][j]=f[i-1][j-1];
                    }
                }else{
                    f[i][j]=f[i][j-2];
                    if(matches(s,p,i,j-1)){
                        f[i][j]=f[i][j-2] || f[i-1][j];
                    }
                }
            }
        }
        return f[m][n];
    }

//    public static boolean matches(String s, String p, int i, int j) {
//        if (i == 0) {
//            return false;
//        }
//        if (p.charAt(j - 1) == '.') {
//            return true;
//        }
//        return s.charAt(i - 1) == p.charAt(j - 1);
//    }

    public static boolean isMatch2(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                }
                else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

}
