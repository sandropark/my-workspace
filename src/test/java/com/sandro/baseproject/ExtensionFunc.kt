package com.sandro.baseproject

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.net.http.HttpResponse

class ExtensionFunc {
    @Test
    fun ef() {
        // String에 확장 함수 추가
        fun String.bold(): String = "<b>$this</b>"

        "hello".bold() shouldBe "<b>hello</b>"
    }

    @Test
    fun ef2() {
        class HttpClient {
            fun request(method: String, url: String, headers: Map<String, String>): HttpResponse<String> {
                return Any() as HttpResponse<String>
            }
        }

        // get, post메서드를 추가
        fun HttpClient.get(url: String): HttpResponse<String> = request("GET", url, emptyMap())
        fun HttpClient.post(url: String): HttpResponse<String> = request("POST", url, emptyMap())
    }
}