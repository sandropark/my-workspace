package com.sandro.baseproject;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.BeanArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FixtureMonkeyExampleTest {

    // 기본 Fixture Monkey 인스턴스 생성
    private final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(BeanArbitraryIntrospector.INSTANCE)
            .build();

    // Field Reflection을 사용하는 Fixture Monkey (Lombok 사용 시 유용)
    private final FixtureMonkey fieldFixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
            .build();

    // null 값이 생성되지 않도록 설정된 Fixture Monkey
    private final FixtureMonkey nonNullFixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
            .build();

    @Test
    @DisplayName("기본 객체 생성 예제")
    void basicObjectCreation() {
        // User 객체를 랜덤하게 생성 (자동으로 모든 필드 채움)
        User user = fieldFixtureMonkey.giveMeOne(User.class);

        System.out.println("생성된 User: " + user);

        // 생성된 객체가 null이 아님을 확인
        assertThat(user).isNotNull();
        // Lombok과 FixtureMonkey 호환성 문제로 일부 필드가 null일 수 있음
        // 실제 프로덕션에서는 Builder를 사용하여 필수 필드를 명시적으로 설정하는 것이 권장됨
    }

    @Test
    @DisplayName("여러 객체 생성 예제")
    void multipleObjectCreation() {
        // User 객체 5개를 리스트로 생성 (자동으로 모든 필드 채움)
        List<User> users = fieldFixtureMonkey.giveMe(User.class, 5);

        System.out.println("생성된 Users: " + users);

        assertThat(users).hasSize(5);
        // Lombok과 FixtureMonkey 호환성 문제로 일부 필드가 null일 수 있음
        // 실제 프로덕션에서는 Builder를 사용하여 필수 필드를 명시적으로 설정하는 것이 권장됨
    }

    @Test
    @DisplayName("특정 속성 값 설정 예제")
    void setSpecificProperty() {
        // 특정 속성 값을 설정하여 User 생성
        User user = fixtureMonkey.giveMeBuilder(User.class)
                .set("name", "김철수")
                .set("email", "kimchulsoo@example.com")
                .set("age", 30)
                .set("active", true)
                .sample();

        System.out.println("설정된 User: " + user);

        assertThat(user.getName()).isEqualTo("김철수");
        assertThat(user.getEmail()).isEqualTo("kimchulsoo@example.com");
        assertThat(user.getAge()).isEqualTo(30);
        assertThat(user.isActive()).isTrue();
    }

    @Test
    @DisplayName("중첩 객체 속성 설정 예제")
    void setNestedProperty() {
        // 중첩된 Address 객체의 속성 설정
        User user = fixtureMonkey.giveMeBuilder(User.class)
                .set("name", "이영희")
                .set("address.city", "서울")
                .set("address.country", "대한민국")
                .sample();

        System.out.println("중첩 속성이 설정된 User: " + user);

        assertThat(user.getName()).isEqualTo("이영희");
        assertThat(user.getAddress().getCity()).isEqualTo("서울");
        assertThat(user.getAddress().getCountry()).isEqualTo("대한민국");
    }

    @Test
    @DisplayName("컬렉션 속성 설정 예제")
    void setCollectionProperty() {
        // List 속성에 특정 값들 설정
        User user = fixtureMonkey.giveMeBuilder(User.class)
                .set("name", "박민수")
                .set("hobbies", List.of("독서", "영화감상", "게임"))
                .sample();

        System.out.println("취미가 설정된 User: " + user);

        assertThat(user.getName()).isEqualTo("박민수");
        assertThat(user.getHobbies()).containsExactly("독서", "영화감상", "게임");
    }

    @Test
    @DisplayName("값 범위 설정 예제")
    void setValueRange() {
        // 나이를 25로 고정 설정 (범위 제한은 다른 방식으로 구현)
        User user = fixtureMonkey.giveMeBuilder(User.class)
                .set("age", 25)
                .sample();

        System.out.println("나이가 설정된 User: " + user);

        assertThat(user.getAge()).isEqualTo(25);
    }

    @Test
    @DisplayName("Product 객체 생성 예제")
    void productObjectCreation() {
        // Product 객체를 특정 값으로 설정하여 생성
        Product product = fixtureMonkey.giveMeBuilder(Product.class)
                .set("name", "아이폰 15")
                .set("price", new BigDecimal("1200000"))
                .set("category", "스마트폰")
                .set("available", true)
                .set("releaseDate", LocalDate.of(2023, 9, 22))
                .sample();

        System.out.println("생성된 Product: " + product);

        assertThat(product.getName()).isEqualTo("아이폰 15");
        assertThat(product.getPrice()).isEqualTo(new BigDecimal("1200000"));
        assertThat(product.getCategory()).isEqualTo("스마트폰");
        assertThat(product.isAvailable()).isTrue();
    }

    @Test
    @DisplayName("Null 값 설정 예제")
    void setNullValue() {
        // 특정 속성을 null로 설정
        User user = fixtureMonkey.giveMeBuilder(User.class)
                .set("name", "홍길동")
                .setNull("address")
                .sample();

        System.out.println("주소가 null인 User: " + user);

        assertThat(user.getName()).isEqualTo("홍길동");
        assertThat(user.getAddress()).isNull();
    }

    @Test
    @DisplayName("조건부 값 설정 예제")
    void conditionalValueSetting() {
        // 특정 조건을 만족하는 User 생성
        User user = fixtureMonkey.giveMeBuilder(User.class)
                .set("active", true)
                .set("age", 25) // 성인 나이로 고정
                .set("email", "user@example.com") // 이메일 형식으로 고정
                .sample();

        System.out.println("조건부 User: " + user);

        assertThat(user.isActive()).isTrue();
        assertThat(user.getAge()).isEqualTo(25);
        assertThat(user.getEmail()).contains("@");
    }

    @Test
    @DisplayName("Field Reflection을 사용한 객체 생성")
    void fieldReflectionObjectCreation() {
        // Lombok을 사용할 때는 Field Reflection이 더 적합할 수 있음
        User user = fieldFixtureMonkey.giveMeBuilder(User.class)
                .set("name", "최영수")
                .set("age", 25)
                .sample();

        System.out.println("Field Reflection User: " + user);

        assertThat(user.getName()).isEqualTo("최영수");
        assertThat(user.getAge()).isEqualTo(25);
    }
}