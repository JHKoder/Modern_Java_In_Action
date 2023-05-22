package github.oineh.chap3.Consumer;

//TODO : T형식의 객체를 인수로 받아서 어떤 동작을 수행하고 싶을때
@FunctionalInterface
public interface Consumer<T>{
    void accept(T t);
}
