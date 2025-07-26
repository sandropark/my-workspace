package com.sandro.baseproject

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DelegationTest {
    interface Drawable {
        fun draw()
        fun resize()
        val color: String?
    }

    class Circle : Drawable {
        override fun draw() {}
        override fun resize() {}
        override val color: String? = null
    }

    class RedCircle(param: Circle) : Drawable by param {
        override val color: String? = "red"
    }

    @Test
    fun test() {
        val redCircle = RedCircle(Circle())

        redCircle.color shouldBe "red"
    }


    interface Messenger {
        fun sendMessage(message: String)
        fun receiveMessage(): String
    }

    @Test
    fun prac() {
        class BasicMessenger : Messenger {
            override fun sendMessage(message: String) {
                println("Sending message: $message")
            }

            override fun receiveMessage(): String {
                return "You've got a new message!"
            }
        }

        // 부모 클래스를 맴버 변수로 설정하기 위해서는 val을 붙여야 한다.
        class SmartMessenger(val basicMessenger: BasicMessenger): Messenger by basicMessenger {
            override fun sendMessage(message: String) {
                println("Sending a smart message: $message")
                basicMessenger.sendMessage("[smart] $message")
            }
        }

        val basicMessenger = BasicMessenger()
        val smartMessenger = SmartMessenger(basicMessenger)

        basicMessenger.sendMessage("Hello!")
        // Sending message: Hello!
        println(smartMessenger.receiveMessage())
        // You've got a new message!
        smartMessenger.sendMessage("Hello from SmartMessenger!")
        // Sending a smart message: Hello from SmartMessenger!
        // Sending message: [smart] Hello from SmartMessenger!
    }
}