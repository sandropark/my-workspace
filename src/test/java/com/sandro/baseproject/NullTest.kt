package com.sandro.baseproject

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class NullTest {
    @Test
    fun `null`() {
        var neverNull: String = "This can't be null"
//        neverNull = null // null이 될 수 없다.

        var nullable: String? = "You can keep a null here"
        nullable = null

        "The compiler assumes non-nullable"
//        inferredNonNull = null    // 타입을 지정하지 않으면 기본적으로 null을 허용하지 않는다.

        val strLength: (String) -> Int = String::length  // null 허용안함

        strLength(neverNull) shouldBe 18
//        strLength(nullable) shouldBe 0 // 인자가 null을 허용하지 않기 때문에 컴파일 오류남

        // null이 가능한 경우 방어로직을 넣는다.
        fun strLength(str: String?): Int {
            if (str == null) return 0
            return str.length
        }
    }

    @Test
    fun null2() {
        // Given
        // 인자는 null 가능, 반환 값도 null 가능
        fun lengthString(maybeString: String?): Int? = maybeString?.length

        // When & Then
        // NPE가 나지 않고 그냥 null을 반환한다.
        lengthString(null) shouldBe null
    }

    @Test
    fun null3() {
        class Address(val country: String = "KR")
        class Company(val address: Address? = null)
        class Person(val company: Company? = null)

        val person = Person()
        person.company?.address?.country shouldBe null

        val person1 = Person(Company(Address()))
        person1.company?.address?.country shouldBe "KR"

        // null이면 null을 반환, 아니면 값을 반환.

        // 함수도 안전하게 호출 가능
        val nullable: String? = null
        nullable?.length shouldBe null
    }

    @Test
    fun 엘비스_연산자() {
        // ?: 뒤에 기본값을 설정해두면 반환값이 null인 경우 기본값을 반환한다.
        val getLength: (String?) -> Int? = { str -> str?.length ?: 0 }

        getLength(null) shouldBe 0
    }
}