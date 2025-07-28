# Fixture Monkey 예제 프로젝트

이 프로젝트는 네이버에서 개발한 Fixture Monkey 라이브러리의 기본적인 사용법을 보여주는 예제입니다.

## Fixture Monkey란?

Fixture Monkey는 테스트용 객체를 쉽게 생성할 수 있도록 도와주는 Java 라이브러리입니다. 랜덤 데이터나 특정 조건을 만족하는 테스트 객체를 간편하게 생성할 수 있습니다.

## 주요 기능

### 1. 기본 객체 생성

```java
FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
    .objectIntrospector(BeanArbitraryIntrospector.INSTANCE)
    .build();

// 랜덤한 User 객체 하나 생성
User user = fixtureMonkey.giveMeOne(User.class);

// User 객체 5개를 리스트로 생성
List<User> users = fixtureMonkey.giveMe(User.class, 5);
```

### 2. 특정 속성 값 설정

```java
User user = fixtureMonkey.giveMeBuilder(User.class)
    .set("name", "김철수")
    .set("email", "kimchulsoo@example.com")
    .set("age", 30)
    .set("active", true)
    .sample();
```

### 3. 중첩 객체 속성 설정

```java
User user = fixtureMonkey.giveMeBuilder(User.class)
    .set("name", "이영희")
    .set("address.city", "서울")
    .set("address.country", "대한민국")
    .sample();
```

### 4. 컬렉션 속성 설정

```java
User user = fixtureMonkey.giveMeBuilder(User.class)
    .set("name", "박민수")
    .set("hobbies", List.of("독서", "영화감상", "게임"))
    .sample();
```

### 5. Null 값 설정

```java
User user = fixtureMonkey.giveMeBuilder(User.class)
    .set("name", "홍길동")
    .setNull("address")
    .sample();
```

## 실행 방법

1. 테스트 실행:

```bash
./gradlew test
```

2. 특정 테스트 클래스만 실행:

```bash
./gradlew test --tests FixtureMonkeyExampleTest
```

## 프로젝트 구조

- `User.java`: 사용자 도메인 모델
- `Address.java`: 주소 도메인 모델
- `Product.java`: 상품 도메인 모델
- `FixtureMonkeyExampleTest.java`: Fixture Monkey 사용 예제들

## 장점

1. **간편한 테스트 데이터 생성**: 복잡한 객체도 한 줄로 생성 가능
2. **유연한 설정**: 필요한 속성만 설정하고 나머지는 자동 생성
3. **중첩 객체 지원**: 깊이 있는 객체 구조도 쉽게 처리
4. **Lombok 호환**: @Data, @Builder 등과 잘 작동

## 참고 자료

- [Fixture Monkey GitHub](https://github.com/naver/fixture-monkey)
- [공식 문서](https://naver.github.io/fixture-monkey/) 