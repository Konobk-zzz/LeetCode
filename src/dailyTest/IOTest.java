package dailyTest;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: zja
 * @Description:
 * @Date: Created in 16:08 2020/10/23
 */
public class IOTest {
    static int fun(){
        int m;
        int n;
        m = 1;
        n = 2;
        int r = m + n;
        return r;
    }

    public static void main(String[] args) {
//        try(DataOutputStream out = new DataOutputStream(new FileOutputStream("employee.txt"))){
//            out.writeInt(1234);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(fun());

        int[] a=new int[256];
        for (int i=0;i<256;i++){
            a[i]=i;
        }
        System.out.println(a['c']);
        System.out.println("c的数值："+(int) 'c');
    }
}
