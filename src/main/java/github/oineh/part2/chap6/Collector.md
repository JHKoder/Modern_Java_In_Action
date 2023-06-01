# Collector 인터페이스

시그니터와 다섯 개의 메서드

```java
public interface Collector<T, A, R> {
    Supplier<A> supplier();
    BiConsumer<A, T> accumulator();
    Function<A, R> finisher();
    BinaryOperator<A> combier();
    Set<Characteristics> characteristics();
}
```

> T 는 수집될 스트림 항목의 제네릭 형식
> A 는 누적자, 수직과정에서 중간결과를 누적하는 객체
> R 은 수집 연산 결과 객체의 형식 