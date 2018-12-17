package pack;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class Sample {

    public static void main(String[] args) {
        Single<List<List<Person>>> singles = Single.just(Sample.getPersons());
//        singles.observeOn(Schedulers.io()).map(x -> System.out.println(x.size()));


    }


    private static List<List<Person>> getPersons() {
        return new ArrayList<>();
    }


}