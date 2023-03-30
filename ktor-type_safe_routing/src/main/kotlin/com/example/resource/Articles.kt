package com.example.resource

import io.ktor.resources.*

@Resource("/articles")
class Articles(val sort: String? = "new") {
    @Resource("new")
    class New(val parent: Articles = Articles())

    @Resource("{id}")
    class Id(val parent: Articles = Articles(), val id: Long) {
        @Resource("edit")
        class Edit(val parent: Id)
    }}