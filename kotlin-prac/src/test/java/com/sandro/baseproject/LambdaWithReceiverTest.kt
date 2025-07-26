package com.sandro.baseproject

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/**
 * 이거 잘 쓰면 복잡한 로직을 되게 간소화할 수 있을듯
 */
class LambdaWithReceiverTest {
    @Test
    fun test() {
        fun StringBuilder.appendText() {
            append("Hello!")
        }

        val stringBuilder = StringBuilder().apply { appendText() }
        stringBuilder.toString() shouldBe "Hello!"
    }

    @Test
    fun test2() {
        class MenuItem(val name: String)

        class Menu(val name: String) {
            val items = mutableListOf<MenuItem>()

            fun item(name: String) = { items.add(MenuItem(name)) }
        }

        fun menu(name: String, init: Menu.() -> Unit): Menu {
            // Creates an instance of the Menu class
            val menu = Menu(name)
            // Calls the lambda expression with receiver init() on the class instance
            menu.init()
            return menu
        }

        fun printMenu(menu: Menu) {
            println("Menu: ${menu.name}")
            menu.items.forEach { println("  Item: ${it.name}") }
        }

        // Use the DSL
        // Create the menu
        val mainMenu = menu("Main Menu") {
            // Add items to the menu
            item("Home")
            item("Settings")
            item("Exit")
        }

        // Print the menu
        printMenu(mainMenu)
        // Menu: Main Menu
        // Item: Home
        // Item: Settings
        // Item: Exit
    }

    @Test
    fun prac() {
        fun fetchData(callback: StringBuilder.() -> Unit) {
            val builder = StringBuilder("Data received")
            builder.callback()
        }

        fetchData {
            append(" - Processed")
            println(toString())
        }
    }

    @Test
    fun prac2() {
        data class Position(val x: Int, val y: Int)

        data class ButtonEvent(
            val isRightClick: Boolean,
            val amount: Int,
            val position: Position
        )

        class Button {
            fun onEvent(action: ButtonEvent.() -> Unit) {
                // Simulate a double-click event (not a right-click)
                val event = ButtonEvent(isRightClick = false, amount = 2, position = Position(100, 200))
                event.action() // Trigger the event callback
            }
        }

        val button = Button()

        button.onEvent {
            fun isDoubleClick(): Boolean = !isRightClick && amount == 2

            if (isDoubleClick())
                println("Double click!")
        }
    }

    @Test
    fun prac3() {
        fun List<Int>.incremented(): List<Int> {
            val originalList = this
            return buildList { originalList.forEach { add(it + 1) } }
        }

        val originalList = listOf(1, 2, 3)
        val newList = originalList.incremented()
        println(newList)
        // [2, 3, 4]
    }
}