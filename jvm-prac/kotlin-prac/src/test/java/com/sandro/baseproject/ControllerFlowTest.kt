package com.sandro.baseproject

import org.junit.jupiter.api.Test

class ControllerFlowTest {
    @Test
    fun conditions() {
        val a = 1
        val b = 2

        println(if (a > b) a else b) // Returns a value: 2
    }

    @Test
    fun `when`() {
        // if와 when을 모두 사용할 수 있는 경우 when을 쓰는 게 더 낫다.
        val obj = "Hello"

        when (obj) {
            // Checks whether obj equals to "1"
            "1" -> println("One")
            // Checks whether obj equals to "Hello"
            "Hello" -> println("Greeting")
            // Default statement
            else -> println("Unknown")
        }
        // Greeting

        // 결과를 받을 수도 있다.
        val result = when (obj) {
            // If obj equals "1", sets result to "one"
            "1" -> "One"
            // If obj equals "Hello", sets result to "Greeting"
            "Hello" -> "Greeting"
            // Sets result to "Unknown" if no previous condition is satisfied
            else -> "Unknown"
        }
        println(result)
        // Greeting

        // ========================================================================
        // when()에 변수를 넣지 않아도 된다.
        val trafficLightState = "Red" // This can be "Green", "Yellow", or "Red"

        val trafficAction = when {
            trafficLightState == "Green" -> "Go"
            trafficLightState == "Yellow" -> "Slow down"
            trafficLightState == "Red" -> "Stop"
            else -> "Malfunction"
        }

        println(trafficAction)
        // Stop
    }

    @Test
    fun roop() {
        for (number in 1..5)
        // number is the iterator and 1..5 is the range
            print(number)
        // 12345

        println()

        for (number in 1..<5)
            print(number)
        // 1234

        println()

        for (number in 5 downTo 1)
            print(number)
        // 54321

        println()

        for (number in 1..5 step 2)
            print(number)
        // 135
    }
}