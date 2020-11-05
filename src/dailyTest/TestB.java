package dailyTest;

/**
 * @Author: zja
 * @Description:
 * @Date: Created in 11:03 2020/9/27
 */
public class TestB extends TestA{

    public TestB() {
        super();
        super.setName();
    }

    protected static int t2=2;

    static {
        t1=21;
        t2=20;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        TestB testB = new TestB();

        System.out.println("t1:"+t1);
        System.out.println("t2:"+t2);
//        Class<TestB> c=TestB.class;
//        TestB b = c.newInstance();
//        System.out.println(Arrays.toString(c.getFields()));
//        Field name = c.getField("name");
//        name.set(b,"666");
//        System.out.println(b.name);
    }
}
