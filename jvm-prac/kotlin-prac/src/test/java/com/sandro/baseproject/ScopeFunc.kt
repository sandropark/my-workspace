package com.sandro.baseproject

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ScopeFunc {
    /*
    프로그래밍에서 스코프(scope)는 변수나 객체가 인식되는 영역입니다.
    가장 일반적으로 사용되는 스코프는 전역 스코프(global scope)와 지역 스코프(local scope)입니다.

    - 글로벌 범위 - 프로그램의 어느 곳에서나 접근할 수 있는 변수 또는 객체입니다.
    - 로컬 범위 - 정의된 블록이나 함수 내에서만 접근 가능한 변수나 객체입니다.

    Kotlin에는 객체 주변에 임시 범위를 생성하고 일부 코드를 실행할 수 있는 범위 함수도 있습니다.

    범위 함수를 사용하면 임시 범위 내에서 객체의 이름을 참조할 필요가 없으므로 코드가 더 간결해집니다.
    범위 함수에 따라 it. 키워드를 통해 객체를 참조하거나 this키워드를 통해 객체를 인수로 사용하여 객체에 접근할 수 있습니다.

    Kotlin에는 총 5개의 범위 함수가 있습니다: let, apply, run, also, with.
     */

    @Test
    fun let() {
        fun sendNotification(recipientAddress: String): String {
            println("Yo $recipientAddress!")
            return "Notification sent!"
        }

        fun getNextAddress(): String {
            return "sebastian@jetbrains.com"
        }

        val address: String? = getNextAddress()
//        sendNotification(address) // notNull이라 컴파일 오류

        // 방법 1 - null 검사로 null이 아닌지 확인 후 인자로 넘긴다.
        var confirm = if (address != null) {
            sendNotification(address)
        } else {
            null
        }

        confirm shouldBe "Notification sent!"

        // let 사용 - ? 키워드와 함께 let을 사용해서 null이 아닌 경우 값이 반환되도록 처리한다.
        confirm = address?.let {
            sendNotification(it)
        }

        confirm shouldBe "Notification sent!"
    }

    class Client() {
        var token: String? = null
        fun connect() = println("connected!")
        fun authenticate() = println("authenticated!")
        fun getData(): String = "Mock data"
    }

    @Test
    fun apply() {
        var client = Client() // 객체 생성

        // 맴버 변수 설정 및 함수 호출
        client.token = "asdf"
        client.connect()
        client.authenticate()

        client.getData() shouldBe "Mock data"

        // apply 사용. 생성 시점에 바로 초기화 (생성자에 로직을 넣지 않아도 된다.)
        client = Client().apply {
            token = "asdf"
            connect()
            authenticate()
        }

        client.getData() shouldBe "Mock data"
    }

    @Test
    fun run() {
        val client: Client = Client().apply {
            token = "asdf"
        }

        val result: String = client.run {
            connect()
            authenticate()
            getData()
        }

        result shouldBe "Mock data"
    }

    @Test
    fun also() {
        val medals: List<String> = listOf("Gold", "Silver", "Bronze")

        val result: List<String> = medals
            .map { it.uppercase() }
            .filter { it.length > 4 }
            .reversed()
        result shouldBe listOf("BRONZE", "SILVER")

        // also를 사용해서 이터레이션 중간에 로깅을 한다.
        val result2: List<String> = medals
            .map { it.uppercase() }
            .also { println(it) }
            .filter { it.length > 4 }
            .also { println(it) }
            .reversed()

        result2 shouldBe listOf("BRONZE", "SILVER")
    }

    @Test
    fun with() {
        class Canvas {
            fun rect(x: Int, y: Int, w: Int, h: Int): Unit = println("$x, $y, $w, $h")
            fun circ(x: Int, y: Int, rad: Int): Unit = println("$x, $y, $rad")
            fun text(x: Int, y: Int, str: String): Unit = println("$x, $y, $str")
        }

        val mainMonitorPrimaryBufferBackedCanvas = Canvas()

        // with 사용 X
        mainMonitorPrimaryBufferBackedCanvas.text(10, 10, "Foo")
        mainMonitorPrimaryBufferBackedCanvas.rect(20, 30, 100, 50)
        mainMonitorPrimaryBufferBackedCanvas.circ(40, 60, 25)
        mainMonitorPrimaryBufferBackedCanvas.text(15, 45, "Hello")
        mainMonitorPrimaryBufferBackedCanvas.rect(70, 80, 150, 100)
        mainMonitorPrimaryBufferBackedCanvas.circ(90, 110, 40)
        mainMonitorPrimaryBufferBackedCanvas.text(35, 55, "World")
        mainMonitorPrimaryBufferBackedCanvas.rect(120, 140, 200, 75)
        mainMonitorPrimaryBufferBackedCanvas.circ(160, 180, 55)
        mainMonitorPrimaryBufferBackedCanvas.text(50, 70, "Kotlin")

        // with 사용
        with(mainMonitorPrimaryBufferBackedCanvas) {
            text(10, 10, "Foo")
            rect(20, 30, 100, 50)
            circ(40, 60, 25)
            text(15, 45, "Hello")
            rect(70, 80, 150, 100)
            circ(90, 110, 40)
            text(35, 55, "World")
            rect(120, 140, 200, 75)
            circ(160, 180, 55)
            text(50, 70, "Kotlin")
        }
    }

    @Test
    fun letPrac() {
        /*
        data class ProductInfo(val priceInDollars: Double?)

        class Product {
            fun getProductInfo(): ProductInfo? {
                return ProductInfo(100.0)
            }
        }

        fun convertToEuros(dollars: Double): Double {
            return dollars * 0.85
        }

        // Rewrite this function
        fun Product.getPriceInEuros(): Double? {
            val info = getProductInfo()
            if (info == null) return null
            val price = info.priceInDollars
            if (price == null) return null
            return convertToEuros(price)
        }

        val product = Product()
        val priceInEuros = product.getPriceInEuros()

        if (priceInEuros != null) {
            println("Price in Euros: €$priceInEuros")
            // Price in Euros: €85.0
        } else {
            println("Price information is not available.")
        }
         */

        data class ProductInfo(val priceInDollars: Double?)

        class Product {
            fun getProductInfo(): ProductInfo? = ProductInfo(100.0)
        }

        fun convertToEuros(dollars: Double): Double = dollars * 0.85
        fun Product.getPriceInEuros(): Double? = getProductInfo()?.priceInDollars?.let { return convertToEuros(it) }

        val product = Product()

        product.getPriceInEuros() shouldBe 85
    }

    @Test
    fun applyAndAlso() {
        data class User(val id: Int, var email: String)

        fun updateEmail(user: User, newEmail: String): User =
            user.apply { this.email = newEmail }
                .also { println("Updating email for user with ID: ${it.id}") }


        val user = User(1, "old_email@example.com")
        val updatedUser = updateEmail(user, "new_email@example.com")

        updatedUser shouldBe User(id = 1, email = "new_email@example.com")
    }
}