package com.sandro.baseproject

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class FunctionTest {
    @Test
    fun function() {
        fun sum(x: Int, y: Int): Int = x + y // 간결하게 작성가능. 반환타입을 명시적으로 작성하는 것을 추천

        sum(1, 2) shouldBe 3
    }

    @Test
    fun lambda() {
        fun uppercaseString(text: String): String {
            return text.uppercase()
        }
        uppercaseString("hello") shouldBe "HELLO"


        val upperCaseString: (String) -> String = { text: String -> text.uppercase() } // 람다로 표현 가능
        upperCaseString("hello") shouldBe "HELLO"


        val simpleLambda = { "hello" }
        simpleLambda() shouldBe "hello"
    }

    @Test
    fun lambda2() {
        // Given
        val numbers = listOf(1, -2, 3, -4, 5, -6)

        // When
        val positives = numbers.filter { x: Int -> x > 0 }  // 괄호를 없애고 람다식을 바로 넘길 수 있음

        // Then
        positives shouldBe listOf(1, 3, 5)
    }

    @Test
    fun lambda3() {
        // 람다를 정의하면서 바로 호출할 수 있다.
        { text: String -> text.uppercase() }("hello") shouldBe "HELLO"
    }

    @Test
    fun trailingLambdas() {
        listOf(1, 2, 3).fold(0, { x, item -> x + item }) shouldBe 6

        // 람다가 인자 마지막 요소라면 인자 밖에 람다를 지정할 수도 있다.
        listOf(1, 2, 3).fold(0) { x, item -> x + item } shouldBe 6
    }
}