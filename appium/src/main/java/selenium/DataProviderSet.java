package selenium;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by naman.nigam on 31/05/17.
 */
public class DataProviderSet {

    @DataProvider(name = "passwordList")
    public static Iterator<Object[]> passwordList() {
        Iterator<Object[]> list = null;
        ArrayList<String> lists = new ArrayList<>();
        return list;
    }
}