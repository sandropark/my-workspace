package com.sandro.baseproject

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class ClassTest {
    @Test
    fun `class`() {
        class A(var value: Int = 0, val name: String = "name")

        val a = A()

        a.value shouldBe 0
        a.name shouldBe "name"
    }

    @Test
    fun dataClass() {
        data class A(var value: Int = 0, val name: String = "name")

        val a = A()
        val b = A()
        val c = A(name = "nameC")

        a.toString() shouldBe "A(value=0, name=name)"
        a shouldBe b
        a shouldNotBe c
        b shouldNotBe c

        // 원본을 수정하지 말고 복사본을 사용하자.
        val aCopy = a.copy()
        a shouldBe aCopy
    }
}