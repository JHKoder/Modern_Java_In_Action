package github.oineh.part1.chap2.parameter.after;

import github.oineh.chap2.parameter.after.ApplePredicate;
import github.oineh.chap2.parameter.before.Color;

import java.util.ArrayList;
import java.util.List;

public class Apple2 {
    private final Color color;
    private final int weight;

    public Apple2(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    //TODO: 네 번째 시도 : 추상적 조건으로 필터링
    public static List<Apple2> filterApples(List<Apple2> inventory, ApplePredicate p){
        List<Apple2> result = new ArrayList<>();
        for(Apple2 apple2 :inventory){
            if(p.test(apple2)){
                result.add(apple2);
            }
        }
        return result;
    }


    public Color getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

}
