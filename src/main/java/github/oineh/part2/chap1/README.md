## 스트림 소개

스트림이란 **'데이터 처리 연산을 지원하도록 소스에서 추출된 연속된 요소'**

#### 두가지 특징

- **파이프라이닝**
- **내부 반복**

#### 작업

- filter : 특정 요소 제외 시킴
- map  : 한 요소를 다른 요소로 변환 하거나 정보를 추출
- limit : 스트림 크기를 축소
- collect : 다른 형식으로 변환

![](img/스크린샷 2023-05-23 오후 7.13.56.png)

### 컬렉션과 차이점

- 저장,계산 순서
    - 컬렉션은 선 저장, 후 계산
    - 스트림은 선 계산, 후 저장
- 외부,내부 반복
    - 컬렉션은 외부
    - 스트림은 내부

![](img/스크린샷 2023-05-23 오후 7.45.14.png)

### 활용

#### 필터링

##### 프레디케이트로 필터링

프레디케이트(블리언을 반환하는 함수)를 인수로 받아서 일치하는 요소를 포함하는 스트림
```text
List<Dish> vegetarianMenu = menu.stream()
                                  .filter(Dish::isVegetarian)
                                  .collect(toList());
```
---

##### 고유 요소 필터링

스트림은 고유 요소로 이루어진 스트림을 반환하는 distinct() 를 지원한다 <br>
distinct() 의 고유 여부는 객체의 hashCode,Equals로 결정 된다.

리스트를 모두 짝수를 선택하고 중복을 필터링
```text
List<Integer> number = ...;
number.stream()
.filter(i -> i%2 == 0)
.distinct()
.forEach(System.out::println);  
```

#### 스트림 슬라이싱

요소를 선택하거나 스킵하는 방법들
- 처음 몇개를 무시하는 방법
- 특정 크기로 스트림 줄이기 등등 

자바 **9** 은 스트림 요소를 효과적으로 선택 할 수 있도록
takeWhile, dropWhile

##### TAKEWHILE 활용

칼로리 320 이하 요리 선택하기
```java
List<Dish> filterMenu = specialMenu.stream()
        .filter(dish -> dish.getCalories() < 320)
        .collect(toList());
```

이중 문제가 생기면 반복이 중단 될수 있다.
그럴때 아래를 이용해서하면 무한스트림을 포함한 모든 스트림에 프레디케이트를 적용해<br>
슬라이스 할 수 있다.
```java
List<Dish> sliceMenu = specialMenu.stream()
        .takeWhile(dish -> dish.getCalories() < 320)
        .collect(toList());
```

##### DROPWHILE 활용

나머지 요소를 선택하여면 어떻게 해야할까<br>
320보다 큰 요소는 어떻게 탐색할까

```java
List<Dish> sliceMenu = specialMenu.stream()
        .dropWhile(dish -> dish.getCalories() < 320)
        .collect(toList());
```

**[ drop while ]** : 프레디케이트가 처음으로 거짓이 되는 지점까지 발견된 요소를 버린다.
프레디케이트가 거짓이 되면 그 지점에서 작업을 준단하고 남은 모든 요소를 반환한다.
무한한 남은 요소를 가진 무한 스트림에서 동작함

**[ Take While ]** : drop while 과 반대로 작업

#### 스트림 축소

limit(n) 메서드 지원 

```java
List<Dish> dishes = specialMenu.stream()
        .filter(dish -> dish.getCalories() > 320)
        .limit(3)
        .collect(toList());
```

#### 요소 건너뛰기

처음 n개 요소를 제외한 스트림을 반환 
skip(n) 메서드 지원


### 매핑 ( .map)

특정 객체에서 특정 데이터를 선택하여 작업할 수 있다. (**변환**,**매핑**)

- 새로운 요소로 매핑되는 과정에서 기존값을 변환하여 새롭게 매핑함
  (dish -> dish.getName) 이동작 수행시 name 타입을 가진 스트림이매핑됨 


```java
words.stream()
        .map(word -> word.split(""))
        .distinct()
        .collect(toList());
```
위 결과 수행하면 

Input : ["Hello","Word"] <br>
Output: (Stream<String[]> ["H","e","l","l","o","W","o","r","d"]

->  (Stream<String>) 으로 하는 방법은? .flatMap() 활용하여 해결

```java
words.stream()
        .map(word -> word.split(""))
        .flatMap(Arrays::Stream)
        .distinct()
        .collect(toList());
```

flatMap은 각 배열을 스트림이 아니라 스트림의 콘텐츠로 매핑이된다. 