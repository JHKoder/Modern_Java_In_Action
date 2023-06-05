
# 병렬 데이터 처리와 성능

컴퓨터의 멀티코어를 활용해서 파이프아인 연산을 실행할 수 있다즌 점이 가장 중요한 특징 

자바 7 이후 에러를 최소화 할 수 있도록 포크/조인 프레임워크기능이 있다.

parallelStream을 호출하면 병렬스트림이 생성된다. 
병렬스트림이란 각각 스레드에서 처리할 수 있도록 스트림 요소를 여러 청크로 분할한 스트림이다. 
멀티코어 프로세서각 각각의 청크를 처리하도록 할당 할 수 있다.

Stream.iterate(1L, i_ i+1)  : 무한 자연수 스트림 생성
    .limit(n)       // n 이하로 제한
    .reduce(0L,Long::sum); 모든 숫자를 더하는 스트림 리듀ㅣㅇ 연산  

> 스레드 개수, 동기화,  등등 병렬 스트림을 이용하면 근심 걱정없이 모든 문제를 쉽게 해결 할 수 있다.

순차 스트림에서 병렬스트림으로

Stream.iterate(1L , i -> i+1)
    .limit(n)
    .parallel()
    .reduce(0L,Long::sum);

스트림 풀 설정

ForkJoinPool 을 사용한 
프로세서 개수 : Runtime.getRuntime().availableProcessors() 가 반환하는 값에 상응하는 스레드를 갖는다. 

System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","12");

전역 설정 코드로 모든 병렬 스트림에 연산에 영향을 준다.

스트림 성능 축정

병렬화를 이용하면 순차적이나 반복형식에 비해 성능이 더 좋아질 것이라 추축했다.
하지만 소프트웨어 공학에서 추측은 위험한 방법이다.

성능을 최적화할 때는 세가지 항금 규칙을 기억해야한다.
첫째도 측정
둘째도 측정
셋째도 측정!

> JMH 의존성으로 측정하자

org.openjdk.jmh:jmh-core:1.17.4
org.openjdk.jmh:jmh-generator-annprocess:1.17.4

> 어노테이션 프로세서 

```maven
<plugin><id>org.apache.maven.plugins
<execution>
<configuration>
<finalName> benchmarks
<transformats>
<implementation = "org.apache.maven.plugins.shade.resource.ManifestResourceTransformat">
<mainClass>org.openjdk.jmh.Main
```

```java
@BenchmarkMode(Mode.AverageTime)   //벤치마크 대상 메서드를 싱행하는데 걸린 평균 시간 측정
@OutputTimeUnit(TimeUnit.MiLLISECONDS) // 결과를 밀리초 단위로 출력
@FORK(2,jvmArgs={"-Xms4G","-Xmx4G"})// 4Gb의 힙 공간을 제공한 환경에서 두번 수행 
class A{
    @Benchmark // 벤치마크 대상 
    public long test(){
        
    }
    
    @TearDown(Level.Invocation) // 매 번 벤치마크를 실행한 다음에 가비지 컬레터 동작 시도 
    public void down(){
        System.gc();
    }
}
```

**[병렬처리를 할때는 내부적으로 어떤 일이 일어나는지 알아야 한다.]**

## 더 특화된 메서드 사용

> LongStream.RangeClosed 는 기본형 long 을 직접 하용하여 박싱과 언 박싱 오버헤드가 사라진다. 
> 쉽게 청크로 분할 할 수 있는 숫자 범위를 생산하여 분활한다. 1-5, 6-10, 11-15, 16-20

>> 병렬화는 완전 공짜가 아니다 
>
>> 결과를 하나의 값으로 합쳐야 한다. 멀티코어 간의 데이터 이동은 우리 생각보다 비싸다.
> 
#### 병렬 스트림을 잘못 사용하면서 발생하는 문제는 공유된 상태를 바꾸는 알고리즘을 사용하기 때문이다.

### 병렬 스트림 효과적으로 사용하기

- 확신이 서지 않으면 직접 측정하라
- 박싱을 주의하라
- 순차 스트림보다 병렬 ㅅ트림에서 선능이 떨어지는 연산이 있다.
- 스트림에서 수행하는 전체 파이프라인 연산 비용을 고려하라
- 소량의 데이터에서는 병렬 스트림이 도움되지 않는다. 
- 스트림을 구성하는 자료구조가 적절한디 확인해라
- 스트림의 특성과 파이프라인의 중간 연산이 스트림의 특성을 어떻게 바꾸는지 따라 분해관정의 성능이 달라진다.
- 최종 연산 병합 과정 비용을 살펴라

### 포크/조인 프레임워크

- 서브태스크를 스레드 풀의 작업자 스레드에 분산할당하는 ExecutorService 인터페이스

#### RecursiveTask 활용

스레드 풀을 이용하려면 RecursiveTask<R> 의 서브 클래스를 마들어야한다.<br>
R은 병렬화된 태스크가 생성하는 결과 형식 또는 결과가 없을때<br>
RecursiveAction 형식이다. RecursiveTask를 정의하려면 추상 메서드 compute를 구현해야 한다.


### Spliterator 

자바 8에서 지원하며 분활할 수 있는 반복자이다.<br>
병렬 작업에 특화되어있고 커스텀 Spliterator 를 꼭 직접 구현해야하는것은 아니지만 <br>
동작 방식을 이해하면 병렬스트림과 관련한 통찰력을 얻을 수 있다.

> 재귀 방식 


- 내부 반복을 이용하면 다른 스레드를 사용하지 않고도 스트림을 병렬할 수 있다. 
- 병렬이항상 빠르지 않다.
- 병렬 스트림으로 데이터 집합을 처리할때 데이터가 아주 많거아 각 요소를 처리하는데 오랜시간이 걱릴때 성능을 높일 수 있다.
- 기본형 특화 스트림을 사용하는 등 올바른 자료구조를 선택하여 성능을 이끌수 있다.


---
# Sample Code

#### 단어 수 세기
> IntStream.range(0,SENTENCE.length()).mapToObj(SENTENCE::charAt);

#### 문자열 누적하고 합치기 (1)
> stream.reduce(new WordCounter(0,true), <br>WordCounter::accumulate,<br>WordCounter::combine);

