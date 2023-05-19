package github.oineh.chap2.parameter.before;

import java.util.ArrayList;
import java.util.List;

import static github.oineh.chap2.parameter.before.Color.GREEN;

public class Apple {
    private final Color color;
    private final int weight;

    public Apple(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    //TODO: 첫 번째 시도: 녹색 사과 필터링
    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(GREEN.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    //TODO: 두 번째 시도: 색을 파리미터화
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:inventory){
            if(apple.getColor().equals(color)){
                result.add(apple);
            }
        }
        return result;
    }
    //TODO: 가능한 모든 속성으로 필터링
    public static List<Apple> filterApplesByWeight(List<Apple> inventory,Color color,int weight,boolean flag){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:inventory){
            if((flag && apple.getColor().equals(color)) ||
                    (!flag && apple.getWeight() > weight)){
                result.add(apple);
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
