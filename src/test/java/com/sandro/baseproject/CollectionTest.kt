package com.sandro.baseproject

import org.junit.jupiter.api.Test

class CollectionTest {
    @Test
    fun list() {
        // Read only list
        val readOnlyShapes = listOf("triangle", "square", "circle")
        println(readOnlyShapes)
        // [triangle, square, circle]

        // Mutable list with explicit type declaration
        val shapes: MutableList<String> = mutableListOf("triangle", "square", "circle")
        println(shapes)
        // [triangle, square, circle]

        /*
         타입을 지정할 때 불변은 List, 가변은 MutableList를 사용한다.

         val shapes: MutableList<String> = mutableListOf("triangle", "square", "circle")
         val shapesLocked: List<String> = shapes
         */
    }

    @Test
    fun indexing() {
        val readOnlyShapes = listOf("triangle", "square", "circle")
        println("The first item in the list is: ${readOnlyShapes[0]}")
        println("The first item in the list is: ${readOnlyShapes.first()}") // triangle
        println("The first item in the list is: ${readOnlyShapes.last()}")  // circle
    }

    @Test
    fun set() {
        val readOnlyFruit = setOf("apple", "banana", "cherry", "cherry")
        println("banana" in readOnlyFruit)
    }

    @Test
    fun map() {
        val readOnlyJuiceMenu = mapOf("apple" to 100, "kiwi" to 190, "orange" to 100)
        println("orange" in readOnlyJuiceMenu.keys)
        // true

        // Alternatively, you don't need to use the keys property
        println("orange" in readOnlyJuiceMenu)
        // true

        println(200 in readOnlyJuiceMenu.values)
        // false
    }
}