package github.oineh.chap2.Comparator;

import github.oineh.chap1.code_present.Apple;
import github.oineh.chap1.code_present.Type;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Apple> inventory = date();
        //before
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() + o2.getWeight();
            }
        });

        //after
        inventory.sort((Apple a1,Apple a2) -> a1.getWeight() + a2.getWeight());
    }

    public static List<Apple> date() {
        return List.of(new Apple(Type.RED, 150),
                new Apple(Type.RED, 150),
                new Apple(Type.RED, 150),
                new Apple(Type.RED, 150));
    }
}
