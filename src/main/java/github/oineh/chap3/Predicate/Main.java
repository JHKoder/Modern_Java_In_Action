package github.oineh.chap3.Predicate;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> listOfStrings = data();
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(listOfStrings,nonEmptyStringPredicate);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> results = new ArrayList<>();
        for(T t: list){
            if(p.test(t)){
                results.add(t);
            }
        }
        return results;
    }

    private static List<String> data() {
        return List.of(
                "help","String","JeongHun Kang",null,""
        );
    }

}
