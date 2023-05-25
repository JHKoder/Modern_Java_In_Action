# 스트림으로 데이터 수집 
+ Collectors 클래스로 컬렉션을 만들고 사용하기
+ 하나의 값으로 데이터 스트림 리듀스하기
+ 특별한 리듀싱 요약 연산
+ 데이터 그룹화와 분활
+ 자신만의 커스텀 컬렉터 개발

- 컬렉션 ( Collection )
- 컬렉터 ( Collector ) 인터페이스

> 다음을 구현 할 수 있다.
- 통화별로 트랜잭션을 그룹화 한 다음에 해당 통화로 인러난 모든 트랜잭션 합게를 계산하시오(Map<Currency,Integer)
- 트랜잭션을 비싼 트랜잭션과 저렴한 트랜잭션 두 그룹으로 분류하시오(Map<Boolean,List<Transaction>>)
- 트랜잭션을 도시 등 다수준으로 그룹화하시오. 그리고 각 트랜잭션이 비싼지 저렴한지 구분하시오(Map<String,Map<Boolean,List<Transaction>>>)

---

## 컬렉터란 무엇인가.

### 고급 리듀싱 기능을 수행하는 컬렉터

- 높은 수준의 조합성과 재사용성을 꼽을 수 있다.
- Collect로 결과를 수집하는 과정을 간단하면서도 우연한 방식으로 정의할수 있다는 것이 최대 장점이다.

### 미리 정의된 컬렉터

- Collectors 에서 제공하는 메서드의 기능은 크게 세 가지로  구분 한다.


## 스트림에서 최대값,최솟값

- Collectors.maxBy
- Collectors.minBy
    - Optional<Dish> re = stream().collect(maxBy(dishCaloriesComparator));


## 리듀싱 과 요약

- before
  -  stream().collect(Collectors.counting()));
- after
  - stream().count();

- avg = stream().collect(averagingInt(Dish::getCal));

- [다음 코드로 요소 수, 칼로리합게, 편균,초대값,최솟값 등을 계산하는 요소이다.]
  - IntSummaryStatistics in = stream().collect(summarizingInt(Dish::getCal));
    -  IntSummaryStatistics 클래스로 모든 정보가 수집된다.
      - toString() =  {count,sum,min,average,max}

## 문자열 연결

- 컬렉터에 joining 팩토리 메서드를 이용하면 스트림의 각객체에 toString() 호출하여 추출한 모든 문자열을 하나의 문자로 연결하여 반환
  - String s = stream().map(dish::getName)).collect(joining());
    - 내부적 구현으로 StringBuilder를 이용하여 문자열을 하나로 만든다.
      - toString()을 구현하고 있다면 생략가능
        - stream().collect(joining());
- 구분자를 넣을 수 있다.
  - String s = stream().map(Dish::getName).collect(joining(", "));

#### 범용 리듀싱 요약 연산

- 모든 메뉴의 칼로리 합계 
  - int total = stream().collect(reducing(0, Dish::getCal, (i ,j ) -> i + j));
- 한개의 인수를 가진 리듀싱 버전을 이용해 가낲 높은 요리를 찾기 [**항등 함수**]
  - Optional<Dish> mos = stream().collect(reducing(d1 ,d2) -> d1.getCal() > d2.getCal() ? d1 : d2 ));

## 제네릭 와일드카드 '?' 사용법
```java
class Sample {
  public static <T> Collector<T, ?, Long> counting() {
    return reducing(0L, e -> 1L, Long::sum);
  }
}
```
- Long객체 형식의 요소를 1로 변환한 다음에 모두 더할 수 있다.
- '?' 누적자 형식이 알려지 않음 
- 즉 자유로움을 의미한다.

## 그룹화 
