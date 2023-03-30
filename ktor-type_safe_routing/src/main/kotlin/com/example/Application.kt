package com.example
import com.example.resource.Articles
import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.resources.Resources

 import io.ktor.resources.href
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
 import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.i
import kotlinx.html.p
import kotlin.text.get


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)
fun Application.module() {
    install(Resources)
    routing {
        get<Articles> { article ->
            // Get all articles ...
            call.respondText("List of articles sorted starting from ${article.sort}")
        }
        get<Articles.New> {
            // Show a page with fields for creating a new article ...
            call.respondText("Create a new article")
        }
        post<Articles> {
            // Save an article ...
            call.respondText("An article is saved", status = HttpStatusCode.Created)
        }
        get<Articles.Id> { article ->
            // Show an article with id ${article.id} ...
            call.respondText("An article with id ${article.id}", status = HttpStatusCode.OK)
        }
        get<Articles.Id.Edit> { article ->
            // Show a page with fields for editing an article ...
            call.respondText("Edit an article with id ${article.parent.id}", status = HttpStatusCode.OK)
        }
        put<Articles.Id> { article ->
            // Update an article ...
            call.respondText("An article with id ${article.id} updated", status = HttpStatusCode.OK)
        }
        delete<Articles.Id> { article ->
            // Delete an article ...
            call.respondText("An article with id ${article.id} deleted", status = HttpStatusCode.OK)
        }
        get {
            call.respondHtml {
                body {
                    this@module.apply {
                        p {
                            val link: String = href(Articles())
                            a(link) { +"Get all articles" }
                        }
                        p {
                            val link: String = href(Articles.New())
                            a(link) { +"Create a new article" }
                        }
                        p {
                            val link: String = href(Articles.Id.Edit(Articles.Id(id = 123)))
                            a(link) { +"Edit an exising article" }
                        }
                        p {
                            val urlBuilder = URLBuilder(URLProtocol.HTTPS, "ktor.io", parameters = parametersOf("token", "123"))
                            href(Articles(sort = null), urlBuilder)
                            val link: String = urlBuilder.buildString()
                            i { a(link) { +link } }
                        }
                    }
                }
            }
        }
    }

}


