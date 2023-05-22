package github.oineh.part1.chap3.Consumer;

import github.oineh.chap3.Consumer.Consumer;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        forEach(Arrays.asList(1,2,3,4),
                System.out::println);
    }
    public static <T> void forEach(List<T> list, Consumer<T> c){
        for(T t:list){
            c.accept(t);
        }
    }
}
