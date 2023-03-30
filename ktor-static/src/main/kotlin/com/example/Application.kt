package com.example

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import java.io.*
fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    routing {
        static("/hi") {
            staticRootFolder = File("files")
            file("index.html")
            default("index.html")
            static("images") {
                file("ktor_logo.png")
                file("image.png", "ktor_logo.png")
            }
            static("assets") {
                files("css")
                files("js")
            }
        }
    }
}