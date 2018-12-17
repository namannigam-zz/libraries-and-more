package hashcode;

import java.util.ArrayList;

/**
 * Created by naman.nigam on 06/10/16.
 */
public class HashCodeTest {

    public static void main(String[] args) {
        OneObject one = new OneObject();
        OneObject two = new OneObject();
        one.currency = Currency.INR;
        one.field = 1;
        one.xyz = new double[]{1.0, 1.0};
        one.arrayList = new ArrayList();

        two.currency = Currency.INR;
        two.field = 10.000;
        two.xyz = new double[]{2.0,2.0};
        two.arrayList = new ArrayList();

        System.out.println(one.hashCode());
        System.out.println(two.hashCode());
        if (one.equals(two)) {
            System.out.println("Equal object!");
        } else {
            System.out.println("Unequal object!!");
        }

        SubObject subObject = new SubObject();
        System.out.println(subObject.hashCode());
        System.out.println(subObject.hashCode("e"));
    }
}