package github.oineh.chap2.parameter;

import github.oineh.chap2.parameter.after.Apple2;
import github.oineh.chap2.parameter.after.AppleRedAndHeavyPredicate;
import github.oineh.chap2.parameter.before.Apple;

import java.util.List;

import static github.oineh.chap2.parameter.after.Apple2.filterApples;
import static github.oineh.chap2.parameter.before.Apple.filterApplesByWeight;
import static github.oineh.chap2.parameter.before.Color.GREEN;
import static github.oineh.chap2.parameter.before.Color.RED;

public class Main {
    public static void main(String[] args) {
        List<Apple> inventory = beforeApple();
        List<Apple2> afterInventory = afterApple();

        //before
        List<Apple> redApple = filterApplesByWeight(inventory,RED,100,true);

        //after
        List<Apple2> redAndHeavyApples = filterApples(afterInventory,new AppleRedAndHeavyPredicate());
    }

    private static List<Apple> beforeApple() {
        return List.of(
                new Apple(GREEN,100),
                new Apple(GREEN,50),
                new Apple(RED,150)
        );
    }
    private static List<Apple2> afterApple() {
        return List.of(
                new Apple2(GREEN,100),
                new Apple2(GREEN,50),
                new Apple2(RED,150)
        );
    }
}
