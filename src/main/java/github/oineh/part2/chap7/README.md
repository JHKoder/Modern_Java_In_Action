## 병렬 데이터 처리와 성능

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