package github.oineh.chap1.stream;

import github.oineh.chap1.code_present.Apple;
import github.oineh.chap1.code_present.Type;

import java.util.List;
import java.util.stream.Collectors;

public class Stream {
    public static void main(String[] args) {
        List<Apple> inventory = metaData();

        //순차처리방식
        List<Apple> apples = inventory.stream()
                .filter((Apple a) -> a.getWeight() >150)
                .collect(Collectors.toList());

        //병렬처리방식
        List<Apple> applist = inventory.parallelStream()
                .filter(apple -> apple.getWeight() > 150)
                .collect(Collectors.toList());
    }

    private static List<Apple> metaData() {
        return List.of(
                new Apple(Type.GREEN,100),
                new Apple(Type.GREEN,130),
                new Apple(Type.GREEN,150),
                new Apple(Type.GREEN,170)
        );
    }
}
