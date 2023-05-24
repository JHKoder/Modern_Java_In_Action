package github.oineh.part1.chap1.code_present;


import java.util.ArrayList;
import java.util.List;

import static github.oineh.part1.chap1.code_present.Type.GREEN;
import static github.oineh.part1.chap1.code_present.Type.RED;


public class Main {
    public static void main(String[] args) {
        //before
        List<Apple> list = new ArrayList<>();
        for(Apple apple:appDatas()){
            if(GREEN == apple.getColor()){
                list.add(apple);
            }
        }

        //after
        Apple after = new Apple(GREEN,160);
        after.filter(appDatas(),Apple::isGreenApple);
        after.filter(appDatas(),Apple::isHeavyApple);
        after.filter(appDatas(),(Apple a ) -> a.getWeight()>=150);
    }

    private static List<Apple> appDatas() {
        return List.of(
                new Apple(GREEN,100),
                new Apple(GREEN,50),
                new Apple(RED,150)
        );
    }
}
