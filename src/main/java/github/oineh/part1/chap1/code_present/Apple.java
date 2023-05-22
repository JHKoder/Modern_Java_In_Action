package github.oineh.part1.chap1.code_present;

import github.oineh.chap1.code_present.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static github.oineh.chap1.code_present.Type.GREEN;

public class Apple {
    private final github.oineh.chap1.code_present.Type color;
    private final int weight;

    public Apple(github.oineh.chap1.code_present.Type color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public Type getColor() {
        return color;

    }

    public static boolean isGreenApple(Apple apple) {
        return GREEN == apple.getColor();
    }
    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() >= 50;
    }
    public interface predicate<T> {
        boolean test(T t);
    }
    List<Apple> filter(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> list = new ArrayList<>();
        for(Apple apple:inventory){
            if(p.test(apple)){
                list.add(apple);
            }
        }
        return list;
    }
}
