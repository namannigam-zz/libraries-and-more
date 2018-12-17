import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by naman.nigam on 21/12/16.
 */
public class TestOrder {

    @Test
    public void test1() {
        System.out.println("In 1 " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Assert 1 " + Thread.currentThread().getName());
//        Assert.assertTrue(false);
        System.out.println("Out 1 " + Thread.currentThread().getName());
    }

    @Test
    public void test2() {
        System.out.println("In 2 " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Out 2 " + Thread.currentThread().getName());

    }

    @Test(dependsOnMethods = "test1")
    public void test3() {
        System.out.println("In 3 " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Out 3 " + Thread.currentThread().getName());
    }

}