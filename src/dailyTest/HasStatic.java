package dailyTest;

/**
 * @Author: zja
 * @Description:
 * @Date: Created in 14:22 2020/9/27
 */
   public class HasStatic{
     private static int x=100;
     public static void main(String args[]){
                  HasStatic hs1=new HasStatic();
                  hs1.x++;
                  HasStatic  hs2=new HasStatic();
                  hs2.x++;
                  hs1=new HasStatic();
                 hs1.x++;
               HasStatic.x--;
              System.out.println("x="+x);
            }
  }


  class Test{
      public static void main(String[] args) {
      }
  }