package com.example.plugins

import com.example.Customer
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.resources.*
import io.ktor.resources.*
import io.ktor.server.resources.Resources
import kotlinx.serialization.Serializable
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.util.*
import kotlinx.html.*

fun Application.configureRouting() {

    routing {
        post("/signup") {
            val formParameters = call.receiveParameters()
            val password = formParameters["password"].toString()
            call.respondText("The '$password' account is created")
        }

        get("/") {
            call.respondHtml {
                body {
                    form(action = "/signup", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.post) {
                        p {
                            +"Username:"
                            textInput(name = "password")
                        }
                        p {
                            +"password:"
                            textInput(name = "password")
                        }

                        p {
                            submitInput() { value = "Create my account" }
                        }
                    }
                }
            }
        }
    }
}


